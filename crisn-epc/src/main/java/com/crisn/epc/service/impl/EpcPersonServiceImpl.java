package com.crisn.epc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crisn.epc.mapper.EpcPersonMapper;
import com.crisn.epc.domain.EpcPerson;
import com.crisn.epc.service.EpcPersonService;
import org.springframework.stereotype.Service;

/**
 * 人员-业务逻辑处理
 *
 * @author crisn
 * @date 2022-08-15
 */
@Service
public class EpcPersonServiceImpl extends ServiceImpl<EpcPersonMapper, EpcPerson> implements EpcPersonService {

}