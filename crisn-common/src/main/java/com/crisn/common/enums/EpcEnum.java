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

    // 编制类型
    STAFF_1(1, "估算"),
    STAFF_2(2, "概算"),
    STAFF_3(3, "预算"),
    STAFF_4(4, "施工结算"),
    STAFF_5(5, "全口径结算"),

    // 电压等级
    KV_1(1, "10kV"),
    KV_2(2, "20kV"),
    KV_3(3, "1kV"),
    KV_4(4, "0.4kV"),
    KV_5(5, "220kV"),

    // 工程性质
    NATURE_1(1, "新建"),
    NATURE_2(2, "扩建"),
    NATURE_3(3, "改建"),
    NATURE_4(4, "检修"),
    NATURE_5(5, "拆除"),

    // 占地类型
    LAND_1(1, "城区"),
    LAND_2(2, "郊区"),
    LAND_3(3, "乡村"),

    // 地区类型
    AREA_1(1, "I类"),
    AREA_2(2, "II类"),
    AREA_3(3, "III类"),
    AREA_4(4, "IV类"),
    AREA_5(5, "V类"),

    // 特殊地区
    SPECIAL_1(1, "常规地区"),
    SPECIAL_2(2, "高海拔地区"),
    SPECIAL_3(3, "高纬度寒冷地区"),
    SPECIAL_4(4, "酷热地区"),

    // 工程类型
    EPC_1(1, "开关站"),
    EPC_2(2, "配电室"),
    EPC_3(3, "环网箱"),
    EPC_4(4, "箱式变电站"),
    EPC_5(5, "配电变台"),
    EPC_6(6, "架空线路"),
    EPC_7(7, "电缆线路"),
    EPC_8(8, "通信线路"),

    // 结算版本
    VERSION_1(1, "审定版"),
    VERSION_2(2, "未审定版"),

    // 任务状态
    TASK_1(1, "已完成"),
    TASK_2(2, "待处理"),

    // 编制进度状态
    PROGRESS_1(1, "施工结算"),
    PROGRESS_2(2, "甲供物资"),
    PROGRESS_3(3, "其他费用"),

    // 状态值
    STATE_1(1, "启用"),
    STATE_2(2, "停用"),

    //项目划分
    DIVIDE_1(1, "安装工程"),
    DIVIDE_2(2, "架空线路工程"),
    DIVIDE_3(3, "架空线路本体工程"),
    DIVIDE_4(4, "杆塔工程"),
    DIVIDE_5(5, "杆塔组立"),
    DIVIDE_6(6, "电缆线路工程"),
    DIVIDE_7(7, "电缆线路安装"),
    DIVIDE_8(8, "电缆敷设"),
    DIVIDE_9(9, "10kV电缆敷设"),
    DIVIDE_10(10, "配电站"),
    DIVIDE_11(11, "开关站工程"),
    DIVIDE_12(12, "主要生产工程"),
    DIVIDE_13(13, "开关站"),
    DIVIDE_14(14, "变压器"),
    DIVIDE_15(15, "杆上变配电装置"),
    DIVIDE_16(16, "变配电装置安装"),
    DIVIDE_17(17, "配电装置"),
    DIVIDE_18(18, "10kV(20kV)配电装置"),
    DIVIDE_19(19, "架线工程"),
    DIVIDE_20(20, "导线架设"),
    DIVIDE_21(21, "10kV电缆头安装"),
    DIVIDE_22(22, "建筑工程"),
    DIVIDE_23(23, "电缆线路建筑"),
    DIVIDE_24(24, "构筑物"),
    DIVIDE_25(25, "电缆埋管");

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
