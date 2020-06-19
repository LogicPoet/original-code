package com.cat.es.repository;

import com.cat.es.model.UserES;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserESRepositoryTest {

    @Autowired
    UserESRepository repo;

    /**
     * 测试普通查询，按生日倒序
     */
    @Test
    public void queryBuilder() {
        MatchQueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "云白");

        log.info("【queryBuilder】= {}", queryBuilder.toString());

        repo.search(queryBuilder).forEach(person -> log.info("【person】= {}", person));
    }

    @Test
    public void select2() {
        List<UserES> userES = repo.getByName("云白");
        log.info("查询结果：{}",userES);
    }

    @Test
    public void termQueryBuilder() {

        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("name", "云白");
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", "云白");
        log.info("【user+term】= {}", termQueryBuilder.toString());
        log.info("【user+match】= {}", matchQueryBuilder.toString());

        repo.search(termQueryBuilder).forEach(person -> log.info("【user】= {}", person));

    }

    @Test
    public void getByName() {
        List<UserES> userES = repo.getByName("云白");
        log.info("查询结果：{}",userES);
    }

    @Test
    public void readByName() {
        List<UserES> userES = repo.readByName("云白");
        log.info("查询结果：{}",userES);
    }

    @Test
    public void queryByName() {
        List<UserES> userES = repo.queryByName("云白");
        log.info("查询结果：{}",userES);
    }

    @Test
    public void selectAllByName() {
        TermQueryBuilder tenantId = QueryBuilders.termQuery("tenantId", "000001");
        Iterable<UserES> search = repo.search(tenantId);
        List<UserES> userES =new ArrayList<>();
        search.forEach(e->{
            UserES u = new UserES();
            BeanUtils.copyProperties(e,u);
            userES.add(u);
        });
        log.info("查询结果：{}",userES.size());
    }


}