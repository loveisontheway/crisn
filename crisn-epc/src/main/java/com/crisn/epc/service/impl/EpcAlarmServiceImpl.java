package com.crisn.epc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crisn.epc.mapper.EpcAlarmMapper;
import com.crisn.epc.domain.EpcAlarm;
import com.crisn.epc.service.EpcAlarmService;
import org.springframework.stereotype.Service;

/**
 * 告警-业务逻辑处理
 *
 * @author crisn
 * @date 2022-08-10
 */
@Service
public class EpcAlarmServiceImpl extends ServiceImpl<EpcAlarmMapper, EpcAlarm> implements EpcAlarmService {

}