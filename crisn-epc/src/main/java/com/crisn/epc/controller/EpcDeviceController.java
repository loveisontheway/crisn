package com.crisn.epc.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.crisn.common.annotation.Log;
import com.crisn.common.core.controller.BaseController;
import com.crisn.common.core.domain.AjaxResult;
import com.crisn.common.core.page.PageDomain;
import com.crisn.common.core.page.TableSupport;
import com.crisn.common.enums.BusinessTypeEnum;
import com.crisn.common.utils.crisn.WrapperUtil;
import com.crisn.common.utils.poi.ExcelUtil;
import com.crisn.epc.domain.EpcDevice;
import com.crisn.epc.service.EpcDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 设备-Controller
 *
 * @author crisn
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/epc/device")
public class EpcDeviceController extends BaseController {

    @Autowired
    private EpcDeviceService epcDeviceService;

    /**
     * 分页-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:page')")
    @GetMapping("/page")
    public IPage<EpcDevice> page(EpcDevice epcDevice) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcDevice> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcDevice> wrapper = WrapperUtil.entityToWrapper(epcDevice);
        return epcDeviceService.page(page, wrapper);
    }

    /**
     * 列表-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcDevice epcDevice) {
        QueryWrapper<EpcDevice> wrapper = WrapperUtil.entityToWrapper(epcDevice);
        List<EpcDevice> list = epcDeviceService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:exp')")
    @Log(title = "设备" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcDevice epcDevice) {
        QueryWrapper<EpcDevice> wrapper = WrapperUtil.entityToWrapper(epcDevice);
        List<EpcDevice> list = epcDeviceService.list(wrapper);
        ExcelUtil<EpcDevice> util = new ExcelUtil<EpcDevice>(EpcDevice. class);
        util.exportExcel(response, list, "设备数据");
    }

    /**
     * 获取-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.success(epcDeviceService.getById(id));
    }

    /**
     * 新增-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:add')")
    @Log(title = "设备" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcDevice epcDevice) {
        return toAjax(epcDeviceService.save(epcDevice));
    }

    /**
     * 修改-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:edit')")
    @Log(title = "设备" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcDevice epcDevice) {
        return toAjax(epcDeviceService.updateById(epcDevice));
    }

    /**
     * 删除-设备
     */
    @PreAuthorize("@ss.hasPermi('epc:device:del')")
    @Log(title = "设备" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Integer[] ids) {
        LambdaUpdateWrapper<EpcDevice> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcDevice::getId, ids);
        wrapper.set(EpcDevice::getState, 1);
        return toAjax(epcDeviceService.update(wrapper));
    }
}