package com.crisn.epc.domain;

import com.crisn.common.annotation.Excel;
import com.crisn.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 人员-epc_person
 * 
 * @author crisn
 * @date 2022-08-10
 */
public class EpcPerson extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String name;

    /** 性别（1=男 2=女 3=其它） */
    @Excel(name = "性别", readConverterExp = "1=男,2=女,3=其它")
    private Integer sex;

    /** 项目编码 */
    @Excel(name = "项目编码")
    private String sexCode;

    /** 手机号 */
    @Excel(name = "手机号")
    private String mobile;

    /** 工程编码 */
    @Excel(name = "工程编码")
    private String projectCode;

    /** 人员类型（外键epc_person_type） */
    @Excel(name = "人员类型（外键epc_person_type）")
    private String personTypeId;

    /** 项目部类型（1=业主项目部 2=监理项目部 3=施工项目部） */
    @Excel(name = "项目部类型", readConverterExp = "1=业主项目部,2=监理项目部,3=施工项目部")
    private String projectDeptType;

    /** 身份证 */
    @Excel(name = "身份证")
    private String card;

    /** 身份证（加密） */
    @Excel(name = "身份证（加密）")
    private String cardEncrypt;

    /** 是否有效（0=是 1=否） */
    @Excel(name = "是否有效", readConverterExp = "0=是,1=否")
    private Integer isValid;

    /** 是否黑名单（0=是 1=否） */
    @Excel(name = "是否黑名单", readConverterExp = "0=是,1=否")
    private Integer isBlacklist;

    /** 是否离场（0=是 1=否） */
    @Excel(name = "是否离场", readConverterExp = "0=是,1=否")
    private Integer isDeparture;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getSex() {
        return sex;
    }
    public void setSexCode(String sexCode) {
        this.sexCode = sexCode;
    }

    public String getSexCode() {
        return sexCode;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }
    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }
    public void setPersonTypeId(String personTypeId) {
        this.personTypeId = personTypeId;
    }

    public String getPersonTypeId() {
        return personTypeId;
    }
    public void setProjectDeptType(String projectDeptType) {
        this.projectDeptType = projectDeptType;
    }

    public String getProjectDeptType() {
        return projectDeptType;
    }
    public void setCard(String card) {
        this.card = card;
    }

    public String getCard() {
        return card;
    }
    public void setCardEncrypt(String cardEncrypt) {
        this.cardEncrypt = cardEncrypt;
    }

    public String getCardEncrypt() {
        return cardEncrypt;
    }
    public void setIsValid(Integer isValid) {
        this.isValid = isValid;
    }

    public Integer getIsValid() {
        return isValid;
    }
    public void setIsBlacklist(Integer isBlacklist) {
        this.isBlacklist = isBlacklist;
    }

    public Integer getIsBlacklist() {
        return isBlacklist;
    }
    public void setIsDeparture(Integer isDeparture) {
        this.isDeparture = isDeparture;
    }

    public Integer getIsDeparture() {
        return isDeparture;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("sex", getSex())
            .append("sexCode", getSexCode())
            .append("mobile", getMobile())
            .append("projectCode", getProjectCode())
            .append("personTypeId", getPersonTypeId())
            .append("projectDeptType", getProjectDeptType())
            .append("card", getCard())
            .append("cardEncrypt", getCardEncrypt())
            .append("isValid", getIsValid())
            .append("isBlacklist", getIsBlacklist())
            .append("isDeparture", getIsDeparture())
            .append("state", getState())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}