package collection.arraylist;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: DynamicProxyDemo</p>
 * <p>description: 一个ArrayList的动态代理类</p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/16 20:43
 **/
public class DynamicProxyDemo {
    public static void main(String[] args) {
        final List<String> list = new ArrayList<String>();

        List<String> proxyInstance = (List<String>) Proxy.newProxyInstance(list.getClass().getClassLoader(), list.getClass().getInterfaces(), new InvocationHandler() {
                            @Override
                            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                                return method.invoke(list, args);
                            }
                        });
        proxyInstance.add("你好");
        System.out.println(list);

    }


}
