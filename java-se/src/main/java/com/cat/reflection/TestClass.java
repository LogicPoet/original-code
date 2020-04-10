package com.cat.reflection;

/**
 * <p>Title: TestClass</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/21 9:09
 **/
public class TestClass {
    private String msg="Original";

    /**
     * String 会被 JVM 优化
     */
    private final String FINAL_VALUE;


    public TestClass() {
        this.FINAL_VALUE = "FINAL";
    }

    public String getMsg(){
        return msg;
    }

    public String getFinalValue(){
        //剧透，会被优化为: return "FINAL" ,拭目以待吧
        return FINAL_VALUE;
    }

    private void privateMethod(String head , int tail){
        System.out.print(head + tail);
    }
}
