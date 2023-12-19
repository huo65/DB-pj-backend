package com.huo.dbpjbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huo.dbpjbackend.domain.Bus;
import com.huo.dbpjbackend.service.BusService;
import com.huo.dbpjbackend.mapper.BusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 86132
* @description 针对表【bus(大巴表)】的数据库操作Service实现
* @createDate 2023-12-01 12:13:52
*/
@Service
public class BusServiceImpl extends ServiceImpl<BusMapper, Bus>
    implements BusService{

    @Autowired
    BusMapper busMapper;

    @Override
    public int getAvailable(String model) {
        return busMapper.getAvailable(model);
    }

    @Override
    public void resv(String model) {
        busMapper.addReservation(model);
    }
}




