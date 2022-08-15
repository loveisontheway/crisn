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
import com.crisn.epc.domain.EpcMiddle;
import com.crisn.epc.service.EpcMiddleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 中间关联-Controller
 *
 * @author crisn
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/epc/middle")
public class EpcMiddleController extends BaseController {

    @Autowired
    private EpcMiddleService epcMiddleService;

    /**
     * 分页-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:page')")
    @GetMapping("/page")
    public IPage<EpcMiddle> page(EpcMiddle epcMiddle) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcMiddle> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcMiddle> wrapper = WrapperUtil.entityToWrapper(epcMiddle);
        return epcMiddleService.page(page, wrapper);
    }

    /**
     * 列表-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcMiddle epcMiddle) {
        QueryWrapper<EpcMiddle> wrapper = WrapperUtil.entityToWrapper(epcMiddle);
        List<EpcMiddle> list = epcMiddleService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:exp')")
    @Log(title = "中间关联" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcMiddle epcMiddle) {
        QueryWrapper<EpcMiddle> wrapper = WrapperUtil.entityToWrapper(epcMiddle);
        List<EpcMiddle> list = epcMiddleService.list(wrapper);
        ExcelUtil<EpcMiddle> util = new ExcelUtil<EpcMiddle>(EpcMiddle. class);
        util.exportExcel(response, list, "中间关联数据");
    }

    /**
     * 获取-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.success(epcMiddleService.getById(id));
    }

    /**
     * 新增-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:add')")
    @Log(title = "中间关联" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcMiddle epcMiddle) {
        return toAjax(epcMiddleService.save(epcMiddle));
    }

    /**
     * 修改-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:edit')")
    @Log(title = "中间关联" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcMiddle epcMiddle) {
        return toAjax(epcMiddleService.updateById(epcMiddle));
    }

    /**
     * 删除-中间关联
     */
    @PreAuthorize("@ss.hasPermi('epc:middle:del')")
    @Log(title = "中间关联" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Integer[] ids) {
        LambdaUpdateWrapper<EpcMiddle> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcMiddle::getId, ids);
        wrapper.set(EpcMiddle::getState, 1);
        return toAjax(epcMiddleService.update(wrapper));
    }
}