package com.huo.dbpjbackend.mapper;

import com.huo.dbpjbackend.domain.Flights;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
* @author 86132
* @description 针对表【flights(航班信息)】的数据库操作Mapper
* @createDate 2023-12-01 12:13:42
* @Entity generator.domain.Flights
*/
@Mapper
public interface FlightsMapper extends BaseMapper<Flights> {
    @Update(" UPDATE flights\n" +
            "        SET numAvail = numAvail - 1\n" +
            "        WHERE flightNum = #{model}")
    void addReservation(@Param("model") int model);

    @Select(" SELECT numAvail FROM flights WHERE flightNum = #{model}")
    int getAvailable(int model);

}




