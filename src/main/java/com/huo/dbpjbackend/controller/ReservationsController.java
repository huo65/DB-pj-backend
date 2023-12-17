package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Reservations;
import com.huo.dbpjbackend.service.ReservationsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 1. @ClassName ReservationsController
 * 2. @Description 预定表
 * 3. @Author huo
 * 4. @Date 2023/12/6 22:47
 */
@RestController
@RequestMapping("/reservation")
@Slf4j
@Api(tags = "reservation相关接口")
public class ReservationsController {
    
    @Autowired
    ReservationsService reservationsService;

//    private static final int FLIGHT_BOOKING = 1;
//    private static final int HOTEL_BOOKING = 2;
//    private static final int BUS_BOOKING = 3;
//    Integer resvtype = reservations.getResvtype();
//        switch (resvtype){
//        case FLIGHT_BOOKING:
//
//    }
    /**
     * 新增预定
     */
    @PostMapping
    public Result<String> save(@RequestBody Reservations bus) {
        log.info("bus:{}",bus);
        reservationsService.save(bus);
        return Result.success("添加预定成功");
    }
    /**
     * 预定分页查询
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        Page<Reservations> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Reservations> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Reservations::getCustname);
        reservationsService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }


    /**
     * 根据id删除预定
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("key") String key){
        LambdaQueryWrapper<Reservations> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservations::getResvkey,key);
        reservationsService.remove(queryWrapper);
        return Result.success("取消预定成功");
    }


}
