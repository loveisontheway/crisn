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
import com.crisn.epc.domain.EpcPersonType;
import com.crisn.epc.service.EpcPersonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 人员类型-Controller
 *
 * @author crisn
 * @date 2022-08-10
 */
@RestController
@RequestMapping("/epc/person_type")
public class EpcPersonTypeController extends BaseController {

    @Autowired
    private EpcPersonTypeService epcPersonTypeService;

    /**
     * 分页-人员类型
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:page')")
    @GetMapping("/page")
    public IPage<EpcPersonType> page(EpcPersonType epcPersonType) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcPersonType> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcPersonType> wrapper = WrapperUtil.entityToWrapper(epcPersonType);
        return epcPersonTypeService.page(page, wrapper);
    }

    /**
     * 列表-人员类型
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcPersonType epcPersonType) {
        QueryWrapper<EpcPersonType> wrapper = WrapperUtil.entityToWrapper(epcPersonType);
        List<EpcPersonType> list = epcPersonTypeService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-人员类型
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:exp')")
    @Log(title = "人员类型" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcPersonType epcPersonType) {
        QueryWrapper<EpcPersonType> wrapper = WrapperUtil.entityToWrapper(epcPersonType);
        List<EpcPersonType> list = epcPersonTypeService.list(wrapper);
        ExcelUtil<EpcPersonType> util = new ExcelUtil<EpcPersonType>(EpcPersonType. class);
        util.exportExcel(response, list, "人员类型数据");
    }

    /**
     * 获取-人员类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Long id) {
        return AjaxResult.success(epcPersonTypeService.getById(id));
    }

    /**
     * 新增-人员类型
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:add')")
    @Log(title = "人员类型" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcPersonType epcPersonType) {
        return toAjax(epcPersonTypeService.save(epcPersonType));
    }

    /**
     * 修改-人员类型
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:edit')")
    @Log(title = "人员类型" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcPersonType epcPersonType) {
        return toAjax(epcPersonTypeService.updateById(epcPersonType));
    }

    /**
     * 删除-人员类型
     */
    @PreAuthorize("@ss.hasPermi('epc:person_type:del')")
    @Log(title = "人员类型" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Long[] ids) {
        LambdaUpdateWrapper<EpcPersonType> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcPersonType::getId, ids);
        wrapper.set(EpcPersonType::getState, 1);
        return toAjax(epcPersonTypeService.update(wrapper));
    }
}