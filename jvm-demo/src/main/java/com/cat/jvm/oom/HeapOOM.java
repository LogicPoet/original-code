package com.cat.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 将堆的最小值-Xms参数与最大值-Xmx参数设置为一样即可避免堆自动扩展
 * VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author LZ
 * @date 2020/6/22 18:07
 **/
public class HeapOOM {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true){
            list.add(new OOMObject());
        }
    }

}
