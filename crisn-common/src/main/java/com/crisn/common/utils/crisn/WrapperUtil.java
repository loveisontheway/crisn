package com.crisn.common.utils.crisn;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crisn.common.exception.ServiceException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Entity > QueryWrapper 通过反射机制实现
 *
 * @author jjl
 * @date 2022/5/23
 */
public abstract class WrapperUtil {

    /**
     * Entity > QueryWrapper
     *
     * @param entity object
     * @return QueryWrapper<T> 查询对象封装操作类
     */
    public static <T> QueryWrapper<T> entityToWrapper(T entity) {
        Class<?> clazz = entity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        // 遍历属性
        for (Field field : fields) {
            Method method;
            try {
                String fieldName = field.getName();
                // 跳过 serialVersionUID
                if ("serialVersionUID".equals(fieldName)) {
                    continue;
                }
                // 获取列名 DB > Table
                String column = StrUtil.toUnderlineCase(fieldName);
                // get方法
                method = clazz.getMethod("get" + captureName(fieldName));
                Object value = method.invoke(entity);
                if ("state".equals(column)) {
                    wrapper.eq("state", 0);
                }
                if (value instanceof String) {
                    String str = (String) value;
                    wrapper.like(StrUtil.isNotBlank(str), column, value);
                } else {
                    wrapper.eq(value != null, column, value);
                }
            } catch (Exception e) {
                throw new ServiceException(e.toString());
            }
        }
        return wrapper;
    }

    /**
     * 将字符串的首字母转大写
     *
     * @param str 需要转换的字符串
     * @return String
     */
    private static String captureName(String str) {
        // 进行字母的ascii编码前移，效率要高于截取字符串
        char[] cs = str.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);
    }

}
