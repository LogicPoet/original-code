package cn.catguid.dynamic;

import cn.catguid.dynamic.entity.User1;
import cn.catguid.dynamic.entity.User2;
import cn.catguid.dynamic.mapper.db1.UserMapper1;
import cn.catguid.dynamic.mapper.db2.UserMapper2;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ApplicationTest {

    @Autowired
    UserMapper1 userMapper1;

    @Autowired
    UserMapper2 userMapper2;

    @Test
    public void main() {
        List<User1> user1s = userMapper1.selectAll();
        log.debug("db1结果：{}",user1s);
        //userMapper2.selectList(Wrappers.emptyWrapper());
        List<User2> user2s = userMapper2.selectAll();
        log.debug("db1结果：{}",user1s);
        System.out.println(user1s.size());
        System.out.println(user2s.size());
    }

    @Test
    public void main2() {
        List<User1> user1s = userMapper1.selectList(Wrappers.emptyWrapper());
        List<User2> user2s = userMapper2.selectList(Wrappers.emptyWrapper());

        System.out.println("db1结果："+user1s.size());
        System.out.println("db2结果："+user2s.size());
    }
}
