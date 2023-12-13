package com.huo.dbpjbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 大巴表
 * @TableName bus
 */
@TableName(value ="bus")
@Data
public class Bus implements Serializable {
    /**
     * 位置
     */
    @TableId
    private String location;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 总坐
     */
    private Integer numbus;

    /**
     * 可坐
     */
    private Integer numavail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}