package springboot.domain.eip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysOrgPersonExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysOrgPersonExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andFdIdIsNull() {
            addCriterion("fd_id is null");
            return (Criteria) this;
        }

        public Criteria andFdIdIsNotNull() {
            addCriterion("fd_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdIdEqualTo(String value) {
            addCriterion("fd_id =", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdNotEqualTo(String value) {
            addCriterion("fd_id <>", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdGreaterThan(String value) {
            addCriterion("fd_id >", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_id >=", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdLessThan(String value) {
            addCriterion("fd_id <", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdLessThanOrEqualTo(String value) {
            addCriterion("fd_id <=", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdLike(String value) {
            addCriterion("fd_id like", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdNotLike(String value) {
            addCriterion("fd_id not like", value, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdIn(List<String> values) {
            addCriterion("fd_id in", values, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdNotIn(List<String> values) {
            addCriterion("fd_id not in", values, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdBetween(String value1, String value2) {
            addCriterion("fd_id between", value1, value2, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdIdNotBetween(String value1, String value2) {
            addCriterion("fd_id not between", value1, value2, "fdId");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoIsNull() {
            addCriterion("fd_mobile_no is null");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoIsNotNull() {
            addCriterion("fd_mobile_no is not null");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoEqualTo(String value) {
            addCriterion("fd_mobile_no =", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoNotEqualTo(String value) {
            addCriterion("fd_mobile_no <>", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoGreaterThan(String value) {
            addCriterion("fd_mobile_no >", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_mobile_no >=", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoLessThan(String value) {
            addCriterion("fd_mobile_no <", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoLessThanOrEqualTo(String value) {
            addCriterion("fd_mobile_no <=", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoLike(String value) {
            addCriterion("fd_mobile_no like", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoNotLike(String value) {
            addCriterion("fd_mobile_no not like", value, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoIn(List<String> values) {
            addCriterion("fd_mobile_no in", values, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoNotIn(List<String> values) {
            addCriterion("fd_mobile_no not in", values, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoBetween(String value1, String value2) {
            addCriterion("fd_mobile_no between", value1, value2, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdMobileNoNotBetween(String value1, String value2) {
            addCriterion("fd_mobile_no not between", value1, value2, "fdMobileNo");
            return (Criteria) this;
        }

        public Criteria andFdEmailIsNull() {
            addCriterion("fd_email is null");
            return (Criteria) this;
        }

        public Criteria andFdEmailIsNotNull() {
            addCriterion("fd_email is not null");
            return (Criteria) this;
        }

        public Criteria andFdEmailEqualTo(String value) {
            addCriterion("fd_email =", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailNotEqualTo(String value) {
            addCriterion("fd_email <>", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailGreaterThan(String value) {
            addCriterion("fd_email >", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailGreaterThanOrEqualTo(String value) {
            addCriterion("fd_email >=", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailLessThan(String value) {
            addCriterion("fd_email <", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailLessThanOrEqualTo(String value) {
            addCriterion("fd_email <=", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailLike(String value) {
            addCriterion("fd_email like", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailNotLike(String value) {
            addCriterion("fd_email not like", value, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailIn(List<String> values) {
            addCriterion("fd_email in", values, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailNotIn(List<String> values) {
            addCriterion("fd_email not in", values, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailBetween(String value1, String value2) {
            addCriterion("fd_email between", value1, value2, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdEmailNotBetween(String value1, String value2) {
            addCriterion("fd_email not between", value1, value2, "fdEmail");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameIsNull() {
            addCriterion("fd_login_name is null");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameIsNotNull() {
            addCriterion("fd_login_name is not null");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameEqualTo(String value) {
            addCriterion("fd_login_name =", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameNotEqualTo(String value) {
            addCriterion("fd_login_name <>", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameGreaterThan(String value) {
            addCriterion("fd_login_name >", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_login_name >=", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameLessThan(String value) {
            addCriterion("fd_login_name <", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameLessThanOrEqualTo(String value) {
            addCriterion("fd_login_name <=", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameLike(String value) {
            addCriterion("fd_login_name like", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameNotLike(String value) {
            addCriterion("fd_login_name not like", value, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameIn(List<String> values) {
            addCriterion("fd_login_name in", values, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameNotIn(List<String> values) {
            addCriterion("fd_login_name not in", values, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameBetween(String value1, String value2) {
            addCriterion("fd_login_name between", value1, value2, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdLoginNameNotBetween(String value1, String value2) {
            addCriterion("fd_login_name not between", value1, value2, "fdLoginName");
            return (Criteria) this;
        }

        public Criteria andFdPasswordIsNull() {
            addCriterion("fd_password is null");
            return (Criteria) this;
        }

        public Criteria andFdPasswordIsNotNull() {
            addCriterion("fd_password is not null");
            return (Criteria) this;
        }

        public Criteria andFdPasswordEqualTo(String value) {
            addCriterion("fd_password =", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordNotEqualTo(String value) {
            addCriterion("fd_password <>", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordGreaterThan(String value) {
            addCriterion("fd_password >", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("fd_password >=", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordLessThan(String value) {
            addCriterion("fd_password <", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordLessThanOrEqualTo(String value) {
            addCriterion("fd_password <=", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordLike(String value) {
            addCriterion("fd_password like", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordNotLike(String value) {
            addCriterion("fd_password not like", value, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordIn(List<String> values) {
            addCriterion("fd_password in", values, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordNotIn(List<String> values) {
            addCriterion("fd_password not in", values, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordBetween(String value1, String value2) {
            addCriterion("fd_password between", value1, value2, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdPasswordNotBetween(String value1, String value2) {
            addCriterion("fd_password not between", value1, value2, "fdPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordIsNull() {
            addCriterion("fd_init_password is null");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordIsNotNull() {
            addCriterion("fd_init_password is not null");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordEqualTo(String value) {
            addCriterion("fd_init_password =", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordNotEqualTo(String value) {
            addCriterion("fd_init_password <>", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordGreaterThan(String value) {
            addCriterion("fd_init_password >", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("fd_init_password >=", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordLessThan(String value) {
            addCriterion("fd_init_password <", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordLessThanOrEqualTo(String value) {
            addCriterion("fd_init_password <=", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordLike(String value) {
            addCriterion("fd_init_password like", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordNotLike(String value) {
            addCriterion("fd_init_password not like", value, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordIn(List<String> values) {
            addCriterion("fd_init_password in", values, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordNotIn(List<String> values) {
            addCriterion("fd_init_password not in", values, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordBetween(String value1, String value2) {
            addCriterion("fd_init_password between", value1, value2, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdInitPasswordNotBetween(String value1, String value2) {
            addCriterion("fd_init_password not between", value1, value2, "fdInitPassword");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoIsNull() {
            addCriterion("fd_rtx_no is null");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoIsNotNull() {
            addCriterion("fd_rtx_no is not null");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoEqualTo(String value) {
            addCriterion("fd_rtx_no =", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoNotEqualTo(String value) {
            addCriterion("fd_rtx_no <>", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoGreaterThan(String value) {
            addCriterion("fd_rtx_no >", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_rtx_no >=", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoLessThan(String value) {
            addCriterion("fd_rtx_no <", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoLessThanOrEqualTo(String value) {
            addCriterion("fd_rtx_no <=", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoLike(String value) {
            addCriterion("fd_rtx_no like", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoNotLike(String value) {
            addCriterion("fd_rtx_no not like", value, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoIn(List<String> values) {
            addCriterion("fd_rtx_no in", values, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoNotIn(List<String> values) {
            addCriterion("fd_rtx_no not in", values, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoBetween(String value1, String value2) {
            addCriterion("fd_rtx_no between", value1, value2, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdRtxNoNotBetween(String value1, String value2) {
            addCriterion("fd_rtx_no not between", value1, value2, "fdRtxNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoIsNull() {
            addCriterion("fd_card_no is null");
            return (Criteria) this;
        }

        public Criteria andFdCardNoIsNotNull() {
            addCriterion("fd_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andFdCardNoEqualTo(String value) {
            addCriterion("fd_card_no =", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoNotEqualTo(String value) {
            addCriterion("fd_card_no <>", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoGreaterThan(String value) {
            addCriterion("fd_card_no >", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_card_no >=", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoLessThan(String value) {
            addCriterion("fd_card_no <", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoLessThanOrEqualTo(String value) {
            addCriterion("fd_card_no <=", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoLike(String value) {
            addCriterion("fd_card_no like", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoNotLike(String value) {
            addCriterion("fd_card_no not like", value, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoIn(List<String> values) {
            addCriterion("fd_card_no in", values, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoNotIn(List<String> values) {
            addCriterion("fd_card_no not in", values, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoBetween(String value1, String value2) {
            addCriterion("fd_card_no between", value1, value2, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdCardNoNotBetween(String value1, String value2) {
            addCriterion("fd_card_no not between", value1, value2, "fdCardNo");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberIsNull() {
            addCriterion("fd_attendance_card_number is null");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberIsNotNull() {
            addCriterion("fd_attendance_card_number is not null");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberEqualTo(String value) {
            addCriterion("fd_attendance_card_number =", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberNotEqualTo(String value) {
            addCriterion("fd_attendance_card_number <>", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberGreaterThan(String value) {
            addCriterion("fd_attendance_card_number >", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberGreaterThanOrEqualTo(String value) {
            addCriterion("fd_attendance_card_number >=", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberLessThan(String value) {
            addCriterion("fd_attendance_card_number <", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberLessThanOrEqualTo(String value) {
            addCriterion("fd_attendance_card_number <=", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberLike(String value) {
            addCriterion("fd_attendance_card_number like", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberNotLike(String value) {
            addCriterion("fd_attendance_card_number not like", value, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberIn(List<String> values) {
            addCriterion("fd_attendance_card_number in", values, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberNotIn(List<String> values) {
            addCriterion("fd_attendance_card_number not in", values, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberBetween(String value1, String value2) {
            addCriterion("fd_attendance_card_number between", value1, value2, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdAttendanceCardNumberNotBetween(String value1, String value2) {
            addCriterion("fd_attendance_card_number not between", value1, value2, "fdAttendanceCardNumber");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneIsNull() {
            addCriterion("fd_work_phone is null");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneIsNotNull() {
            addCriterion("fd_work_phone is not null");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneEqualTo(String value) {
            addCriterion("fd_work_phone =", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneNotEqualTo(String value) {
            addCriterion("fd_work_phone <>", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneGreaterThan(String value) {
            addCriterion("fd_work_phone >", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("fd_work_phone >=", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneLessThan(String value) {
            addCriterion("fd_work_phone <", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneLessThanOrEqualTo(String value) {
            addCriterion("fd_work_phone <=", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneLike(String value) {
            addCriterion("fd_work_phone like", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneNotLike(String value) {
            addCriterion("fd_work_phone not like", value, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneIn(List<String> values) {
            addCriterion("fd_work_phone in", values, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneNotIn(List<String> values) {
            addCriterion("fd_work_phone not in", values, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneBetween(String value1, String value2) {
            addCriterion("fd_work_phone between", value1, value2, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdWorkPhoneNotBetween(String value1, String value2) {
            addCriterion("fd_work_phone not between", value1, value2, "fdWorkPhone");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangIsNull() {
            addCriterion("fd_default_lang is null");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangIsNotNull() {
            addCriterion("fd_default_lang is not null");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangEqualTo(String value) {
            addCriterion("fd_default_lang =", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangNotEqualTo(String value) {
            addCriterion("fd_default_lang <>", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangGreaterThan(String value) {
            addCriterion("fd_default_lang >", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangGreaterThanOrEqualTo(String value) {
            addCriterion("fd_default_lang >=", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangLessThan(String value) {
            addCriterion("fd_default_lang <", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangLessThanOrEqualTo(String value) {
            addCriterion("fd_default_lang <=", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangLike(String value) {
            addCriterion("fd_default_lang like", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangNotLike(String value) {
            addCriterion("fd_default_lang not like", value, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangIn(List<String> values) {
            addCriterion("fd_default_lang in", values, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangNotIn(List<String> values) {
            addCriterion("fd_default_lang not in", values, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangBetween(String value1, String value2) {
            addCriterion("fd_default_lang between", value1, value2, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdDefaultLangNotBetween(String value1, String value2) {
            addCriterion("fd_default_lang not between", value1, value2, "fdDefaultLang");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoIsNull() {
            addCriterion("fd_wechat_no is null");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoIsNotNull() {
            addCriterion("fd_wechat_no is not null");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoEqualTo(String value) {
            addCriterion("fd_wechat_no =", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoNotEqualTo(String value) {
            addCriterion("fd_wechat_no <>", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoGreaterThan(String value) {
            addCriterion("fd_wechat_no >", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_wechat_no >=", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoLessThan(String value) {
            addCriterion("fd_wechat_no <", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoLessThanOrEqualTo(String value) {
            addCriterion("fd_wechat_no <=", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoLike(String value) {
            addCriterion("fd_wechat_no like", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoNotLike(String value) {
            addCriterion("fd_wechat_no not like", value, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoIn(List<String> values) {
            addCriterion("fd_wechat_no in", values, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoNotIn(List<String> values) {
            addCriterion("fd_wechat_no not in", values, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoBetween(String value1, String value2) {
            addCriterion("fd_wechat_no between", value1, value2, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdWechatNoNotBetween(String value1, String value2) {
            addCriterion("fd_wechat_no not between", value1, value2, "fdWechatNo");
            return (Criteria) this;
        }

        public Criteria andFdSexIsNull() {
            addCriterion("fd_sex is null");
            return (Criteria) this;
        }

        public Criteria andFdSexIsNotNull() {
            addCriterion("fd_sex is not null");
            return (Criteria) this;
        }

        public Criteria andFdSexEqualTo(String value) {
            addCriterion("fd_sex =", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexNotEqualTo(String value) {
            addCriterion("fd_sex <>", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexGreaterThan(String value) {
            addCriterion("fd_sex >", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexGreaterThanOrEqualTo(String value) {
            addCriterion("fd_sex >=", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexLessThan(String value) {
            addCriterion("fd_sex <", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexLessThanOrEqualTo(String value) {
            addCriterion("fd_sex <=", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexLike(String value) {
            addCriterion("fd_sex like", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexNotLike(String value) {
            addCriterion("fd_sex not like", value, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexIn(List<String> values) {
            addCriterion("fd_sex in", values, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexNotIn(List<String> values) {
            addCriterion("fd_sex not in", values, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexBetween(String value1, String value2) {
            addCriterion("fd_sex between", value1, value2, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdSexNotBetween(String value1, String value2) {
            addCriterion("fd_sex not between", value1, value2, "fdSex");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdIsNull() {
            addCriterion("fd_last_change_pwd is null");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdIsNotNull() {
            addCriterion("fd_last_change_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdEqualTo(Date value) {
            addCriterion("fd_last_change_pwd =", value, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdNotEqualTo(Date value) {
            addCriterion("fd_last_change_pwd <>", value, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdGreaterThan(Date value) {
            addCriterion("fd_last_change_pwd >", value, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_last_change_pwd >=", value, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdLessThan(Date value) {
            addCriterion("fd_last_change_pwd <", value, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdLessThanOrEqualTo(Date value) {
            addCriterion("fd_last_change_pwd <=", value, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdIn(List<Date> values) {
            addCriterion("fd_last_change_pwd in", values, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdNotIn(List<Date> values) {
            addCriterion("fd_last_change_pwd not in", values, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdBetween(Date value1, Date value2) {
            addCriterion("fd_last_change_pwd between", value1, value2, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLastChangePwdNotBetween(Date value1, Date value2) {
            addCriterion("fd_last_change_pwd not between", value1, value2, "fdLastChangePwd");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeIsNull() {
            addCriterion("fd_lock_time is null");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeIsNotNull() {
            addCriterion("fd_lock_time is not null");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeEqualTo(Date value) {
            addCriterion("fd_lock_time =", value, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeNotEqualTo(Date value) {
            addCriterion("fd_lock_time <>", value, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeGreaterThan(Date value) {
            addCriterion("fd_lock_time >", value, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_lock_time >=", value, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeLessThan(Date value) {
            addCriterion("fd_lock_time <", value, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeLessThanOrEqualTo(Date value) {
            addCriterion("fd_lock_time <=", value, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeIn(List<Date> values) {
            addCriterion("fd_lock_time in", values, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeNotIn(List<Date> values) {
            addCriterion("fd_lock_time not in", values, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeBetween(Date value1, Date value2) {
            addCriterion("fd_lock_time between", value1, value2, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdLockTimeNotBetween(Date value1, Date value2) {
            addCriterion("fd_lock_time not between", value1, value2, "fdLockTime");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdIsNull() {
            addCriterion("fd_staffing_level_id is null");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdIsNotNull() {
            addCriterion("fd_staffing_level_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdEqualTo(String value) {
            addCriterion("fd_staffing_level_id =", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdNotEqualTo(String value) {
            addCriterion("fd_staffing_level_id <>", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdGreaterThan(String value) {
            addCriterion("fd_staffing_level_id >", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_staffing_level_id >=", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdLessThan(String value) {
            addCriterion("fd_staffing_level_id <", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdLessThanOrEqualTo(String value) {
            addCriterion("fd_staffing_level_id <=", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdLike(String value) {
            addCriterion("fd_staffing_level_id like", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdNotLike(String value) {
            addCriterion("fd_staffing_level_id not like", value, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdIn(List<String> values) {
            addCriterion("fd_staffing_level_id in", values, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdNotIn(List<String> values) {
            addCriterion("fd_staffing_level_id not in", values, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdBetween(String value1, String value2) {
            addCriterion("fd_staffing_level_id between", value1, value2, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdStaffingLevelIdNotBetween(String value1, String value2) {
            addCriterion("fd_staffing_level_id not between", value1, value2, "fdStaffingLevelId");
            return (Criteria) this;
        }

        public Criteria andFdShortNoIsNull() {
            addCriterion("fd_short_no is null");
            return (Criteria) this;
        }

        public Criteria andFdShortNoIsNotNull() {
            addCriterion("fd_short_no is not null");
            return (Criteria) this;
        }

        public Criteria andFdShortNoEqualTo(String value) {
            addCriterion("fd_short_no =", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoNotEqualTo(String value) {
            addCriterion("fd_short_no <>", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoGreaterThan(String value) {
            addCriterion("fd_short_no >", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_short_no >=", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoLessThan(String value) {
            addCriterion("fd_short_no <", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoLessThanOrEqualTo(String value) {
            addCriterion("fd_short_no <=", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoLike(String value) {
            addCriterion("fd_short_no like", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoNotLike(String value) {
            addCriterion("fd_short_no not like", value, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoIn(List<String> values) {
            addCriterion("fd_short_no in", values, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoNotIn(List<String> values) {
            addCriterion("fd_short_no not in", values, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoBetween(String value1, String value2) {
            addCriterion("fd_short_no between", value1, value2, "fdShortNo");
            return (Criteria) this;
        }

        public Criteria andFdShortNoNotBetween(String value1, String value2) {
            addCriterion("fd_short_no not between", value1, value2, "fdShortNo");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}