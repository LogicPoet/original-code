package com.cat.repository;

import com.cat.domain.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: UserRepository</p>
 * <p>description: </p>
 * <p>Company: </p>
 *
 * @author LZ
 * @date 2020/4/11 16:34
 **/
@Repository
public interface UserRepository extends ReactiveMongoRepository<User,String> {
    //description: ReactiveMongoRepository<表名User,id类型String>
}
