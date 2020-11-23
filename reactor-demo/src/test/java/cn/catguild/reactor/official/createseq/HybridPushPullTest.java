package cn.catguild.reactor.official.createseq;

import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LZ
 * @date 2020/9/11 15:49
 **/
public class HybridPushPullTest {

    static class EventProcessor {
        public void register(MyMessageListener<String> myEventListener) {
            List<String> a = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                a.add(String.valueOf(i));
            }
            myEventListener.onMessage(a);
        }

        public List<String> getHistory(long n) {
            return new ArrayList<>();
        }
    }

    static interface MyMessageListener<T> {
        public void onMessage(List<T> messages);
    }

    @Test
    public void hybrid() {

        EventProcessor myMessageProcessor = new EventProcessor();

        Flux<String> bridge = Flux.create(sink -> {
            myMessageProcessor.register(
                    new MyMessageListener<String>() {
                        public void onMessage(List<String> messages) {
                            for (String s : messages) {
                                sink.next(s);//其余异步到达的消息也将被传递。
                            }
                        }
                    });
            sink.onRequest(n -> {
                List<String> messages = myMessageProcessor.getHistory(n);//发出请求时轮询消息。
                for (String s : messages) {
                    sink.next(s);//	如果消息立即可用，请将其推入接收器。
                }
            });
        });
    }

    @Test
    public void cancel() {
        //Flux<String> bridge = Flux.create(sink -> {
        //    sink.onRequest(n -> channel.poll(n))
        //            .onCancel(() -> channel.cancel())
        //            .onDispose(() -> channel.close());
        //});
    }
}
