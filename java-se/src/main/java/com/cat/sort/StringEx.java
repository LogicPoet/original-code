package com.cat.sort;

/**
 * @author liu.zhi
 * @date 2020/10/21 16:23
 */
public class StringEx {

    String str = new String("good");
    String str2 = "good";
    char[] ch = {'t','e','s','t'};

    public void change(String str,char ch[]){
        System.out.println("change里面赋值之前str的引用" + str.hashCode());
        System.out.println("change里面赋值之前ch的引用" + ch.hashCode());
        str = "test ok";
        ch[0] = 'b';
        System.out.println("change里面赋值之后str的引用" + str.hashCode());
        System.out.println("change里面赋值之后ch的引用" + ch.hashCode());
    }

    public static void main(String[] args) {
        //StringEx ex = new StringEx();
        //String str = "good";
        ////System.out.println(ex.str2 == str);
        //System.out.println("main里面change之前str的引用" + str.hashCode());
        //System.out.println("main里面change之前ch的引用" + ex.ch.hashCode());
        //ex.change(ex.str2, ex.ch);
        //
        //System.out.println("main里面change之后str的引用" + str.hashCode());
        //System.out.println("main里面change之后ch的引用" + ex.ch.hashCode());
        //System.out.println(ex.str2);
        //System.out.println(ex.ch);
        String str1 = "good";
        String str2 = new String("good");

        System.out.println(str1 == str2);
    }

}
