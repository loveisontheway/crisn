package com.crisn.epc.domain;

import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人员类型-epc_person_type
 * 
 * @author crisn
 * @date 2022-08-10
 */
public class EpcPersonType extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 类别（1=业主 2=监理 3=施工 4=班组） */
    @Excel(name = "类别", readConverterExp = "1=业主,2=监理,3=施工,4=班组")
    private Integer category;

    /** 职称 */
    @Excel(name = "职称")
    private String jobTitle;

    /** 职称编码 */
    @Excel(name = "职称编码")
    private String jobTitleCode;

    /** 状态值（0=启用 1=停用） */
    @Excel(name = "状态值", readConverterExp = "0=启用,1=停用")
    private Integer state;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getCategory() {
        return category;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }

    public String getJobTitleCode() {
        return jobTitleCode;
    }
    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("category", getCategory())
            .append("jobTitle", getJobTitle())
            .append("jobTitleCode", getJobTitleCode())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
