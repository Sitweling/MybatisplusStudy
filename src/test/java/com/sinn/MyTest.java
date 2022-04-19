package com.sinn;

import com.sinn.mapper.UserMapper;
import com.sinn.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@SpringBootTest

public class MyTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        /*  控制台log打印
        *  INSERT INTO user ( id, name, age, email ) VALUES ( ?, ?, ?, ? )
        * */
        User user = new User();
        user.setAge(23);
        user.setName("张三");
        user.setAge(23);
        user.setEmail("zhangsan@gmail.com");
        int result = userMapper.insert(user);
        System.out.println("result"+result);
        System.out.println(user);
    }

    @Test
    public void testDelete(){
        /*==>  Preparing: DELETE FROM user WHERE id=?*/
        int result = userMapper.deleteById(1515525424933642241L);
        System.out.println("result:"+result);
    }

    @Test
    public void testDeleteMap(){
        /*==>  Preparing: DELETE FROM user WHERE name = ? AND age = ?*/
        Map<String,Object> map=new HashMap<>();
        map.put("name","张三");
        map.put("age",23);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);
    }

    @Test
    public void testDeleteBatchids(){
        /*DELETE FROM user WHERE id IN (?,?,?)*/
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        int deleteBatchIds = userMapper.deleteBatchIds(list);
        System.out.println(deleteBatchIds);
    }

    @Test
    public void testUpdate(){

        //没设置的Age属性，并不会添加到update中（不会更新没设置的数据）
        /*==>  Preparing: UPDATE user SET name=?, email=? WHERE id=?
          ==> Parameters: 李四(String), lisi@qq.com(String), 4(Long)
            <==    Updates: 1*/
        User user = new User();
        user.setId(4L);
        user.setName("李四");
        user.setEmail("lisi@qq.com");
        int updateById = userMapper.updateById(user);
        System.out.println(updateById);
    }

    @Test
    public void testSelect(){
        /*
        * ==>  Preparing: SELECT id,name,age,email FROM user WHERE id=?
            ==> Parameters: 1(Long)
            <==    Columns: id, name, age, email
            <==        Row: 1, Jone, 18, test1@baomidou.com
            <==      Total: 1
        * */
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    @Test
    public void testSelectIDs(){
        /*  和delete一样，用IN
            ==>  Preparing: SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
            ==> Parameters: 1(Long), 2(Long), 3(Long)
        * */
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        List<User> userList = userMapper.selectBatchIds(list);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectMap(){
        /*
        * ==>  Preparing: SELECT id,name,age,email FROM user WHERE name = ? AND age = ?
            ==> Parameters: Jack(String), 20(Integer)
        * */
        Map<String,Object> map=new HashMap<>();
        map.put("name","Jack");
        map.put("age",20);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectList(){
        /*查询全部的数据
        * 传入null的查询条件
        * ==>  Preparing: SELECT id,name,age,email FROM user
        * */
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testSelectMapByID(){
        /*自定义方法
        *==>  Preparing: select id, name, age, email from user where id =?
        ==> Parameters: 1(Long)
        <==    Columns: id, name, age, email
        <==        Row: 1, Jone, 18, test1@baomidou.com
        * */
        Map<String, Object> map = userMapper.selectMapById(1L);
        System.out.println(map);
    }
}
