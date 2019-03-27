package springboot.domain.eip;

import java.io.Serializable;
import java.util.Date;

public class SysOrgPerson implements Serializable {
    private String fdId;

    private String fdMobileNo;

    private String fdEmail;

    private String fdLoginName;

    private String fdPassword;

    private String fdInitPassword;

    private String fdRtxNo;

    private String fdCardNo;

    private String fdAttendanceCardNumber;

    private String fdWorkPhone;

    private String fdDefaultLang;

    private String fdWechatNo;

    private String fdSex;

    private Date fdLastChangePwd;

    private Date fdLockTime;

    private String fdStaffingLevelId;

    private String fdShortNo;

    private static final long serialVersionUID = 1L;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public String getFdMobileNo() {
        return fdMobileNo;
    }

    public void setFdMobileNo(String fdMobileNo) {
        this.fdMobileNo = fdMobileNo == null ? null : fdMobileNo.trim();
    }

    public String getFdEmail() {
        return fdEmail;
    }

    public void setFdEmail(String fdEmail) {
        this.fdEmail = fdEmail == null ? null : fdEmail.trim();
    }

    public String getFdLoginName() {
        return fdLoginName;
    }

    public void setFdLoginName(String fdLoginName) {
        this.fdLoginName = fdLoginName == null ? null : fdLoginName.trim();
    }

    public String getFdPassword() {
        return fdPassword;
    }

    public void setFdPassword(String fdPassword) {
        this.fdPassword = fdPassword == null ? null : fdPassword.trim();
    }

    public String getFdInitPassword() {
        return fdInitPassword;
    }

    public void setFdInitPassword(String fdInitPassword) {
        this.fdInitPassword = fdInitPassword == null ? null : fdInitPassword.trim();
    }

    public String getFdRtxNo() {
        return fdRtxNo;
    }

    public void setFdRtxNo(String fdRtxNo) {
        this.fdRtxNo = fdRtxNo == null ? null : fdRtxNo.trim();
    }

    public String getFdCardNo() {
        return fdCardNo;
    }

    public void setFdCardNo(String fdCardNo) {
        this.fdCardNo = fdCardNo == null ? null : fdCardNo.trim();
    }

    public String getFdAttendanceCardNumber() {
        return fdAttendanceCardNumber;
    }

    public void setFdAttendanceCardNumber(String fdAttendanceCardNumber) {
        this.fdAttendanceCardNumber = fdAttendanceCardNumber == null ? null : fdAttendanceCardNumber.trim();
    }

    public String getFdWorkPhone() {
        return fdWorkPhone;
    }

    public void setFdWorkPhone(String fdWorkPhone) {
        this.fdWorkPhone = fdWorkPhone == null ? null : fdWorkPhone.trim();
    }

    public String getFdDefaultLang() {
        return fdDefaultLang;
    }

    public void setFdDefaultLang(String fdDefaultLang) {
        this.fdDefaultLang = fdDefaultLang == null ? null : fdDefaultLang.trim();
    }

    public String getFdWechatNo() {
        return fdWechatNo;
    }

    public void setFdWechatNo(String fdWechatNo) {
        this.fdWechatNo = fdWechatNo == null ? null : fdWechatNo.trim();
    }

    public String getFdSex() {
        return fdSex;
    }

    public void setFdSex(String fdSex) {
        this.fdSex = fdSex == null ? null : fdSex.trim();
    }

    public Date getFdLastChangePwd() {
        return fdLastChangePwd;
    }

    public void setFdLastChangePwd(Date fdLastChangePwd) {
        this.fdLastChangePwd = fdLastChangePwd;
    }

    public Date getFdLockTime() {
        return fdLockTime;
    }

    public void setFdLockTime(Date fdLockTime) {
        this.fdLockTime = fdLockTime;
    }

    public String getFdStaffingLevelId() {
        return fdStaffingLevelId;
    }

    public void setFdStaffingLevelId(String fdStaffingLevelId) {
        this.fdStaffingLevelId = fdStaffingLevelId == null ? null : fdStaffingLevelId.trim();
    }

    public String getFdShortNo() {
        return fdShortNo;
    }

    public void setFdShortNo(String fdShortNo) {
        this.fdShortNo = fdShortNo == null ? null : fdShortNo.trim();
    }
}