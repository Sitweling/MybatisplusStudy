package com.sinn;

import com.sinn.pojo.User;
import com.sinn.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@SpringBootTest
public class MyServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetCount(){
        /*  查询总记录数
        *   ==>  Preparing: SELECT COUNT( * ) FROM user
            ==> Parameters:
            <==    Columns: COUNT( * )
            <==        Row: 5
            <==      Total: 1
        * */
        long count = userService.count();
        System.out.println(count);
    }

    @Test
    public void testSaveMore(){
        /*  添加多条数据
        *   ==>  Preparing: INSERT INTO user ( id, name, age ) VALUES ( ?, ?, ? )
        * 单次添加，执行10次，实现批量添加
        * 因为BaseMapper中没有提供批量添加功能，所以通用Service中是对BaseMapper中的单词添加执行循环
        * */
        List<User> list=new ArrayList<>();
        for (int i = 1; i <=10; i++) {
            User user = new User();
            user.setName("Sinn"+i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }

}
