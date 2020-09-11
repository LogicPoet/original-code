package cn.catguild.reactor.official.createseq;

import org.junit.Test;
import reactor.core.publisher.Flux;

/**
 * handle方法有点不同： 它是一个实例方法，意味着它被链接在一个现有源上（与普通运算符一样）。 它存在于Mono和Flux中。
 * <p>
 * 就其使用SynchronousSink并仅允许一对一发射的意义而言，它几乎可以生成。
 * 但是，可以使用handle从每个源元素中生成任意值，可能会跳过某些元素。
 * 这样，它可以用作map和filter的组合
 *
 * @author LZ
 * @date 2020/9/11 17:31
 **/
public class HandleTest {

    public static String alphabet(int letterNumber) {
        if (letterNumber < 1 || letterNumber > 26) {
            return null;
        }
        int letterIndexAscii = 'A' + letterNumber - 1;
        return "" + (char) letterIndexAscii;
    }

    @Test
    public void handleTest() {
        Flux<String> alphabet = Flux.just(-1, 30, 13, 9, 20)
                .handle((i, sink) -> {
                    String letter = alphabet(i); // 映射到字母。
                    if (letter != null)// 如果“ map function”返回null。
                        sink.next(letter); // 通过不调用sink.next来过滤掉它。
                });

        alphabet.subscribe(System.out::println);
    }

}
