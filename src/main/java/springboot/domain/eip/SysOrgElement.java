package springboot.domain.eip;

import java.io.Serializable;
import java.util.Date;

public class SysOrgElement implements Serializable {
    private String fdId;

    private Integer fdOrgType;

    private String fdName;

    private String fdNamePinyin;

    private Integer fdOrder;

    private String fdNo;

    private String fdKeyword;

    private Byte fdIsAvailable;

    private Byte fdIsAbandon;

    private Byte fdIsBusiness;

    private String fdImportInfo;

    private Byte fdFlagDeleted;

    private String fdLdapDn;

    private String fdMemo;

    private String fdHierarchyId;

    private Date fdCreateTime;

    private Date fdAlterTime;

    private String fdPortalLink;

    private String fdPortalName;

    private String fdThisLeaderid;

    private String fdSuperLeaderid;

    private String fdParentorgid;

    private String fdParentid;

    private String fdCateid;

    private String fdOrgEmail;

    private Byte authReaderFlag;

    private String fdNameSimplePinyin;

    private static final long serialVersionUID = 1L;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public Integer getFdOrgType() {
        return fdOrgType;
    }

    public void setFdOrgType(Integer fdOrgType) {
        this.fdOrgType = fdOrgType;
    }

    public String getFdName() {
        return fdName;
    }

    public void setFdName(String fdName) {
        this.fdName = fdName == null ? null : fdName.trim();
    }

    public String getFdNamePinyin() {
        return fdNamePinyin;
    }

    public void setFdNamePinyin(String fdNamePinyin) {
        this.fdNamePinyin = fdNamePinyin == null ? null : fdNamePinyin.trim();
    }

    public Integer getFdOrder() {
        return fdOrder;
    }

    public void setFdOrder(Integer fdOrder) {
        this.fdOrder = fdOrder;
    }

    public String getFdNo() {
        return fdNo;
    }

    public void setFdNo(String fdNo) {
        this.fdNo = fdNo == null ? null : fdNo.trim();
    }

    public String getFdKeyword() {
        return fdKeyword;
    }

    public void setFdKeyword(String fdKeyword) {
        this.fdKeyword = fdKeyword == null ? null : fdKeyword.trim();
    }

    public Byte getFdIsAvailable() {
        return fdIsAvailable;
    }

    public void setFdIsAvailable(Byte fdIsAvailable) {
        this.fdIsAvailable = fdIsAvailable;
    }

    public Byte getFdIsAbandon() {
        return fdIsAbandon;
    }

    public void setFdIsAbandon(Byte fdIsAbandon) {
        this.fdIsAbandon = fdIsAbandon;
    }

    public Byte getFdIsBusiness() {
        return fdIsBusiness;
    }

    public void setFdIsBusiness(Byte fdIsBusiness) {
        this.fdIsBusiness = fdIsBusiness;
    }

    public String getFdImportInfo() {
        return fdImportInfo;
    }

    public void setFdImportInfo(String fdImportInfo) {
        this.fdImportInfo = fdImportInfo == null ? null : fdImportInfo.trim();
    }

    public Byte getFdFlagDeleted() {
        return fdFlagDeleted;
    }

    public void setFdFlagDeleted(Byte fdFlagDeleted) {
        this.fdFlagDeleted = fdFlagDeleted;
    }

    public String getFdLdapDn() {
        return fdLdapDn;
    }

    public void setFdLdapDn(String fdLdapDn) {
        this.fdLdapDn = fdLdapDn == null ? null : fdLdapDn.trim();
    }

    public String getFdMemo() {
        return fdMemo;
    }

    public void setFdMemo(String fdMemo) {
        this.fdMemo = fdMemo == null ? null : fdMemo.trim();
    }

    public String getFdHierarchyId() {
        return fdHierarchyId;
    }

    public void setFdHierarchyId(String fdHierarchyId) {
        this.fdHierarchyId = fdHierarchyId == null ? null : fdHierarchyId.trim();
    }

    public Date getFdCreateTime() {
        return fdCreateTime;
    }

    public void setFdCreateTime(Date fdCreateTime) {
        this.fdCreateTime = fdCreateTime;
    }

    public Date getFdAlterTime() {
        return fdAlterTime;
    }

    public void setFdAlterTime(Date fdAlterTime) {
        this.fdAlterTime = fdAlterTime;
    }

    public String getFdPortalLink() {
        return fdPortalLink;
    }

    public void setFdPortalLink(String fdPortalLink) {
        this.fdPortalLink = fdPortalLink == null ? null : fdPortalLink.trim();
    }

    public String getFdPortalName() {
        return fdPortalName;
    }

    public void setFdPortalName(String fdPortalName) {
        this.fdPortalName = fdPortalName == null ? null : fdPortalName.trim();
    }

    public String getFdThisLeaderid() {
        return fdThisLeaderid;
    }

    public void setFdThisLeaderid(String fdThisLeaderid) {
        this.fdThisLeaderid = fdThisLeaderid == null ? null : fdThisLeaderid.trim();
    }

    public String getFdSuperLeaderid() {
        return fdSuperLeaderid;
    }

    public void setFdSuperLeaderid(String fdSuperLeaderid) {
        this.fdSuperLeaderid = fdSuperLeaderid == null ? null : fdSuperLeaderid.trim();
    }

    public String getFdParentorgid() {
        return fdParentorgid;
    }

    public void setFdParentorgid(String fdParentorgid) {
        this.fdParentorgid = fdParentorgid == null ? null : fdParentorgid.trim();
    }

    public String getFdParentid() {
        return fdParentid;
    }

    public void setFdParentid(String fdParentid) {
        this.fdParentid = fdParentid == null ? null : fdParentid.trim();
    }

    public String getFdCateid() {
        return fdCateid;
    }

    public void setFdCateid(String fdCateid) {
        this.fdCateid = fdCateid == null ? null : fdCateid.trim();
    }

    public String getFdOrgEmail() {
        return fdOrgEmail;
    }

    public void setFdOrgEmail(String fdOrgEmail) {
        this.fdOrgEmail = fdOrgEmail == null ? null : fdOrgEmail.trim();
    }

    public Byte getAuthReaderFlag() {
        return authReaderFlag;
    }

    public void setAuthReaderFlag(Byte authReaderFlag) {
        this.authReaderFlag = authReaderFlag;
    }

    public String getFdNameSimplePinyin() {
        return fdNameSimplePinyin;
    }

    public void setFdNameSimplePinyin(String fdNameSimplePinyin) {
        this.fdNameSimplePinyin = fdNameSimplePinyin == null ? null : fdNameSimplePinyin.trim();
    }
}