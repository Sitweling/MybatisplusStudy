package com.sinn;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public class MyPluginsTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void test01(){

        /*
        * ==>  Preparing: SELECT COUNT(*) AS total FROM user WHERE is_delete = 0
        *
        * ==>  Preparing: SELECT id,name,age,email,is_delete FROM user WHERE is_delete=0 LIMIT ?,?
            ==> Parameters: 3(Long), 3(Long)
        * */
        //limit cur , size
        Page<User> page=new Page<>(2,3);
        userMapper.selectPage(page,null);
        //查询出来的结果放在了page对象中
//        System.out.println(page);
        //getPages获取总页数
        System.out.println("getPages： "+page.getPages());
        //getTotal数据总条数
        System.out.println("getTotal： "+page.getTotal());
        //getRecords获取记录
        System.out.println("getRecords： "+page.getRecords());
    }

    @Test
    public void TestPageVo(){
        Page<User> page=new Page<>(1,3);
        userMapper.selectPageVo(page,20);
        //getRecords获取记录
        System.out.println("getRecords： "+page.getRecords());
    }
}
