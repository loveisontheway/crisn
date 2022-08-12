package com.crisn.epc.domain;

import java.math.BigDecimal;
import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备-epc_device
 * 
 * @author crisn
 * @date 2022-08-10
 */
public class EpcDevice extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 设备编码 */
    @Excel(name = "设备编码")
    private String code;

    /** 设备名称 */
    @Excel(name = "设备名称")
    private String name;

    /** 工程编码（外键epc_project） */
    @Excel(name = "工程编码（外键epc_project）")
    private String projectCode;

    /** 工井编码（外键epc_well） */
    @Excel(name = "工井编码（外键epc_well）")
    private String wellCode;

    /** 告警编码（外键epc_alarm） */
    @Excel(name = "告警编码（外键epc_alarm）")
    private String alarmCode;

    /** 设备状态 */
    @Excel(name = "设备状态")
    private Integer status;

    /** 设备电量 */
    @Excel(name = "设备电量")
    private BigDecimal kwh;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
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
    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAlarmCode() {
        return alarmCode;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
    public void setKwh(BigDecimal kwh) {
        this.kwh = kwh;
    }

    public BigDecimal getKwh() {
        return kwh;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("projectCode", getProjectCode())
            .append("wellCode", getWellCode())
            .append("alarmCode", getAlarmCode())
            .append("status", getStatus())
            .append("kwh", getKwh())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}