package com.huo.dbpjbackend.service;

import com.huo.dbpjbackend.domain.Hotels;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86132
* @description 针对表【hotels(DB_PJ.`HOTELS`)】的数据库操作Service
* @createDate 2023-12-01 12:14:09
*/
public interface HotelsService extends IService<Hotels> {

    int getAvailable(String model);

    void resv(String model);
}
