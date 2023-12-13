package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Flights;
import com.huo.dbpjbackend.service.FlightsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 1. @ClassName FlightsController
 * 2. @Description TODO
 * 3. @Author huo
 * 4. @Date 2023/12/6 22:46
 */

@RestController
@RequestMapping("/flights")
@Slf4j
@Api(tags = "flight相关接口")
public class FlightsController {
    @Autowired
    private FlightsService flightsService;

    /**
     * 新增飞机
     */
    @PostMapping
    public Result<String> save(@RequestBody Flights flights) {
        log.info("flights:{}",flights);
        flightsService.save(flights);
        return Result.success("添加飞机成功");
    }
    /**
     * 飞机类分页
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        Page<Flights> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Flights> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Flights::getNumavail);
        flightsService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
    /**
     * 根据id删除飞机
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("Flightnum") Integer flightnum){
        LambdaQueryWrapper<Flights> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Flights::getFlightnum,flightnum);
        flightsService.remove(queryWrapper);
        return Result.success("删除飞机成功");
    }

    /**
     * 根据id修改飞机
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Flights flights){
        flightsService.updateById(flights);
        return Result.success("修改飞机信息成功");
    }
}
