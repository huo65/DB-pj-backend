package com.huo.dbpjbackend.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * DB_PJ.`HOTELS`
 * @TableName hotels
 */
@TableName(value ="hotels")
@Data
public class Hotels implements Serializable {
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
     * 房间号
     */
    private Integer numrooms;

    /**
     * 剩余房间
     */
    private Integer numavail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}