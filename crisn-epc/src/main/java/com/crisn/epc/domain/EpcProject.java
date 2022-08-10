package com.crisn.epc.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 项目-epc_project
 * 
 * @author crisn
 * @date 2022-08-10
 */
public class EpcProject extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    /** 工井编码（外键epc_well） */
    @Excel(name = "工井编码（外键epc_well）")
    private String wellCode;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String code;

    /** 项目类型（1=建筑工程费 2=安装工程费 3=设备购置费 4=其他费用 5=基本预备费） */
    @Excel(name = "项目类型", readConverterExp = "1=建筑工程费,2=安装工程费,3=设备购置费,4=其他费用,5=基本预备费")
    private Integer type;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String name;

    /** 项目概况 */
    @Excel(name = "项目概况")
    private String overview;

    /** 建设单位 */
    @Excel(name = "建设单位")
    private String buildUnit;

    /** 建设单位编码 */
    @Excel(name = "建设单位编码")
    private String buildUnitCode;

    /** 监理单位 */
    @Excel(name = "监理单位")
    private String supervisionUnit;

    /** 监理单位编码 */
    @Excel(name = "监理单位编码")
    private String supervisionUnitCode;

    /** 施工单位 */
    @Excel(name = "施工单位")
    private String constructionUnit;

    /** 施工单位编码 */
    @Excel(name = "施工单位编码")
    private String constructionUnitCode;

    /** 计划开工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划开工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planStartTime;

    /** 计划竣工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划竣工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planEndTime;

    /** 实际开工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际开工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualStartTime;

    /** 实际竣工时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际竣工时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualEndTime;

    /** 计划投产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "计划投产时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date planProductionTime;

    /** 实际投产时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "实际投产时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date actualProductionTime;

    /** 建设地址 */
    @Excel(name = "建设地址")
    private String address;

    /** 项目部编码 */
    @Excel(name = "项目部编码")
    private String projectDeptCode;

    /** 项目部类型（1=业主项目部 2=监理项目部 3=施工项目部） */
    @Excel(name = "项目部类型", readConverterExp = "1=业主项目部,2=监理项目部,3=施工项目部")
    private Integer projectDeptType;

    /** 施工阶段（1=施工准备 2=电缆敷设 3=附件安装 4=验收消缺 5=施工暂停 6=竣工投产） */
    @Excel(name = "施工阶段", readConverterExp = "1=施工准备,2=电缆敷设,3=附件安装,4=验收消缺,5=施工暂停,6=竣工投产")
    private Integer stage;

    /** 工作负责人 */
    @Excel(name = "工作负责人")
    private String person;

    /** 工作负责人电话 */
    @Excel(name = "工作负责人电话")
    private String personPhone;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public void setWellCode(String wellCode) {
        this.wellCode = wellCode;
    }

    public String getWellCode() {
        return wellCode;
    }
    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOverview() {
        return overview;
    }
    public void setBuildUnit(String buildUnit) {
        this.buildUnit = buildUnit;
    }

    public String getBuildUnit() {
        return buildUnit;
    }
    public void setBuildUnitCode(String buildUnitCode) {
        this.buildUnitCode = buildUnitCode;
    }

    public String getBuildUnitCode() {
        return buildUnitCode;
    }
    public void setSupervisionUnit(String supervisionUnit) {
        this.supervisionUnit = supervisionUnit;
    }

    public String getSupervisionUnit() {
        return supervisionUnit;
    }
    public void setSupervisionUnitCode(String supervisionUnitCode) {
        this.supervisionUnitCode = supervisionUnitCode;
    }

    public String getSupervisionUnitCode() {
        return supervisionUnitCode;
    }
    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }
    public void setConstructionUnitCode(String constructionUnitCode) {
        this.constructionUnitCode = constructionUnitCode;
    }

    public String getConstructionUnitCode() {
        return constructionUnitCode;
    }
    public void setPlanStartTime(Date planStartTime) {
        this.planStartTime = planStartTime;
    }

    public Date getPlanStartTime() {
        return planStartTime;
    }
    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }
    public void setActualStartTime(Date actualStartTime) {
        this.actualStartTime = actualStartTime;
    }

    public Date getActualStartTime() {
        return actualStartTime;
    }
    public void setActualEndTime(Date actualEndTime) {
        this.actualEndTime = actualEndTime;
    }

    public Date getActualEndTime() {
        return actualEndTime;
    }
    public void setPlanProductionTime(Date planProductionTime) {
        this.planProductionTime = planProductionTime;
    }

    public Date getPlanProductionTime() {
        return planProductionTime;
    }
    public void setActualProductionTime(Date actualProductionTime) {
        this.actualProductionTime = actualProductionTime;
    }

    public Date getActualProductionTime() {
        return actualProductionTime;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }
    public void setProjectDeptCode(String projectDeptCode) {
        this.projectDeptCode = projectDeptCode;
    }

    public String getProjectDeptCode() {
        return projectDeptCode;
    }
    public void setProjectDeptType(Integer projectDeptType) {
        this.projectDeptType = projectDeptType;
    }

    public Integer getProjectDeptType() {
        return projectDeptType;
    }
    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getStage() {
        return stage;
    }
    public void setPerson(String person) {
        this.person = person;
    }

    public String getPerson() {
        return person;
    }
    public void setPersonPhone(String personPhone) {
        this.personPhone = personPhone;
    }

    public String getPersonPhone() {
        return personPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("wellCode", getWellCode())
            .append("code", getCode())
            .append("type", getType())
            .append("name", getName())
            .append("overview", getOverview())
            .append("buildUnit", getBuildUnit())
            .append("buildUnitCode", getBuildUnitCode())
            .append("supervisionUnit", getSupervisionUnit())
            .append("supervisionUnitCode", getSupervisionUnitCode())
            .append("constructionUnit", getConstructionUnit())
            .append("constructionUnitCode", getConstructionUnitCode())
            .append("planStartTime", getPlanStartTime())
            .append("planEndTime", getPlanEndTime())
            .append("actualStartTime", getActualStartTime())
            .append("actualEndTime", getActualEndTime())
            .append("planProductionTime", getPlanProductionTime())
            .append("actualProductionTime", getActualProductionTime())
            .append("address", getAddress())
            .append("projectDeptCode", getProjectDeptCode())
            .append("projectDeptType", getProjectDeptType())
            .append("stage", getStage())
            .append("person", getPerson())
            .append("personPhone", getPersonPhone())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
