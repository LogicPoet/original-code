package com.cat.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * <p>Title: User</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LZ
 * @date 2020/4/11 16:31
 **/
@Document(collection = "user")
@Data
public class User {

    @Id
    private String id;

    private String name;

    private int age;

}
