package com.sc.producer.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
@Data
使用这个注解，就不用再去手写Getter,Setter,equals,canEqual,hasCode,toString等方法了，注解后在编译时会自动加进去。
@AllArgsConstructor
使用后添加一个构造函数，该构造函数含有所有已声明字段属性参数
@NoArgsConstructor
使用后创建一个无参构造函数
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     *  AUTO, 自增
     *  NONE,
     *  INPUT,
     *  ASSIGN_ID,
     *  ASSIGN_UUID, String UUID
     *  ID_WORKER  Long 雪花算法
     *  ID_WORKER_STR String 雪花算法
     *  UUID String UUID
     */
    @TableId(type = IdType.AUTO)
    private int id;

    private String name;

    private int age;

    private String email;

    /**
     * 自动填充
     */
    @TableField(fill = FieldFill.INSERT)
    private Timestamp createTime;


    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Timestamp updateTime;

}
