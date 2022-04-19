package com.sinn.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinn.mapper.UserMapper;
import com.sinn.pojo.User;
import com.sinn.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
/*
* 说明一下为什么需要继承&实现接口
* 首先是实现接口 public class UserServiceImpl implements UserService，这是常规操作
* 然后UserService是MybatisPlus提供的通用Service，有大量方法需要实现，单纯实现接口需要实现很多方法
* 我们不需要重写全部的方法，所以我们继承MybatisPlus继承UserService的实现类ServiceImpl<Mapper,pojo类>即可！
* */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
