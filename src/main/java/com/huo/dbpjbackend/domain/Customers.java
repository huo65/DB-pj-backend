package com.huo.dbpjbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 客户表
 * @TableName customers
 */
@TableName(value ="customers")
@Data
public class Customers implements Serializable {
    /**
     * 客户名
     */
    @TableId
    private String custname;

    /**
     * 客户ID
     */
    private Integer custid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}