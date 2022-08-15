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
import com.crisn.epc.domain.EpcWell;
import com.crisn.epc.service.EpcWellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 工井-Controller
 *
 * @author crisn
 * @date 2022-08-15
 */
@RestController
@RequestMapping("/epc/well")
public class EpcWellController extends BaseController {

    @Autowired
    private EpcWellService epcWellService;

    /**
     * 分页-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:page')")
    @GetMapping("/page")
    public IPage<EpcWell> page(EpcWell epcWell) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcWell> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcWell> wrapper = WrapperUtil.entityToWrapper(epcWell);
        return epcWellService.page(page, wrapper);
    }

    /**
     * 列表-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcWell epcWell) {
        QueryWrapper<EpcWell> wrapper = WrapperUtil.entityToWrapper(epcWell);
        List<EpcWell> list = epcWellService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:exp')")
    @Log(title = "工井" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcWell epcWell) {
        QueryWrapper<EpcWell> wrapper = WrapperUtil.entityToWrapper(epcWell);
        List<EpcWell> list = epcWellService.list(wrapper);
        ExcelUtil<EpcWell> util = new ExcelUtil<EpcWell>(EpcWell. class);
        util.exportExcel(response, list, "工井数据");
    }

    /**
     * 获取-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.success(epcWellService.getById(id));
    }

    /**
     * 新增-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:add')")
    @Log(title = "工井" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcWell epcWell) {
        return toAjax(epcWellService.save(epcWell));
    }

    /**
     * 修改-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:edit')")
    @Log(title = "工井" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcWell epcWell) {
        return toAjax(epcWellService.updateById(epcWell));
    }

    /**
     * 删除-工井
     */
    @PreAuthorize("@ss.hasPermi('epc:well:del')")
    @Log(title = "工井" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Integer[] ids) {
        LambdaUpdateWrapper<EpcWell> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcWell::getId, ids);
        wrapper.set(EpcWell::getState, 1);
        return toAjax(epcWellService.update(wrapper));
    }
}