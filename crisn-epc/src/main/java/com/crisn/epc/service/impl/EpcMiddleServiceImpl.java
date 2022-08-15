package com.crisn.epc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crisn.epc.mapper.EpcMiddleMapper;
import com.crisn.epc.domain.EpcMiddle;
import com.crisn.epc.service.EpcMiddleService;
import org.springframework.stereotype.Service;

/**
 * 中间关联-业务逻辑处理
 *
 * @author crisn
 * @date 2022-08-15
 */
@Service
public class EpcMiddleServiceImpl extends ServiceImpl<EpcMiddleMapper, EpcMiddle> implements EpcMiddleService {

}