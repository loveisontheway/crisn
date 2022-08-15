package com.crisn.epc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crisn.epc.mapper.EpcPersonTypeMapper;
import com.crisn.epc.domain.EpcPersonType;
import com.crisn.epc.service.EpcPersonTypeService;
import org.springframework.stereotype.Service;

/**
 * 人员类型-业务逻辑处理
 *
 * @author crisn
 * @date 2022-08-15
 */
@Service
public class EpcPersonTypeServiceImpl extends ServiceImpl<EpcPersonTypeMapper, EpcPersonType> implements EpcPersonTypeService {

}