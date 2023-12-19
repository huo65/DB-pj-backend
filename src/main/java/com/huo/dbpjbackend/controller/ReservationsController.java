package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.CustomException;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Bus;
import com.huo.dbpjbackend.domain.Flights;
import com.huo.dbpjbackend.domain.Hotels;
import com.huo.dbpjbackend.domain.Reservations;
import com.huo.dbpjbackend.mapper.BusMapper;
import com.huo.dbpjbackend.mapper.FlightsMapper;
import com.huo.dbpjbackend.mapper.HotelsMapper;
import com.huo.dbpjbackend.service.ReservationsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 1. @ClassName ReservationsController
 * 2. @Description 预定表
 * 3. @Author huo
 * 4. @Date 2023/12/6 22:47
 */
@RestController
@RequestMapping("/reservations")
@Slf4j
@Api(tags = "reservation相关接口")
public class ReservationsController {
    
    @Autowired
    ReservationsService reservationsService;

    @Autowired
    HotelsMapper hotelsService;

    @Autowired
    BusMapper busService;


    @Autowired
    FlightsMapper flightsMapper;


    /**
     * 新增预定
     */
    @PostMapping
    public Result<String> save(@RequestBody Reservations reservations) {
        log.info("reservations:{}",reservations);
        reservationsService.addReservation(reservations);
        return Result.success("添加预定成功");
    }
    /**
     * 预定分页查询
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize, int type, String name){
        Page<Reservations> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Reservations> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(type!=0,Reservations::getResvtype,type);
        queryWrapper.eq(Strings.isNotBlank(name),Reservations::getCustname,name);
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
    /**
     * 查询某个客户的旅行路线
     */
    @GetMapping("/checkUserRoute")
    public Result<Map<String, Object>> check(String custName){
        LambdaQueryWrapper<Reservations> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservations::getResvtype,1);
        if(Strings.isBlank(custName)){
            throw new CustomException("请先登录");
        }
        queryWrapper.eq(Reservations::getCustname,custName);
        List<Reservations> list = reservationsService.list(queryWrapper);
        List<String> modelValues = list.stream()
                .map(Reservations::getModel)
                .collect(Collectors.toList());

        List<Flights> flights = flightsMapper.selectBatchIds(modelValues);
        log.info("flights:"+flights);

        List<String> from = flights.stream().map(Flights::getFromcity).collect(Collectors.toList());
        List<String> to = flights.stream().map(Flights::getArivcity).collect(Collectors.toList());
        log.info("from:"+from);
        log.info("to:"+to);

        List<Map<String, String>> nodes = new ArrayList<>();
        List<Map<String, String>> edges = new ArrayList<>();

        for(int i = 0; i < from.size(); i++) {
            String fromNode = from.get(i);
            String toNode = to.get(i);

            Map<String, String> nodeFrom = new HashMap<>();
            Map<String, String> nodeTo = new HashMap<>();
            nodeFrom.put("id", fromNode);
            nodeFrom.put("label", fromNode);
            nodes.add(nodeFrom);
            nodeTo.put("id", toNode);
            nodeTo.put("label", toNode);
            nodes.add(nodeTo);

            Map<String, String> edge = new HashMap<>();
            edge.put("source", fromNode);
            edge.put("target", toNode);
            edges.add(edge);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("nodes", nodes);
        data.put("edges", edges);
        log.info("data:"+data);

        return Result.success(data);
    }

    @GetMapping("/checkComplete")
    public Result<StringBuilder> checkComplete(String custName) {
        LambdaQueryWrapper<Reservations> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservations::getResvtype, 1);
        if (Strings.isBlank(custName)) {
            throw new CustomException("请先登录");
        }
        queryWrapper.eq(Reservations::getCustname, custName);
        List<Reservations> reservationsList = reservationsService.list(queryWrapper);
        List<String> modelValues = reservationsList.stream()
                .map(Reservations::getModel)
                .collect(Collectors.toList());
        List<Flights> flights = flightsMapper.selectBatchIds(modelValues);

        List<String> from = flights.stream().map(Flights::getFromcity).collect(Collectors.toList());
        List<String> to = flights.stream().map(Flights::getArivcity).collect(Collectors.toList());

        Set<String> uniqueElements = new HashSet<>(from);
        uniqueElements.addAll(to);
        List<String> allElements = new ArrayList<>(uniqueElements);

        List<String> hotels = hotelsService.selectBatchIds(allElements).stream().map(Hotels::getLocation).collect(Collectors.toList());
        log.info("needHotels:"+hotels);
        List<String> buses = busService.selectBatchIds(allElements).stream().map(Bus::getLocation).collect(Collectors.toList());
        log.info("needBuses:"+buses);

        LambdaQueryWrapper<Reservations> queryHotels = new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservations::getResvtype, 2);
        List<String> reservationsHotels = reservationsService.list(queryWrapper).stream().map(Reservations::getModel).collect(Collectors.toList());
        log.info("haveHotels:"+reservationsHotels);
        LambdaQueryWrapper<Reservations> queryBus = new LambdaQueryWrapper<>();
        queryWrapper.eq(Reservations::getResvtype, 3);
        List<String> reservationsBus = reservationsService.list(queryWrapper).stream().map(Reservations::getModel).collect(Collectors.toList());
        log.info("haveBuses:"+reservationsBus);

        boolean hasNullHotels = compareLists(hotels,reservationsHotels);

        boolean hasNullBus = compareLists(buses,reservationsBus);
        StringBuilder res = new StringBuilder();
        if (!hasNullHotels){
            res.append("有地点酒店未预订;");
        }
        if (!hasNullBus){
            res.append("有地点大巴未预订;");
        }
        log.info("res:"+res);
        return Result.success(res);

    }
//da xiao
    public static boolean compareLists(List<String> list1, List<String> list2) {
        if (list1.size() != list2.size()) {
            return false;
        }

        List<String> copyOfList2 = new ArrayList<>(list2);
        for (String element : list1) {
            if (!copyOfList2.remove(element)) {
                return false;
            }
        }

        return copyOfList2.isEmpty();
    }
}
