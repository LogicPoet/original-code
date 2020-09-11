package cn.catguild.reactor.official.basic;

import cn.catguild.reactor.SampleSubscriber;
import org.junit.Test;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建flux或mono并订阅的简单方法
 *
 * @author LZ
 * @date 2020/9/11 11:01
 **/
public class BasicTest {

    /**
     * Simple Ways to Create a Flux or Mono and Subscribe to It
     */
    @Test
    public void createTest() {
        Flux<String> seq1 = Flux.just("foo", "bar", "foobar");

        List<String> iterable = Arrays.asList("foo", "bar", "foobar");
        Flux<String> seq2 = Flux.fromIterable(iterable);

        // =============================================================
        // Notice the factory method honors the generic type even though it has no value.
        Mono<String> noData = Mono.empty();
        Mono<String> data = Mono.just("foo");
        Flux<Integer> numbersFormFiveToSeven = Flux.range(5, 3);
    }

    /**
     * subscribe Method Examples
     */
    @Test
    public void simpleSubscribeTest() {
        Flux<Integer> ints = Flux.range(1, 3);
        // 最简单的订阅
        ints.subscribe();

        // 订阅并将值输出到控制台
        //ints.subscribe(i -> System.out.println(i));
        ints.subscribe(System.out::println);
    }

    /**
     * 携带错误处理器 的订阅者
     */
    @Test
    public void errSubscribeTest() {
        Flux<Integer> ints = Flux.range(1, 4)
                .map(i -> {
                    if (i <= 3) return i;
                    throw new RuntimeException("Got to 4");
                });

        // 使用带error处理器的订阅
        ints.subscribe(System.out::println, error -> System.err.println("Error: " + error));
    }

    /**
     * 携带错误处理器和完成事件处理器 的订阅者
     * ps：这两个处理器是互斥的，最终只有一个会被执行
     * <p>
     * 完成回调没有输入，由一对空括号表示：它与接口中的run方法匹配Runnable。
     */
    @Test
    public void errAndComSubscribeTest() {
        Flux<Integer> ints = Flux.range(1, 4);

        // 使用带error处理器的订阅
        ints.subscribe(System.out::println
                , error -> System.err.println("Error: " + error)
                , () -> System.out.println("Done")
        );
    }

    /**
     * subscribe方法的最后一个签名包括一个Consumer<Subscription>
     * 该变体需要您对进行某些操作Subscription（request(long)对它执行操作 cancel()）。否则Flux挂起。
     */
    @Test
    public void errAndComAndConSubscribeTest() {
        Flux<Integer> ints = Flux.range(1, 4);

        ints.subscribe(System.out::println
                , error -> System.err.println("Error: " + error)
                , () -> System.out.println("Done")
                //当我们订阅时，我们会收到Subscription
                //表示我们10 要从源头获取元素（实际上将发出4个元素并完成）。
                , sub -> sub.request(10)
        );
    }

    /**
     * 自定义的 订阅器
     */
    @Test
    public void sampleSubscribeTest() {
        SampleSubscriber<Integer> ss = new SampleSubscriber<>();
        Flux<Integer> ints = Flux.range(1, 4);

        ints.subscribe(System.out::println
                , error -> System.err.println("Error: " + error)
                , () -> System.out.println("Done")
                //当我们订阅时，我们会收到Subscription
                //表示我们10 要从源头获取元素（实际上将发出4个元素并完成）。
                , sub -> sub.request(10)
        );

        ints.subscribe(ss);
    }


    /**
     * On Backpressure and Ways to Reshape Requests
     * <p>
     * ps:
     * When manipulating a request,
     * you must be careful to produce enough demand for the sequence to advance,
     * or your Flux can get “stuck”.
     * That is why BaseSubscriber defaults to an unbounded request in hookOnSubscribe.
     * When overriding this hook, you should usually call request at least once.
     */
    @Test
    public void backPressureSubscribeTest() {
        Flux.range(1, 10)
                .doOnRequest(r -> System.out.println("request of " + r))
                .subscribe(new BaseSubscriber<Integer>() {

                    @Override
                    protected void hookOnSubscribe(Subscription subscription) {
                        request(1);
                    }

                    @Override
                    protected void hookOnNext(Integer value) {
                        System.out.println("Cancelling after having received " + value);
                        cancel();
                    }

                });
    }

}