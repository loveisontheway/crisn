package com.crisn.epc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.crisn.epc.mapper.EpcProjectMapper;
import com.crisn.epc.domain.EpcProject;
import com.crisn.epc.service.EpcProjectService;
import org.springframework.stereotype.Service;

/**
 * 工程-业务逻辑处理
 *
 * @author crisn
 * @date 2022-08-15
 */
@Service
public class EpcProjectServiceImpl extends ServiceImpl<EpcProjectMapper, EpcProject> implements EpcProjectService {

}