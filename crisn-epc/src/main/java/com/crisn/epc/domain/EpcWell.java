package com.crisn.epc.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 工井-epc_well
 * 
 * @author crisn
 * @date 2022-08-10
 */
public class EpcWell extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 工井编码 */
    @Excel(name = "工井编码")
    private String code;

    /** 工井名称 */
    @Excel(name = "工井名称")
    private String name;

    /** 工井类型（1=盘井 2=直线井 3=转弯井 4=接头井） */
    @Excel(name = "工井类型", readConverterExp = "1=盘井,2=直线井,3=转弯井,4=接头井")
    private Integer type;

    /** 经度 */
    @Excel(name = "经度")
    private BigDecimal lon;

    /** 纬度 */
    @Excel(name = "纬度")
    private BigDecimal lat;

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
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    public BigDecimal getLon() {
        return lon;
    }
    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    public BigDecimal getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("type", getType())
            .append("lon", getLon())
            .append("lat", getLat())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
