package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Customers;
import com.huo.dbpjbackend.domain.Flights;
import com.huo.dbpjbackend.service.CustomersService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 1. @ClassName CustomersController
 * 2. @Description 游客
 * 3. @Author huo
 * 4. @Date 2023/12/6 22:46
 */
@RestController
@RequestMapping("/customers")
@Slf4j
@Api(tags = "customer相关接口")
public class CustomersController {
    @Autowired
    private CustomersService customerService;
    /**
     * 新增用户
     */
    @PostMapping
    public Result<String> save(@RequestBody Customers customer) {
        log.info("customer:{}",customer);
        customerService.save(customer);
        return Result.success("添加用户成功");
    }
    /**
     * 用户类分页
     */
    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        Page<Customers> pageInfo = new Page<>(page,pageSize);

        LambdaQueryWrapper<Customers> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Customers::getCustid);
        customerService.page(pageInfo,queryWrapper);

        return Result.success(pageInfo);
    }
    /**
     * 根据id获取用户
     * @return
     */
    @GetMapping("/{id}")
    public Result<Customers> get(@PathVariable String id){
        LambdaQueryWrapper<Customers> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customers::getCustid,id);
        Customers customers= customerService.getOne(queryWrapper);
        return Result.success(customers);
    }

    /**
     * 根据id删除用户
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("custname") String custname){
        LambdaQueryWrapper<Customers> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customers::getCustname,custname);
        customerService.remove(queryWrapper);
        return Result.success("删除用户成功");
    }

    /**
     * 根据id修改用户
     * @param customer
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Customers customer){
        customerService.updateById(customer);
        return Result.success("修改用户信息成功");
    }

}
