package com.sinn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sinn.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@Repository
public interface UserMapper extends BaseMapper<User>  {


    /**
     * 根据id查询用户信息为Map集合
     * @param id
     * @return
     */
    Map<String,Object> selectMapById(Long id);

    /**
     * 自定义方法，根据年龄查询用户信息并且分页
     * @param page Mybatis-Plus提供的分页对象，必须放在第一个参数的位置
     * @param age
     * @return Page
     */
    Page<User> selectPageVo(@Param("page") Page<User> page,@Param("age") Integer age);
}
