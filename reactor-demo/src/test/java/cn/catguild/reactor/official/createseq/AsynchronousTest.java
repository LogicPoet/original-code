package cn.catguild.reactor.official.createseq;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * 异步和多线程：create
 * <p>
 * create是Flux的程序化创建的更高级形式，它适合于每轮多次排放，甚至来自多个线程。
 * <p>
 * ps：create不会并行化您的代码，也不会使其异步，即使它可以与异步API一起使用。
 *
 * @author LZ
 * @date 2020/9/11 15:15
 **/
public class AsynchronousTest {
    static class EventProcessor {
        public void register(MyEventListener<String> myEventListener) {
            List<String> a = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                a.add(String.valueOf(i));
            }
            myEventListener.onDataChunk(a);
            myEventListener.processComplete();
        }
    }

    static interface MyEventListener<T> {
        public void onDataChunk(List<T> chunk);

        public void processComplete();
    }

    /**
     * 使用create将其桥接到Flux <T>中
     */
    @Test
    public void createTest() {
        EventProcessor myEventProcessor = new EventProcessor();
        Flux<String> bridge = Flux.create(sink ->
            myEventProcessor.register( // 每当myEventProcessor执行时，所有这些操作都是异步完成的。
                    new MyEventListener<String>() { // 桥接到MyEventListener API

                        @Override
                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s); // 块中的每个元素都将成为Flux中的一个元素。
                            }
                        }

                        @Override
                        public void processComplete() {
                            sink.complete(); // processComplete事件被转换为onComplete。
                        }
                    }
            )
        );
    }

}
