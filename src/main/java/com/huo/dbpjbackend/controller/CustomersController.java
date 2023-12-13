package com.huo.dbpjbackend.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huo.dbpjbackend.common.Result;
import com.huo.dbpjbackend.domain.Customers;
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
@RequestMapping("/customer")
@Slf4j
@Api(tags = "customer相关接口")
public class CustomersController {
    @Autowired
    private CustomersService customerService;
    /**
     * 新增大巴
     */
    @PostMapping
    public Result<String> save(@RequestBody Customers customer) {
        log.info("customer:{}",customer);
        customerService.save(customer);
        return Result.success("添加大巴成功");
    }
    /**
     * 大巴类分页
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
     * 根据id删除大巴
     */
    @DeleteMapping
    public Result<String> delete(@RequestParam("custname") String custname){
        LambdaQueryWrapper<Customers> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Customers::getCustname,custname);
        customerService.remove(queryWrapper);
        return Result.success("删除大巴成功");
    }

    /**
     * 根据id修改大巴
     * @param customer
     * @return
     */
    @PutMapping
    public Result<String> update(@RequestBody Customers customer){
        customerService.updateById(customer);
        return Result.success("修改大巴信息成功");
    }

}
