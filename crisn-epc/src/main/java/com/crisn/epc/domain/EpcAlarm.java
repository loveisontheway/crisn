package com.crisn.epc.domain;

import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 告警-epc_alarm
 * 
 * @author crisn
 * @date 2022-08-10
 */
public class EpcAlarm extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 设备编码（外键epc_device） */
    @Excel(name = "设备编码（外键epc_device）")
    private String deviceCode;

    /** 工程编码（外键epc_project） */
    @Excel(name = "工程编码（外键epc_project）")
    private String projectCode;

    /** 工井编码（外键epc_well） */
    @Excel(name = "工井编码（外键epc_well）")
    private String wellCode;

    /** 告警内容 */
    @Excel(name = "告警内容")
    private String content;

    /** 处理状态（0=已处理 1=未处理） */
    @Excel(name = "处理状态", readConverterExp = "0=已处理,1=未处理")
    private Integer status;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceCode() {
        return deviceCode;
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
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceCode", getDeviceCode())
            .append("projectCode", getProjectCode())
            .append("wellCode", getWellCode())
            .append("content", getContent())
            .append("status", getStatus())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}