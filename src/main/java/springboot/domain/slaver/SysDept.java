package springboot.domain.slaver;

import java.util.Date;
import javax.annotation.Generated;

public class SysDept {
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long deptId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Long parentId;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String ancestors;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String deptName;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Integer orderNum;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String leader;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String phone;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String email;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String status;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String delFlag;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String createBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date createTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private String updateBy;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    private Date updateTime;

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getDeptId() {
        return deptId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Long getParentId() {
        return parentId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getAncestors() {
        return ancestors;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDeptName() {
        return deptName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Integer getOrderNum() {
        return orderNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getLeader() {
        return leader;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setLeader(String leader) {
        this.leader = leader;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getPhone() {
        return phone;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getEmail() {
        return email;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setEmail(String email) {
        this.email = email;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getStatus() {
        return status;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setStatus(String status) {
        this.status = status;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getDelFlag() {
        return delFlag;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getCreateBy() {
        return createBy;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getCreateTime() {
        return createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String getUpdateBy() {
        return updateBy;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public Date getUpdateTime() {
        return updateTime;
    }

    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    @Generated("org.mybatis.generator.api.MyBatisGenerator")
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", deptId=").append(deptId);
        sb.append(", parentId=").append(parentId);
        sb.append(", ancestors=").append(ancestors);
        sb.append(", deptName=").append(deptName);
        sb.append(", orderNum=").append(orderNum);
        sb.append(", leader=").append(leader);
        sb.append(", phone=").append(phone);
        sb.append(", email=").append(email);
        sb.append(", status=").append(status);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}