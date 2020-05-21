package com.cat;

import java.io.*;
import java.util.Base64;

/**
 * @author LZ
 * @date 2020/5/11 16:28
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        Base64.Encoder encoder = Base64.getEncoder();
        //D:\yuying\16k.pcm
        String s;
        try (InputStream input = new FileInputStream("D:\\yuying\\16k.pcm")) {
            int n;
            StringBuilder sb = new StringBuilder();
            while ((n = input.read()) != -1) {
                sb.append(n);
            }
            s = sb.toString();
        }
        //System.out.println(s);
        // 编译
        String s1 = encoder.encodeToString(s.getBytes());
        System.out.println(s.getBytes().length);
        System.out.println();
        System.out.println(s1);
    }
}
