package springboot.domain.eip;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class KmReviewMain implements Serializable {
    private String fdId;

    private Date fdLastModifiedTime;

    private String docSubject;

    private BigDecimal fdCurrentNumber;

    private String fdFeedbackModify;

    private BigDecimal fdFeedbackExecuted;

    private String fdNumber;

    private String docCreatorId;

    private Date docCreateTime;

    private String fdDepartmentId;

    private Date docPublishTime;

    private BigDecimal docReadCount;

    private String extendFilePath;

    private Byte fdUseForm;

    private String docStatus;

    private Byte authAttNodownload;

    private Byte authAttNocopy;

    private Byte authAttNoprint;

    private Byte authReaderFlag;

    private Byte fdChangeReaderFlag;

    private Byte fdChangeAtt;

    private String fdModelName;

    private String fdModelId;

    private String fdWorkId;

    private String fdPhaseId;

    private String fdTemplateId;

    private Integer docEvalCount;

    private String syncDataToCalendarTime;

    private Byte fdDisableMobileForm;

    private Byte fdRbpFlag;

    private String fdTitleRegulation;

    private String authAreaId;

    private Byte fdCanCircularize;

    private static final long serialVersionUID = 1L;

    public String getFdId() {
        return fdId;
    }

    public void setFdId(String fdId) {
        this.fdId = fdId == null ? null : fdId.trim();
    }

    public Date getFdLastModifiedTime() {
        return fdLastModifiedTime;
    }

    public void setFdLastModifiedTime(Date fdLastModifiedTime) {
        this.fdLastModifiedTime = fdLastModifiedTime;
    }

    public String getDocSubject() {
        return docSubject;
    }

    public void setDocSubject(String docSubject) {
        this.docSubject = docSubject == null ? null : docSubject.trim();
    }

    public BigDecimal getFdCurrentNumber() {
        return fdCurrentNumber;
    }

    public void setFdCurrentNumber(BigDecimal fdCurrentNumber) {
        this.fdCurrentNumber = fdCurrentNumber;
    }

    public String getFdFeedbackModify() {
        return fdFeedbackModify;
    }

    public void setFdFeedbackModify(String fdFeedbackModify) {
        this.fdFeedbackModify = fdFeedbackModify == null ? null : fdFeedbackModify.trim();
    }

    public BigDecimal getFdFeedbackExecuted() {
        return fdFeedbackExecuted;
    }

    public void setFdFeedbackExecuted(BigDecimal fdFeedbackExecuted) {
        this.fdFeedbackExecuted = fdFeedbackExecuted;
    }

    public String getFdNumber() {
        return fdNumber;
    }

    public void setFdNumber(String fdNumber) {
        this.fdNumber = fdNumber == null ? null : fdNumber.trim();
    }

    public String getDocCreatorId() {
        return docCreatorId;
    }

    public void setDocCreatorId(String docCreatorId) {
        this.docCreatorId = docCreatorId == null ? null : docCreatorId.trim();
    }

    public Date getDocCreateTime() {
        return docCreateTime;
    }

    public void setDocCreateTime(Date docCreateTime) {
        this.docCreateTime = docCreateTime;
    }

    public String getFdDepartmentId() {
        return fdDepartmentId;
    }

    public void setFdDepartmentId(String fdDepartmentId) {
        this.fdDepartmentId = fdDepartmentId == null ? null : fdDepartmentId.trim();
    }

    public Date getDocPublishTime() {
        return docPublishTime;
    }

    public void setDocPublishTime(Date docPublishTime) {
        this.docPublishTime = docPublishTime;
    }

    public BigDecimal getDocReadCount() {
        return docReadCount;
    }

    public void setDocReadCount(BigDecimal docReadCount) {
        this.docReadCount = docReadCount;
    }

    public String getExtendFilePath() {
        return extendFilePath;
    }

    public void setExtendFilePath(String extendFilePath) {
        this.extendFilePath = extendFilePath == null ? null : extendFilePath.trim();
    }

    public Byte getFdUseForm() {
        return fdUseForm;
    }

    public void setFdUseForm(Byte fdUseForm) {
        this.fdUseForm = fdUseForm;
    }

    public String getDocStatus() {
        return docStatus;
    }

    public void setDocStatus(String docStatus) {
        this.docStatus = docStatus == null ? null : docStatus.trim();
    }

    public Byte getAuthAttNodownload() {
        return authAttNodownload;
    }

    public void setAuthAttNodownload(Byte authAttNodownload) {
        this.authAttNodownload = authAttNodownload;
    }

    public Byte getAuthAttNocopy() {
        return authAttNocopy;
    }

    public void setAuthAttNocopy(Byte authAttNocopy) {
        this.authAttNocopy = authAttNocopy;
    }

    public Byte getAuthAttNoprint() {
        return authAttNoprint;
    }

    public void setAuthAttNoprint(Byte authAttNoprint) {
        this.authAttNoprint = authAttNoprint;
    }

    public Byte getAuthReaderFlag() {
        return authReaderFlag;
    }

    public void setAuthReaderFlag(Byte authReaderFlag) {
        this.authReaderFlag = authReaderFlag;
    }

    public Byte getFdChangeReaderFlag() {
        return fdChangeReaderFlag;
    }

    public void setFdChangeReaderFlag(Byte fdChangeReaderFlag) {
        this.fdChangeReaderFlag = fdChangeReaderFlag;
    }

    public Byte getFdChangeAtt() {
        return fdChangeAtt;
    }

    public void setFdChangeAtt(Byte fdChangeAtt) {
        this.fdChangeAtt = fdChangeAtt;
    }

    public String getFdModelName() {
        return fdModelName;
    }

    public void setFdModelName(String fdModelName) {
        this.fdModelName = fdModelName == null ? null : fdModelName.trim();
    }

    public String getFdModelId() {
        return fdModelId;
    }

    public void setFdModelId(String fdModelId) {
        this.fdModelId = fdModelId == null ? null : fdModelId.trim();
    }

    public String getFdWorkId() {
        return fdWorkId;
    }

    public void setFdWorkId(String fdWorkId) {
        this.fdWorkId = fdWorkId == null ? null : fdWorkId.trim();
    }

    public String getFdPhaseId() {
        return fdPhaseId;
    }

    public void setFdPhaseId(String fdPhaseId) {
        this.fdPhaseId = fdPhaseId == null ? null : fdPhaseId.trim();
    }

    public String getFdTemplateId() {
        return fdTemplateId;
    }

    public void setFdTemplateId(String fdTemplateId) {
        this.fdTemplateId = fdTemplateId == null ? null : fdTemplateId.trim();
    }

    public Integer getDocEvalCount() {
        return docEvalCount;
    }

    public void setDocEvalCount(Integer docEvalCount) {
        this.docEvalCount = docEvalCount;
    }

    public String getSyncDataToCalendarTime() {
        return syncDataToCalendarTime;
    }

    public void setSyncDataToCalendarTime(String syncDataToCalendarTime) {
        this.syncDataToCalendarTime = syncDataToCalendarTime == null ? null : syncDataToCalendarTime.trim();
    }

    public Byte getFdDisableMobileForm() {
        return fdDisableMobileForm;
    }

    public void setFdDisableMobileForm(Byte fdDisableMobileForm) {
        this.fdDisableMobileForm = fdDisableMobileForm;
    }

    public Byte getFdRbpFlag() {
        return fdRbpFlag;
    }

    public void setFdRbpFlag(Byte fdRbpFlag) {
        this.fdRbpFlag = fdRbpFlag;
    }

    public String getFdTitleRegulation() {
        return fdTitleRegulation;
    }

    public void setFdTitleRegulation(String fdTitleRegulation) {
        this.fdTitleRegulation = fdTitleRegulation == null ? null : fdTitleRegulation.trim();
    }

    public String getAuthAreaId() {
        return authAreaId;
    }

    public void setAuthAreaId(String authAreaId) {
        this.authAreaId = authAreaId == null ? null : authAreaId.trim();
    }

    public Byte getFdCanCircularize() {
        return fdCanCircularize;
    }

    public void setFdCanCircularize(Byte fdCanCircularize) {
        this.fdCanCircularize = fdCanCircularize;
    }
}