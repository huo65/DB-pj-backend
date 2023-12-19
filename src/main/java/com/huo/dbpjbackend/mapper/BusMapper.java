package com.huo.dbpjbackend.mapper;

import com.huo.dbpjbackend.domain.Bus;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;


/**
* @author 86132
* @description 针对表【bus(大巴表)】的数据库操作Mapper
* @createDate 2023-12-01 12:13:52
* @Entity generator.domain.Bus
*/
@Mapper
public interface BusMapper extends BaseMapper<Bus> {

    @Select(" SELECT numavail FROM bus WHERE location = #{model}")
    int getAvailable(String model);
    @Update(" UPDATE bus\n" +
            "        SET numavail = numavail - 1\n" +
            "        WHERE location = #{model}")
    void addReservation(String model);
}




