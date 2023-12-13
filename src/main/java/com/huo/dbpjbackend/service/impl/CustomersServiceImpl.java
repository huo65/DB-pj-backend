package com.huo.dbpjbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huo.dbpjbackend.domain.Customers;
import com.huo.dbpjbackend.service.CustomersService;
import com.huo.dbpjbackend.mapper.CustomersMapper;
import org.springframework.stereotype.Service;

/**
* @author 86132
* @description 针对表【customers(客户表)】的数据库操作Service实现
* @createDate 2023-12-01 12:13:20
*/
@Service
public class CustomersServiceImpl extends ServiceImpl<CustomersMapper, Customers>
    implements CustomersService{

}




