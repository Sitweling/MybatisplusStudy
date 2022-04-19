package com.sinn;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.sinn.mapper.UserMapper;
import com.sinn.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/19
 */
@SpringBootTest
public class MyWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void selectTest(){
        //查询用户名包含a，年龄在20~30之间，邮箱信息不为null的用户信息
        /*
        * ==>  Preparing: SELECT id,name,age,email,is_delete FROM user WHERE is_delete=0 AND
        * (name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
            ==> Parameters: %a%(String), 20(Integer), 30(Integer)
            <==    Columns: id, name, age, email, is_delete
            <==        Row: 2, Jack, 20, test2@baomidou.com, 0
            <==      Total: 1
        * */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","a")
                        .between("age",20,30)
                                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void test02(){
        //查询用户信息，按照年龄降序排序，年龄相同按照ID升序排序
        /*
        * Preparing: SELECT id,name,age,email,is_delete FROM user WHERE is_delete=0 ORDER BY age DESC,id ASC
        * */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                        .orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    @Test
    public void Test03(){
        //删除邮箱为null的用户信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("email");
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);
    }

    @Test
    public void test04(){
        //修改age>20和name中包含a，或者email为空的用户
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",20)
                .like("name","a")
                .or()
                .isNull("email");
        //此处创建的User是修改以后的对象
        User user=new User();
        user.setName("新用户1");
        user.setEmail("AtaLaShi@Jp.com");
        userMapper.update(user,queryWrapper);
    }

    @Test
    public void test05(){
        //将用户名中包含a并且(年龄>20或邮箱为null)的用户信息修改
        //lambda中的条件会优先执行
        /*
        * ==>  Preparing: UPDATE user SET name=?, email=? WHERE is_delete=0 AND (name LIKE ? AND (age > ? OR email IS NULL))
        * */
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("name","a")
                .and(i->i.gt("age",20)
                        .or()
                        .isNull("email"));
        User user = new User();
        user.setName("新用户2");
        user.setEmail("test@qq.com");
        userMapper.update(user,queryWrapper);
    }

    @Test
    public void test06(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("age","name","email");
        List<Map<String, Object>> selectMaps = userMapper.selectMaps(queryWrapper);
        selectMaps.forEach(System.out::println);
    }

    @Test
    public void test07(){
        //查询id小于等于100的用户信息
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql("id","select id form user where id<=100");
        /* select * from user
        * where id IN (select id form user where id<=100)
        * 强行实现一个子查询。。。
        * */
    }

    @Test
    public void updateTest2(){
        //将用户名中包含a并且(年龄>20或邮箱为null)的用户信息修改
        LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName,"a")
                .and(i->i.gt(User::getAge,20)
                        .or()
                        .isNull(User::getEmail));
        updateWrapper.set(User::getName,"小黑")
                .set(User::getEmail,"updateTest@qq.com");
        int update = userMapper.update(null, updateWrapper);
        System.out.println(update);
    }

    @Test
    public void Test09(){
        String username="";
        Integer ageBegin=20;
        Integer ageEnd=20;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("name",username);
        }
        if(ageBegin!=null){
            queryWrapper.ge("age",ageBegin);
        }
        if(ageEnd!=null){
            queryWrapper.le("age",ageEnd);
        }
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    //使用condition组装条件
    @Test
    public void test10(){
        String username="";
        Integer ageBegin=20;
        Integer ageEnd=20;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"username",username)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    //使用lambda表达式
    @Test
    public void test11(){
        String username="";
        Integer ageBegin=20;
        Integer ageEnd=20;
        LambdaQueryWrapper<User> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin!=null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        List<Map<String, Object>> list = userMapper.selectMaps(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void updateTest(){
        //将用户名中包含a并且(年龄>20或邮箱为null)的用户信息修改
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.like("name","a")
                .and(i->i.gt("age",20)
                        .or()
                        .isNull("email"));
        updateWrapper.set("name","小黑")
                .set("email","updateTest@qq.com");
        int update = userMapper.update(null, updateWrapper);
        System.out.println(update);
    }
}
