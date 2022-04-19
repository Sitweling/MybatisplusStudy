package com.sinn.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sinn.enums.SexEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: Sitweling
 * @CreateTime: 2022/4/17
 */
@Data
//设置实体类所对应的表名
@TableName("user")
public class User {

    /*@TableId(value="id")
    * 将属性对应的字段指定为主键
    * Value属性用于指定主键的字段
    * */
    private Long id;

    /*
    * @TableField(value = "user_name")
    * 设置表中对应的名字
    *     */
    private String name;

    private Integer age;

    private String email;

    /*
    * 将物理删除改成逻辑删除
    * */
    @TableLogic
    private Integer isDelete;

    private SexEnum sex;
}
