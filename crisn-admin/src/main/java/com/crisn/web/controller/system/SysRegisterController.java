package com.crisn.web.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.crisn.common.core.controller.BaseController;
import com.crisn.common.core.domain.AjaxResult;
import com.crisn.common.core.domain.model.RegisterBody;
import com.crisn.common.utils.StringUtil;
import com.crisn.framework.web.service.SysRegisterService;
import com.crisn.system.service.SysConfigService;

/**
 * 注册验证
 *
 * @author CRISN
 */
@RestController
public class SysRegisterController extends BaseController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private SysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtil.isEmpty(msg) ? success() : error(msg);
    }
}
