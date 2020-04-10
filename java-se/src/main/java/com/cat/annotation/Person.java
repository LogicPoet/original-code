package com.cat.annotation;

/**
 * <p>Title: Person</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LogicPoet
 * @version V1.0
 * @date 2019/9/21 16:39
 **/

public class Person {
    private static Person person;

    private Person(){

    }

    public static Person getInstance(){
        if (person==null){
            synchronized (Person.class){
                if (person==null){
                    return person=new Person();
                }
            }
        }
        return person;
    }
}
