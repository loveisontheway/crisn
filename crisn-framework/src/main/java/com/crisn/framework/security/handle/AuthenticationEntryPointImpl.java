package com.crisn.framework.security.handle;

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson2.JSON;
import com.crisn.common.constant.HttpStatusConst;
import com.crisn.common.core.domain.AjaxResult;
import com.crisn.common.utils.ServletUtil;
import com.crisn.common.utils.StringUtil;

/**
 * 认证失败处理类 返回未授权
 *
 * @author CRISN
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable {
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {
        int code = HttpStatusConst.UNAUTHORIZED;
        String msg = StringUtil.format("请求访问：{}，认证失败，无法访问系统资源", request.getRequestURI());
        ServletUtil.renderString(response, JSON.toJSONString(AjaxResult.error(code, msg)));
    }
}
