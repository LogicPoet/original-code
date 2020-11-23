package cn.catguild.reactor;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

/**
 *
 * 自定义的 Subscriber，默认它会触发一个无限制的请求，并且行为与subscribe()完全相同，
 * 但是需要自定义请求量是，拓展功能会更加有用。
 *
 * 对于自定义请求量是，最起码要实现hookOnSubscribe、hookOnNext
 *
 * 继承了BaseSubscriber类型，这个类提供了钩子函数，可以调整订阅者的行为
 *
 *
 * @author LZ
 * @date 2020/9/11 13:46
 **/
public class SampleSubscriber<T> extends BaseSubscriber<T> {

    /**
     * Hook for further processing of onSubscribe's Subscription. Implement this method
     * to call {@link #request(long)} as an initial request. Values other than the
     * unbounded {@code Long.MAX_VALUE} imply that you'll also call request in
     * {@link #hookOnNext(Object)}.
     * <p> Defaults to request unbounded Long.MAX_VALUE as in {@link #requestUnbounded()}
     *
     * @param subscription the subscription to optionally process
     */
    @Override
    protected void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribed");
        request(1);
    }

    /**
     * Hook for processing of onNext values. You can call {@link #request(long)} here
     * to further request data from the source {@link org.reactivestreams.Publisher} if
     * the {@link #hookOnSubscribe(Subscription) initial request} wasn't unbounded.
     * <p>Defaults to doing nothing.
     *
     * @param value the emitted value to process
     */
    @Override
    protected void hookOnNext(T value) {
        System.out.println(value);
        request(1);
    }
}
