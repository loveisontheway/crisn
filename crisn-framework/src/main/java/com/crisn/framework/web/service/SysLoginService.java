package com.crisn.framework.web.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.crisn.common.constant.CacheConst;
import com.crisn.common.constant.Const;
import com.crisn.common.core.domain.entity.SysUser;
import com.crisn.common.core.domain.model.LoginUser;
import com.crisn.common.core.redis.RedisCache;
import com.crisn.common.exception.ServiceException;
import com.crisn.common.exception.user.CaptchaException;
import com.crisn.common.exception.user.CaptchaExpireException;
import com.crisn.common.exception.user.UserPasswordNotMatchException;
import com.crisn.common.utils.DateUtil;
import com.crisn.common.utils.MessageUtil;
import com.crisn.common.utils.ServletUtil;
import com.crisn.common.utils.StringUtil;
import com.crisn.common.utils.ip.IpUtil;
import com.crisn.framework.manager.AsyncManager;
import com.crisn.framework.manager.factory.AsyncFactory;
import com.crisn.system.service.SysConfigService;
import com.crisn.system.service.SysUserService;

/**
 * 登录校验方法
 *
 * @author CRISN
 */
@Component
public class SysLoginService {
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private SysUserService userService;

    @Autowired
    private SysConfigService configService;

    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid) {
        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, code, uuid);
        }
        // 用户验证
        Authentication authentication = null;
        try {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Const.LOGIN_FAIL, MessageUtil.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Const.LOGIN_FAIL, e.getMessage()));
                throw new ServiceException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Const.LOGIN_SUCCESS, MessageUtil.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getUserId());
        // 生成token
        return tokenService.createToken(loginUser);
    }

    /**
     * 校验验证码
     *
     * @param username 用户名
     * @param code     验证码
     * @param uuid     唯一标识
     * @return 结果
     */
    public void validateCaptcha(String username, String code, String uuid) {
        String verifyKey = CacheConst.CAPTCHA_CODE_KEY + StringUtil.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);
        if (captcha == null) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Const.LOGIN_FAIL, MessageUtil.message("user.jcaptcha.expire")));
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Const.LOGIN_FAIL, MessageUtil.message("user.jcaptcha.error")));
            throw new CaptchaException();
        }
    }

    /**
     * 记录登录信息
     *
     * @param userId 用户ID
     */
    public void recordLoginInfo(Long userId) {
        SysUser sysUser = new SysUser();
        sysUser.setUserId(userId);
        sysUser.setLoginIp(IpUtil.getIpAddr(ServletUtil.getRequest()));
        sysUser.setLoginDate(DateUtil.getNowDate());
        userService.updateUserProfile(sysUser);
    }
}
