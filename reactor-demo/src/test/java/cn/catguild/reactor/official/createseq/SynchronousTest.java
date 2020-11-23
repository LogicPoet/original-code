package cn.catguild.reactor.official.createseq;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 同步创建Flux序列
 *
 * 以编程方式创建Flux的最简单形式是通过generate方法，该方法带有一个generator函数。
 * <p>
 * 这用于synchronous发射和one-by-one发射，这意味着接收器是SynchronousSink，并且其next（）方法在每次回调调用中最多只能调用一次。
 *
 * @author LZ
 * @date 2020/9/11 14:51
 **/
public class SynchronousTest {
    /**
     * Example of state-based generate
     */
    @Test
    public void stateBasedTest() {

        Flux<String> flux = Flux.generate(
                () -> 0, //提供初始状态值0。
                (state, sink) -> {
                    sink.next("3 x " + state + " = " + 3 * state); //使用状态来选择要发出的信号（乘法表3中的一行）
                    if (state == 10)
                        sink.complete(); //使用它来选择何时停止
                    return state + 1; //回在下一次调用中使用的新状态（除非序列在此调用中终止）
                });

        flux.subscribe(System.out::println);

    }

    /**
     * Example of Mutable state variant generate
     * 可变状态变量
     */
    @Test
    public void mutableStateVariantTest() {

        Flux<String> flux = Flux.generate(
                AtomicInteger::new, //生成一个可变对象作为状态。
                (state, sink) -> {
                    long i = state.getAndIncrement(); // 在这里改变状态
                    sink.next("3 x " + i + " = " + 3 * i); //使用状态来选择要发出的信号（乘法表3中的一行）
                    if (i == 10)
                        sink.complete();
                    return state; //返回与新状态相同的实例
                });

        flux.subscribe(System.out::println);

    }

    /**
     * 清理最后一个状态实例
     * <p>
     * 如果状态包含在过程结束时需要处理的数据库连接或其他资源，
     * 则Consumerlambda可以关闭连接或以其他方式处理应在过程结束时完成的任何任务
     */
    @Test
    public void cleanUpStateInstanceTest() {
        Flux<String> flux = Flux.generate(
                AtomicInteger::new,//生成一个可变对象
                (state, sink) -> {
                    int i = state.getAndIncrement();//改变状态
                    sink.next("3 x " + i + " = " + 3 * i);
                    if (i == 10)
                        sink.complete();
                    return state; // 返回与新状态相同的实例
                },
                (state) -> System.out.println("state: " + state) //将最后一个状态值（11）视为consumer lambda的输出
        );
    }
}
