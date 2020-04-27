package com.cat.annotation.check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author LZ
 * @date 2020/4/26 14:59
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @CatCheck(msg = "姓名填写格式错误",min = 2,max = 10)
    private String name;

    @CatCheck(msg = "年龄填写格式错误",condition = ">1000")
    private Integer age;

    private LocalDate birth;

}
