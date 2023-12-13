package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Bus;
import com.huo.dbpjbackend.service.BusService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 1. @ClassName BusController
 * 2. @Description 大巴车接口
 * 3. @Author huo
 * 4. @Date 2023/12/1 23:34
 */
@RestController
@RequestMapping("/bus")
@Slf4j
@Api(tags = "bus相关接口")
public class BusController {
    @Autowired
    private BusService busService;
    /**
     * 新增大巴
     */
    @PostMapping
    public Result<String> save(@RequestBody Bus bus) {
        log.info("bus:{}",bus);
        busService.save(bus);
        return Result.success("添加大巴成功");
    }
    /**
     * 大巴类分页
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        Page<Bus> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Bus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Bus::getNumavail);
        busService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
    /**
     * 根据id删除大巴
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("Location") String location){
        LambdaQueryWrapper<Bus> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Bus::getLocation,location);
        busService.remove(queryWrapper);
        return Result.success("删除大巴成功");
    }

    /**
     * 根据id修改大巴
     * @param bus
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Bus bus){
        busService.updateById(bus);
        return Result.success("修改大巴信息成功");
    }

//    /**
//     * 根据条件查询大巴数据
//     * @param bus
//     * @return
//     */
//    @GetMapping("/list")
//    public Result<List<Bus>> list(Bus bus) {
//        LambdaQueryWrapper<Bus> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(bus.get() != null, Bus::getType,bus.getType());
////        queryWrapper.eq(Bus::,1);TODO 验证是否错误
//        queryWrapper.orderByAsc(Bus::getSort).orderByDesc(Bus::getUpdateTime);
//
//
//        List<Bus> list = busService.list(queryWrapper);
//
//        return Result.success(list);
//    }

}
