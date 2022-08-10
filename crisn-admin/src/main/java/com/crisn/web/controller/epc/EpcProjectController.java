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
import com.crisn.epc.domain.EpcProject;
import com.crisn.epc.service.EpcProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 项目-Controller
 *
 * @author crisn
 * @date 2022-08-10
 */
@RestController
@RequestMapping("/epc/project")
public class EpcProjectController extends BaseController {

    @Autowired
    private EpcProjectService epcProjectService;

    /**
     * 分页-项目
     */
    @PreAuthorize("@ss.hasPermi('epc:project:page')")
    @GetMapping("/page")
    public IPage<EpcProject> page(EpcProject epcProject) {
        PageDomain domain = TableSupport.buildPageRequest();
        Page<EpcProject> page = new Page<>(domain.getPageNum(), domain.getPageSize());
        QueryWrapper<EpcProject> wrapper = WrapperUtil.entityToWrapper(epcProject);
        return epcProjectService.page(page, wrapper);
    }

    /**
     * 列表-项目
     */
    @PreAuthorize("@ss.hasPermi('epc:project:list')")
    @GetMapping("/list")
    public AjaxResult list(EpcProject epcProject) {
        QueryWrapper<EpcProject> wrapper = WrapperUtil.entityToWrapper(epcProject);
        List<EpcProject> list = epcProjectService.list(wrapper);
        return AjaxResult.success(list);
    }

    /**
     * 导出-项目
     */
    @PreAuthorize("@ss.hasPermi('epc:project:exp')")
    @Log(title = "项目" , businessType = BusinessTypeEnum.EXPORT)
    @PostMapping("/exp")
    public void exp(HttpServletResponse response, EpcProject epcProject) {
        QueryWrapper<EpcProject> wrapper = WrapperUtil.entityToWrapper(epcProject);
        List<EpcProject> list = epcProjectService.list(wrapper);
        ExcelUtil<EpcProject> util = new ExcelUtil<EpcProject>(EpcProject. class);
        util.exportExcel(response, list, "项目数据");
    }

    /**
     * 获取-项目详细信息
     */
    @PreAuthorize("@ss.hasPermi('epc:project:get')")
    @GetMapping("/{id}")
    public AjaxResult get(@PathVariable("id") Integer id) {
        return AjaxResult.success(epcProjectService.getById(id));
    }

    /**
     * 新增-项目
     */
    @PreAuthorize("@ss.hasPermi('epc:project:add')")
    @Log(title = "项目" , businessType = BusinessTypeEnum.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody EpcProject epcProject) {
        return toAjax(epcProjectService.save(epcProject));
    }

    /**
     * 修改-项目
     */
    @PreAuthorize("@ss.hasPermi('epc:project:edit')")
    @Log(title = "项目" , businessType = BusinessTypeEnum.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody EpcProject epcProject) {
        return toAjax(epcProjectService.updateById(epcProject));
    }

    /**
     * 删除-项目
     */
    @PreAuthorize("@ss.hasPermi('epc:project:del')")
    @Log(title = "项目" , businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult del(@PathVariable Integer[] ids) {
        LambdaUpdateWrapper<EpcProject> wrapper = new LambdaUpdateWrapper<>();
        wrapper.in(EpcProject::getId, ids);
        wrapper.set(EpcProject::getState, 1);
        return toAjax(epcProjectService.update(wrapper));
    }
}