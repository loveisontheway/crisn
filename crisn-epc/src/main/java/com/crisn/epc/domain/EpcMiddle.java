package com.crisn.epc.domain;

import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 中间关联-epc_middle
 * 
 * @author crisn
 * @date 2022-08-15
 */
public class EpcMiddle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 类型（1=工程关联 2=工井关联 3=设备关联 4=告警关联 5=人员关联） */
    @Excel(name = "类型", readConverterExp = "1=工程关联,2=工井关联,3=设备关联,4=告警关联,5=人员关联")
    private Integer type;

    /** 工程编码（外键epc_project） */
    @Excel(name = "工程编码（外键epc_project）")
    private String projectCode;

    /** 工井编码（外键epc_well） */
    @Excel(name = "工井编码（外键epc_well）")
    private String wellCode;

    /** 设备编码（外键epc_device） */
    @Excel(name = "设备编码（外键epc_device）")
    private String deviceCode;

    /** 告警编码（外键epc_alarm） */
    @Excel(name = "告警编码（外键epc_alarm）")
    private String alarmCode;

    /** 人员id（外键epc_person） */
    @Excel(name = "人员id（外键epc_person）")
    private Integer personId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }
    public void setWellCode(String wellCode) {
        this.wellCode = wellCode;
    }

    public String getWellCode() {
        return wellCode;
    }
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
    }
    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAlarmCode() {
        return alarmCode;
    }
    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public Integer getPersonId() {
        return personId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("projectCode", getProjectCode())
            .append("wellCode", getWellCode())
            .append("deviceCode", getDeviceCode())
            .append("alarmCode", getAlarmCode())
            .append("personId", getPersonId())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}