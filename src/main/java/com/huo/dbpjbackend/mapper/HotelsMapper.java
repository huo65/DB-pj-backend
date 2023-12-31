package com.huo.dbpjbackend.mapper;

import com.huo.dbpjbackend.domain.Hotels;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author 86132
* @description 针对表【hotels(DB_PJ.`HOTELS`)】的数据库操作Mapper
* @createDate 2023-12-01 12:14:09
* @Entity generator.domain.Hotels
*/
@Mapper
public interface HotelsMapper extends BaseMapper<Hotels> {

    @Select(" SELECT numavail FROM hotels WHERE location = #{model}")
    int getAvailable(String model);

    @Update(" UPDATE hotels\n" +
            "        SET numavail = numavail - 1\n" +
            "        WHERE location = #{model}")
    void addReservation(String model);
}




