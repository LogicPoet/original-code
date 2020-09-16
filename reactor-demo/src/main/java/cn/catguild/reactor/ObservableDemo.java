package cn.catguild.reactor;

import java.util.Observable;

/**
 * @author liu.zhi
 * @date 2020/9/16 18:19
 */
public class ObservableDemo extends Observable {

    public static void main(String[] args) {
        ObservableDemo observable = new ObservableDemo();

        observable.addObserver((o,a)->System.out.println("数据发送了变化"+a+o));
        observable.addObserver((o,b)->System.out.println("观察者发送了变化"+b+o));

        observable.setChanged();

        observable.notifyObservers();
    }

}
