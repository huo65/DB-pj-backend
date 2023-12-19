package com.huo.dbpjbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 预定表
 * @TableName reservations
 */
@TableName(value ="reservations")
@Data
public class Reservations implements Serializable {
    /**
     * 主键
     */
    @TableId
    private int resvkey;

    /**
     * 客户名
     */
    private String custname;

    /**
     * 预定类型
     */
    private Integer resvtype;
    /**
     * 预订型号
     */
    private String model;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}