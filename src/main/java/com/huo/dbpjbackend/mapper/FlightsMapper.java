package com.huo.dbpjbackend.mapper;

import com.huo.dbpjbackend.domain.Flights;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86132
* @description 针对表【flights(航班信息)】的数据库操作Mapper
* @createDate 2023-12-01 12:13:42
* @Entity generator.domain.Flights
*/
@Mapper
public interface FlightsMapper extends BaseMapper<Flights> {

}




