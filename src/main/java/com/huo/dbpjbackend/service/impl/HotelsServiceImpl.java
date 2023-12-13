package com.huo.dbpjbackend.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huo.dbpjbackend.domain.Hotels;
import com.huo.dbpjbackend.service.HotelsService;
import com.huo.dbpjbackend.mapper.HotelsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86132
* @description 针对表【hotels(DB_PJ.`HOTELS`)】的数据库操作Service实现
* @createDate 2023-12-01 12:14:09
*/
@Service
public class HotelsServiceImpl extends ServiceImpl<HotelsMapper, Hotels>
    implements HotelsService {

}




