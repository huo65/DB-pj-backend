package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Hotels;
import com.huo.dbpjbackend.service.HotelsService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 1. @ClassName HotelsController
 * 2. @Description 旅馆
 * 3. @Author huo
 * 4. @Date 2023/12/6 22:47
 */
@RestController
@RequestMapping("/hotels")
@Slf4j
@Api(tags = "hotel相关接口")
public class HotelsController {
    @Autowired
    private HotelsService hotelsService;

    /**
     * 新增旅馆
     */
    @PostMapping
    public Result<String> save(@RequestBody Hotels hotels) {
        log.info("hotels:{}",hotels);
        hotelsService.save(hotels);
        return Result.success("添加旅馆成功");
    }
    /**
     * 旅馆类分页
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        Page<Hotels> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Hotels> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Hotels::getNumavail);
        hotelsService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
    /**
     * 根据id删除旅馆
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("Location") String location){
        LambdaQueryWrapper<Hotels> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Hotels::getLocation,location);
        hotelsService.remove(queryWrapper);
        return Result.success("删除旅馆成功");
    }

    /**
     * 根据id修改旅馆
     * @param hotels
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Hotels hotels){
        hotelsService.updateById(hotels);
        return Result.success("修改旅馆信息成功");
    }
}
