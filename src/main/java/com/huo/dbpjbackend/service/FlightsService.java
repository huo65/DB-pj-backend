package com.huo.dbpjbackend.service;

import com.huo.dbpjbackend.domain.Flights;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86132
* @description 针对表【flights(航班信息)】的数据库操作Service
* @createDate 2023-12-01 12:13:42
*/
public interface FlightsService extends IService<Flights> {

    void resv(int model);

    int getAvailable(int model);
}

