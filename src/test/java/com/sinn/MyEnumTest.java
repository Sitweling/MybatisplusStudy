package com.sinn;

import com.sinn.enums.SexEnum;
import com.sinn.mapper.UserMapper;
import com.sinn.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/19
 */
@SpringBootTest
public class MyEnumTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        User user = new User();
        user.setName("admin");
        user.setAge(33);
        user.setSex(SexEnum.MALE);
        int insert = userMapper.insert(user);
        System.out.println(insert);
    }
}
