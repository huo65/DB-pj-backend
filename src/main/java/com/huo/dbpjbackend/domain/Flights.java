package com.huo.dbpjbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 航班信息
 * @TableName flights
 */
@TableName(value ="flights")
@Data
public class Flights implements Serializable {
    /**
     * 号
     */
    @TableId(type = IdType.AUTO)
    private Integer flightnum;

    /**
     * 价
     */
    private Integer price;

    /**
     * 总坐
     */
    private Integer numseats;

    /**
     * 可坐
     */
    private Integer numavail;

    /**
     * from
     */
    private String fromcity;

    /**
     * 目的地
     */
    private String arivcity;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}