package com.huo.dbpjbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huo.dbpjbackend.domain.Flights;
import com.huo.dbpjbackend.service.FlightsService;
import com.huo.dbpjbackend.mapper.FlightsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86132
* @description 针对表【flights(航班信息)】的数据库操作Service实现
* @createDate 2023-12-01 12:13:42
*/
@Service
public class FlightsServiceImpl extends ServiceImpl<FlightsMapper, Flights>
    implements FlightsService {

}




