package com.huo.dbpjbackend.service;

import com.huo.dbpjbackend.domain.Reservations;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86132
* @description 针对表【reservations(预定表)】的数据库操作Service
* @createDate 2023-12-01 12:14:16
*/
public interface ReservationsService extends IService<Reservations> {

    void addReservation(Reservations reservations);
}
