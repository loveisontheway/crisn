package com.crisn.common.enums;

import cn.hutool.core.util.StrUtil;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * EPC
 *
 * @author jjl
 * @date 2022/3/25
 */
public enum EpcEnum {

    // 性别
    SEX_1(1, "男"),
    SEX_2(2, "女"),
    SEX_3(3, "其他"),

    // 施工阶段
    STAGE_1(1, "施工准备"),
    STAGE_2(2, "电缆敷设"),
    STAGE_3(3, "附件安装"),
    STAGE_4(4, "验收消缺"),
    STAGE_5(5, "施工暂停"),
    STAGE_6(6, "竣工投产"),

    // 工井类型
    WELL_1(0, "已处理"),
    WELL_2(1, "未处理"),

    // 项目部类别
    PROJECT_DEPT_1(1, "业主项目部"),
    PROJECT_DEPT_2(2, "监理项目部"),
    PROJECT_DEPT_3(3, "施工项目部");

    private Integer num;
    private String value;

    EpcEnum(int num, String value) {
        this.num = num;
        this.value = value;
    }

    /**
     * num -> value
     *
     * @param type String
     * @param num  Integer
     * @return String
     */
    public static String getValue(String type, Integer num) {
        String str = type.toUpperCase() + "_" + num;
        for (EpcEnum e : EpcEnum.values()) {
            if (str.equals(e.toString())) {
                return e.getValue();
            }
        }
        return null;
    }

    /**
     * value -> num
     *
     * @param value String
     * @return Integer
     */
    public static Integer getNum(String value) {
        for (EpcEnum e : EpcEnum.values()) {
            if (value.equals(e.getValue())) {
                return e.getNum();
            }
        }
        return null;
    }

    /**
     * value (,) -> num (,)
     *
     * @param value String
     * @return String
     */
    public static String getNumDot(String value) {
        if (StrUtil.isEmpty(value) && !value.contains(",")) {
            return null;
        }
        String strDot = null;
        String[] strArr = value.split(",");
        String[] numArr = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            numArr[i] = String.valueOf(getNum(strArr[i]));
        }
        Arrays.sort(numArr);
        strDot = Arrays.stream(numArr)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(","));
        return strDot;
    }

    /**
     * num (,) -> value (,)
     *
     * @param type String
     * @param num  String
     * @return String
     */
    public static String getValueDot(String type, String num) {
        if (StrUtil.isEmpty(num) && !num.contains(",")) {
            return null;
        }
        String strDot = null;
        String[] strArr = num.split(",");
        String[] valArr = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            Integer no = Integer.valueOf(strArr[i]);
            valArr[i] = String.valueOf(getValue(type, no));
        }
        Arrays.sort(valArr);
        strDot = Arrays.stream(valArr)
                .filter(Objects::nonNull)
                .collect(Collectors.joining(","));
        return strDot;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
