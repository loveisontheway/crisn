package com.crisn.epc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crisn.epc.mapper.EpcDeviceMapper;
import com.crisn.epc.domain.EpcDevice;
import com.crisn.epc.service.EpcDeviceService;
import org.springframework.stereotype.Service;

/**
 * 设备-业务逻辑处理
 *
 * @author crisn
 * @date 2022-08-15
 */
@Service
public class EpcDeviceServiceImpl extends ServiceImpl<EpcDeviceMapper, EpcDevice> implements EpcDeviceService {

}