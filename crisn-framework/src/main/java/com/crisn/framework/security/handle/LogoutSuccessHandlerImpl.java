package com.crisn.framework.security.handle;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import com.alibaba.fastjson2.JSON;
import com.crisn.common.constant.Const;
import com.crisn.common.constant.HttpStatusConst;
import com.crisn.common.core.domain.AjaxResult;
import com.crisn.common.core.domain.model.LoginUser;
import com.crisn.common.utils.ServletUtil;
import com.crisn.common.utils.StringUtil;
import com.crisn.framework.manager.AsyncManager;
import com.crisn.framework.manager.factory.AsyncFactory;
import com.crisn.framework.web.service.TokenService;

/**
 * 自定义退出处理类 返回成功
 *
 * @author CRISN
 */
@Configuration
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    @Autowired
    private TokenService tokenService;

    /**
     * 退出处理
     *
     * @return
     */
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtil.isNotNull(loginUser)) {
            String userName = loginUser.getUsername();
            // 删除用户缓存记录
            tokenService.delLoginUser(loginUser.getToken());
            // 记录用户退出日志
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(userName, Const.LOGOUT, "退出成功"));
        }
        ServletUtil.renderString(response, JSON.toJSONString(AjaxResult.error(HttpStatusConst.SUCCESS, "退出成功")));
    }
}
