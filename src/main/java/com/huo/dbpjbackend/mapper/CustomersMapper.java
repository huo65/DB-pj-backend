package com.huo.dbpjbackend.mapper;

import com.huo.dbpjbackend.domain.Customers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 86132
* @description 针对表【customers(客户表)】的数据库操作Mapper
* @createDate 2023-12-01 12:13:20
* @Entity generator.domain.Customers
*/
@Mapper
public interface CustomersMapper extends BaseMapper<Customers> {

}




