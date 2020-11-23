package cn.catguild.reactor.official.createseq;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Asynchronous but single-threaded: push
 *
 * 推送是生成和创建之间的中间立场，适用于处理单个生产者的事件.
 * 它与create类似，因为它也可以是异步的，并且可以使用create支持的任何溢出策略来管理背压.
 * 但是，一次只有一个生产线程可以一次调用下一个，完成或错误。
 *
 * @author LZ
 * @date 2020/9/11 15:26
 **/
public class asyncSingleThreadTest {

    static class EventProcessor {
        public void register(SingleThreadEventListener<String> myEventListener) {
            List<String> a = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                a.add(String.valueOf(i));
            }
            myEventListener.onDataChunk(a);
            myEventListener.processComplete();
        }
    }

    static interface SingleThreadEventListener<T> {
        public void onDataChunk(List<T> chunk);

        public void processComplete();

        public void processError(Throwable e);
    }

    @Test
    public void pushTest(){
        EventProcessor myEventProcessor = new EventProcessor();

        Flux.push(sink->{
            myEventProcessor.register(
                    new SingleThreadEventListener<String>(){ //桥接到SingleThreadEventListener API。

                        @Override
                        public void onDataChunk(List<String> chunk) {
                            for (String s : chunk) {
                                sink.next(s); //使用单个侦听器线程中的next将事件推送到接收器
                            }
                        }

                        @Override
                        public void processComplete() {
                            sink.complete();//从同一侦听器线程生成的complete事件。
                        }

                        @Override
                        public void processError(Throwable e){
                            sink.error(e);//error事件也从同一侦听器线程生成。
                        }
                    }
            );
        });
    }

}