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
import com.crisn.epc.domain.EpcPerson;
import com.crisn.epc.service.EpcPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人员-Controller
 *
 * @author crisn
 * @date 2022-08-10
 */
@RestController
@RequestMapping("/epc/person")
public class EpcPersonController extends BaseController {

    @Autowired
    private EpcPersonService epcPersonService;

    /**
     * 分页-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:page')")
    @GetMapping("/page")
    public IPage<EpcPerson> page(EpcPerson epcPerson) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcPerson> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcPerson> wrapper = WrapperUtil.entityToWrapper(epcPerson);
        return epcPersonService.page(page, wrapper);
    }

    /**
     * 列表-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcPerson epcPerson) {
        QueryWrapper<EpcPerson> wrapper = WrapperUtil.entityToWrapper(epcPerson);
        List<EpcPerson> list = epcPersonService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:exp')")
    @Log(title = "人员" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcPerson epcPerson) {
        QueryWrapper<EpcPerson> wrapper = WrapperUtil.entityToWrapper(epcPerson);
        List<EpcPerson> list = epcPersonService.list(wrapper);
        ExcelUtil<EpcPerson> util = new ExcelUtil<EpcPerson>(EpcPerson. class);
        util.exportExcel(response, list, "人员数据");
    }

    /**
     * 获取-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.success(epcPersonService.getById(id));
    }

    /**
     * 新增-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:add')")
    @Log(title = "人员" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcPerson epcPerson) {
        return toAjax(epcPersonService.save(epcPerson));
    }

    /**
     * 修改-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:edit')")
    @Log(title = "人员" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcPerson epcPerson) {
        return toAjax(epcPersonService.updateById(epcPerson));
    }

    /**
     * 删除-人员
     */
    @PreAuthorize("@ss.hasPermi('epc:person:del')")
    @Log(title = "人员" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Integer[] ids) {
        LambdaUpdateWrapper<EpcPerson> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcPerson::getId, ids);
        wrapper.set(EpcPerson::getState, 1);
        return toAjax(epcPersonService.update(wrapper));
    }
}