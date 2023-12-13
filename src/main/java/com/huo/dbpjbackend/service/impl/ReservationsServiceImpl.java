package com.huo.dbpjbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huo.dbpjbackend.domain.Reservations;
import com.huo.dbpjbackend.service.ReservationsService;
import com.huo.dbpjbackend.mapper.ReservationsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86132
* @description 针对表【reservations(预定表)】的数据库操作Service实现
* @createDate 2023-12-01 12:14:16
*/
@Service
public class ReservationsServiceImpl extends ServiceImpl<ReservationsMapper, Reservations>
    implements ReservationsService{

}




