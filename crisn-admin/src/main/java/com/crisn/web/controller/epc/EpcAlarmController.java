package com.crisn.web.controller.epc;

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
import com.crisn.epc.domain.EpcAlarm;
import com.crisn.epc.service.EpcAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 告警-Controller
 *
 * @author crisn
 * @date 2022-08-10
 */
@RestController
@RequestMapping("/epc/alarm")
public class EpcAlarmController extends BaseController {

    @Autowired
    private EpcAlarmService epcAlarmService;

    /**
     * 分页-告警
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:page')")
    @GetMapping("/page")
    public IPage<EpcAlarm> page(EpcAlarm epcAlarm) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcAlarm> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcAlarm> wrapper = WrapperUtil.entityToWrapper(epcAlarm);
        return epcAlarmService.page(page, wrapper);
    }

    /**
     * 列表-告警
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcAlarm epcAlarm) {
        QueryWrapper<EpcAlarm> wrapper = WrapperUtil.entityToWrapper(epcAlarm);
        List<EpcAlarm> list = epcAlarmService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-告警
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:exp')")
    @Log(title = "告警" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcAlarm epcAlarm) {
        QueryWrapper<EpcAlarm> wrapper = WrapperUtil.entityToWrapper(epcAlarm);
        List<EpcAlarm> list = epcAlarmService.list(wrapper);
        ExcelUtil<EpcAlarm> util = new ExcelUtil<EpcAlarm>(EpcAlarm. class);
        util.exportExcel(response, list, "告警数据");
    }

    /**
     * 获取-告警详细信息
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        return AjaxResult.success(epcAlarmService.getById(id));
    }

    /**
     * 新增-告警
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:add')")
    @Log(title = "告警" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcAlarm epcAlarm) {
        return toAjax(epcAlarmService.save(epcAlarm));
    }

    /**
     * 修改-告警
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:edit')")
    @Log(title = "告警" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcAlarm epcAlarm) {
        return toAjax(epcAlarmService.updateById(epcAlarm));
    }

    /**
     * 删除-告警
     */
    @PreAuthorize("@ss.hasPermi('epc:alarm:del')")
    @Log(title = "告警" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Long[] ids) {
        LambdaUpdateWrapper<EpcAlarm> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcAlarm::getId, ids);
        wrapper.set(EpcAlarm::getState, 1);
        return toAjax(epcAlarmService.update(wrapper));
    }
}