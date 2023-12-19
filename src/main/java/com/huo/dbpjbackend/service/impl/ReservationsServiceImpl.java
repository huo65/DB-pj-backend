package com.huo.dbpjbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huo.dbpjbackend.common.CustomException;
import com.huo.dbpjbackend.domain.Reservations;
import com.huo.dbpjbackend.service.BusService;
import com.huo.dbpjbackend.service.FlightsService;
import com.huo.dbpjbackend.service.HotelsService;
import com.huo.dbpjbackend.service.ReservationsService;
import com.huo.dbpjbackend.mapper.ReservationsMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 86132
* @description 针对表【reservations(预定表)】的数据库操作Service实现
* @createDate 2023-12-01 12:14:16
*/
@Service
@Slf4j
public class ReservationsServiceImpl extends ServiceImpl<ReservationsMapper, Reservations>
    implements ReservationsService{
    private static final int FLIGHT_BOOKING = 1;
    private static final int HOTEL_BOOKING = 2;
    private static final int BUS_BOOKING = 3;

    @Autowired
    FlightsService flightsService;
    @Autowired
    HotelsService hotelsService;
    @Autowired
    BusService busService;

    @Override
    public void addReservation(Reservations reservations) {
        Integer resvtype = reservations.getResvtype();
        String model = reservations.getModel();
        switch (resvtype){
            case FLIGHT_BOOKING:
                int anInt = Integer.parseInt(model);
                if (flightsService.getAvailable(anInt) <= 0) throw new CustomException("剩余数量不足");
                flightsService.resv(anInt);
                break;
            case HOTEL_BOOKING:
                if (hotelsService.getAvailable(model) <= 0) throw new CustomException("剩余数量不足");
                hotelsService.resv(model);
                break;
            case BUS_BOOKING:
                if (busService.getAvailable(model) <= 0) throw new CustomException("剩余数量不足");
                busService.resv(model);
        }
        this.save(reservations);

    }
}




