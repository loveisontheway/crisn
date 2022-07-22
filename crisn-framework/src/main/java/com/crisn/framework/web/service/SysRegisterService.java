package com.crisn.framework.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.crisn.common.constant.CacheConst;
import com.crisn.common.constant.Const;
import com.crisn.common.constant.UserConst;
import com.crisn.common.core.domain.entity.SysUser;
import com.crisn.common.core.domain.model.RegisterBody;
import com.crisn.common.core.redis.RedisCache;
import com.crisn.common.exception.user.CaptchaException;
import com.crisn.common.exception.user.CaptchaExpireException;
import com.crisn.common.utils.MessageUtil;
import com.crisn.common.utils.SecurityUtil;
import com.crisn.common.utils.StringUtil;
import com.crisn.framework.manager.AsyncManager;
import com.crisn.framework.manager.factory.AsyncFactory;
import com.crisn.system.service.SysConfigService;
import com.crisn.system.service.SysUserService;

/**
 * 注册校验方法
 *
 * @author CRISN
 */
@Component
public class SysRegisterService {
    @Autowired
    private SysUserService userService;

    @Autowired
    private SysConfigService configService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 注册
     */
    public String register(RegisterBody registerBody) {
        String msg = "", username = registerBody.getUsername(), password = registerBody.getPassword();

        boolean captchaEnabled = configService.selectCaptchaEnabled();
        // 验证码开关
        if (captchaEnabled) {
            validateCaptcha(username, registerBody.getCode(), registerBody.getUuid());
        }

        if (StringUtil.isEmpty(username)) {
            msg = "用户名不能为空";
        } else if (StringUtil.isEmpty(password)) {
            msg = "用户密码不能为空";
        } else if (username.length() < UserConst.USERNAME_MIN_LENGTH
                || username.length() > UserConst.USERNAME_MAX_LENGTH) {
            msg = "账户长度必须在2到20个字符之间";
        } else if (password.length() < UserConst.PASSWORD_MIN_LENGTH
                || password.length() > UserConst.PASSWORD_MAX_LENGTH) {
            msg = "密码长度必须在5到20个字符之间";
        } else if (UserConst.NOT_UNIQUE.equals(userService.checkUserNameUnique(username))) {
            msg = "保存用户'" + username + "'失败，注册账号已存在";
        } else {
            SysUser sysUser = new SysUser();
            sysUser.setUserName(username);
            sysUser.setNickName(username);
            sysUser.setPassword(SecurityUtil.encryptPassword(registerBody.getPassword()));
            boolean regFlag = userService.registerUser(sysUser);
            if (!regFlag) {
                msg = "注册失败,请联系系统管理人员";
            } else {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Const.REGISTER,
                        MessageUtil.message("user.register.success")));
            }
        }
        return msg;
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
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException();
        }
    }
}
