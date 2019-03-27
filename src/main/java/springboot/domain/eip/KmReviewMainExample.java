package springboot.domain.eip;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KmReviewMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public KmReviewMainExample() {
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

        public Criteria andFdLastModifiedTimeIsNull() {
            addCriterion("fd_last_modified_time is null");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeIsNotNull() {
            addCriterion("fd_last_modified_time is not null");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeEqualTo(Date value) {
            addCriterion("fd_last_modified_time =", value, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeNotEqualTo(Date value) {
            addCriterion("fd_last_modified_time <>", value, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeGreaterThan(Date value) {
            addCriterion("fd_last_modified_time >", value, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_last_modified_time >=", value, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeLessThan(Date value) {
            addCriterion("fd_last_modified_time <", value, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeLessThanOrEqualTo(Date value) {
            addCriterion("fd_last_modified_time <=", value, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeIn(List<Date> values) {
            addCriterion("fd_last_modified_time in", values, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeNotIn(List<Date> values) {
            addCriterion("fd_last_modified_time not in", values, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeBetween(Date value1, Date value2) {
            addCriterion("fd_last_modified_time between", value1, value2, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andFdLastModifiedTimeNotBetween(Date value1, Date value2) {
            addCriterion("fd_last_modified_time not between", value1, value2, "fdLastModifiedTime");
            return (Criteria) this;
        }

        public Criteria andDocSubjectIsNull() {
            addCriterion("doc_subject is null");
            return (Criteria) this;
        }

        public Criteria andDocSubjectIsNotNull() {
            addCriterion("doc_subject is not null");
            return (Criteria) this;
        }

        public Criteria andDocSubjectEqualTo(String value) {
            addCriterion("doc_subject =", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectNotEqualTo(String value) {
            addCriterion("doc_subject <>", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectGreaterThan(String value) {
            addCriterion("doc_subject >", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("doc_subject >=", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectLessThan(String value) {
            addCriterion("doc_subject <", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectLessThanOrEqualTo(String value) {
            addCriterion("doc_subject <=", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectLike(String value) {
            addCriterion("doc_subject like", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectNotLike(String value) {
            addCriterion("doc_subject not like", value, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectIn(List<String> values) {
            addCriterion("doc_subject in", values, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectNotIn(List<String> values) {
            addCriterion("doc_subject not in", values, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectBetween(String value1, String value2) {
            addCriterion("doc_subject between", value1, value2, "docSubject");
            return (Criteria) this;
        }

        public Criteria andDocSubjectNotBetween(String value1, String value2) {
            addCriterion("doc_subject not between", value1, value2, "docSubject");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberIsNull() {
            addCriterion("fd_current_number is null");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberIsNotNull() {
            addCriterion("fd_current_number is not null");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberEqualTo(BigDecimal value) {
            addCriterion("fd_current_number =", value, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberNotEqualTo(BigDecimal value) {
            addCriterion("fd_current_number <>", value, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberGreaterThan(BigDecimal value) {
            addCriterion("fd_current_number >", value, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fd_current_number >=", value, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberLessThan(BigDecimal value) {
            addCriterion("fd_current_number <", value, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fd_current_number <=", value, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberIn(List<BigDecimal> values) {
            addCriterion("fd_current_number in", values, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberNotIn(List<BigDecimal> values) {
            addCriterion("fd_current_number not in", values, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fd_current_number between", value1, value2, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdCurrentNumberNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fd_current_number not between", value1, value2, "fdCurrentNumber");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyIsNull() {
            addCriterion("fd_feedback_modify is null");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyIsNotNull() {
            addCriterion("fd_feedback_modify is not null");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyEqualTo(String value) {
            addCriterion("fd_feedback_modify =", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyNotEqualTo(String value) {
            addCriterion("fd_feedback_modify <>", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyGreaterThan(String value) {
            addCriterion("fd_feedback_modify >", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyGreaterThanOrEqualTo(String value) {
            addCriterion("fd_feedback_modify >=", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyLessThan(String value) {
            addCriterion("fd_feedback_modify <", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyLessThanOrEqualTo(String value) {
            addCriterion("fd_feedback_modify <=", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyLike(String value) {
            addCriterion("fd_feedback_modify like", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyNotLike(String value) {
            addCriterion("fd_feedback_modify not like", value, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyIn(List<String> values) {
            addCriterion("fd_feedback_modify in", values, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyNotIn(List<String> values) {
            addCriterion("fd_feedback_modify not in", values, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyBetween(String value1, String value2) {
            addCriterion("fd_feedback_modify between", value1, value2, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackModifyNotBetween(String value1, String value2) {
            addCriterion("fd_feedback_modify not between", value1, value2, "fdFeedbackModify");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedIsNull() {
            addCriterion("fd_feedback_executed is null");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedIsNotNull() {
            addCriterion("fd_feedback_executed is not null");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedEqualTo(BigDecimal value) {
            addCriterion("fd_feedback_executed =", value, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedNotEqualTo(BigDecimal value) {
            addCriterion("fd_feedback_executed <>", value, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedGreaterThan(BigDecimal value) {
            addCriterion("fd_feedback_executed >", value, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fd_feedback_executed >=", value, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedLessThan(BigDecimal value) {
            addCriterion("fd_feedback_executed <", value, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fd_feedback_executed <=", value, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedIn(List<BigDecimal> values) {
            addCriterion("fd_feedback_executed in", values, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedNotIn(List<BigDecimal> values) {
            addCriterion("fd_feedback_executed not in", values, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fd_feedback_executed between", value1, value2, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdFeedbackExecutedNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fd_feedback_executed not between", value1, value2, "fdFeedbackExecuted");
            return (Criteria) this;
        }

        public Criteria andFdNumberIsNull() {
            addCriterion("fd_number is null");
            return (Criteria) this;
        }

        public Criteria andFdNumberIsNotNull() {
            addCriterion("fd_number is not null");
            return (Criteria) this;
        }

        public Criteria andFdNumberEqualTo(String value) {
            addCriterion("fd_number =", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberNotEqualTo(String value) {
            addCriterion("fd_number <>", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberGreaterThan(String value) {
            addCriterion("fd_number >", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberGreaterThanOrEqualTo(String value) {
            addCriterion("fd_number >=", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberLessThan(String value) {
            addCriterion("fd_number <", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberLessThanOrEqualTo(String value) {
            addCriterion("fd_number <=", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberLike(String value) {
            addCriterion("fd_number like", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberNotLike(String value) {
            addCriterion("fd_number not like", value, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberIn(List<String> values) {
            addCriterion("fd_number in", values, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberNotIn(List<String> values) {
            addCriterion("fd_number not in", values, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberBetween(String value1, String value2) {
            addCriterion("fd_number between", value1, value2, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andFdNumberNotBetween(String value1, String value2) {
            addCriterion("fd_number not between", value1, value2, "fdNumber");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdIsNull() {
            addCriterion("doc_creator_id is null");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdIsNotNull() {
            addCriterion("doc_creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdEqualTo(String value) {
            addCriterion("doc_creator_id =", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotEqualTo(String value) {
            addCriterion("doc_creator_id <>", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdGreaterThan(String value) {
            addCriterion("doc_creator_id >", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("doc_creator_id >=", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdLessThan(String value) {
            addCriterion("doc_creator_id <", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("doc_creator_id <=", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdLike(String value) {
            addCriterion("doc_creator_id like", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotLike(String value) {
            addCriterion("doc_creator_id not like", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdIn(List<String> values) {
            addCriterion("doc_creator_id in", values, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotIn(List<String> values) {
            addCriterion("doc_creator_id not in", values, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdBetween(String value1, String value2) {
            addCriterion("doc_creator_id between", value1, value2, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotBetween(String value1, String value2) {
            addCriterion("doc_creator_id not between", value1, value2, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeIsNull() {
            addCriterion("doc_create_time is null");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeIsNotNull() {
            addCriterion("doc_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeEqualTo(Date value) {
            addCriterion("doc_create_time =", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeNotEqualTo(Date value) {
            addCriterion("doc_create_time <>", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeGreaterThan(Date value) {
            addCriterion("doc_create_time >", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("doc_create_time >=", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeLessThan(Date value) {
            addCriterion("doc_create_time <", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("doc_create_time <=", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeIn(List<Date> values) {
            addCriterion("doc_create_time in", values, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeNotIn(List<Date> values) {
            addCriterion("doc_create_time not in", values, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeBetween(Date value1, Date value2) {
            addCriterion("doc_create_time between", value1, value2, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("doc_create_time not between", value1, value2, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdIsNull() {
            addCriterion("fd_department_id is null");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdIsNotNull() {
            addCriterion("fd_department_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdEqualTo(String value) {
            addCriterion("fd_department_id =", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdNotEqualTo(String value) {
            addCriterion("fd_department_id <>", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdGreaterThan(String value) {
            addCriterion("fd_department_id >", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_department_id >=", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdLessThan(String value) {
            addCriterion("fd_department_id <", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdLessThanOrEqualTo(String value) {
            addCriterion("fd_department_id <=", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdLike(String value) {
            addCriterion("fd_department_id like", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdNotLike(String value) {
            addCriterion("fd_department_id not like", value, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdIn(List<String> values) {
            addCriterion("fd_department_id in", values, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdNotIn(List<String> values) {
            addCriterion("fd_department_id not in", values, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdBetween(String value1, String value2) {
            addCriterion("fd_department_id between", value1, value2, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andFdDepartmentIdNotBetween(String value1, String value2) {
            addCriterion("fd_department_id not between", value1, value2, "fdDepartmentId");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeIsNull() {
            addCriterion("doc_publish_time is null");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeIsNotNull() {
            addCriterion("doc_publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeEqualTo(Date value) {
            addCriterion("doc_publish_time =", value, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeNotEqualTo(Date value) {
            addCriterion("doc_publish_time <>", value, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeGreaterThan(Date value) {
            addCriterion("doc_publish_time >", value, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("doc_publish_time >=", value, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeLessThan(Date value) {
            addCriterion("doc_publish_time <", value, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("doc_publish_time <=", value, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeIn(List<Date> values) {
            addCriterion("doc_publish_time in", values, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeNotIn(List<Date> values) {
            addCriterion("doc_publish_time not in", values, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeBetween(Date value1, Date value2) {
            addCriterion("doc_publish_time between", value1, value2, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("doc_publish_time not between", value1, value2, "docPublishTime");
            return (Criteria) this;
        }

        public Criteria andDocReadCountIsNull() {
            addCriterion("doc_read_count is null");
            return (Criteria) this;
        }

        public Criteria andDocReadCountIsNotNull() {
            addCriterion("doc_read_count is not null");
            return (Criteria) this;
        }

        public Criteria andDocReadCountEqualTo(BigDecimal value) {
            addCriterion("doc_read_count =", value, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountNotEqualTo(BigDecimal value) {
            addCriterion("doc_read_count <>", value, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountGreaterThan(BigDecimal value) {
            addCriterion("doc_read_count >", value, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("doc_read_count >=", value, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountLessThan(BigDecimal value) {
            addCriterion("doc_read_count <", value, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("doc_read_count <=", value, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountIn(List<BigDecimal> values) {
            addCriterion("doc_read_count in", values, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountNotIn(List<BigDecimal> values) {
            addCriterion("doc_read_count not in", values, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("doc_read_count between", value1, value2, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andDocReadCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("doc_read_count not between", value1, value2, "docReadCount");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathIsNull() {
            addCriterion("extend_file_path is null");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathIsNotNull() {
            addCriterion("extend_file_path is not null");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathEqualTo(String value) {
            addCriterion("extend_file_path =", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathNotEqualTo(String value) {
            addCriterion("extend_file_path <>", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathGreaterThan(String value) {
            addCriterion("extend_file_path >", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathGreaterThanOrEqualTo(String value) {
            addCriterion("extend_file_path >=", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathLessThan(String value) {
            addCriterion("extend_file_path <", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathLessThanOrEqualTo(String value) {
            addCriterion("extend_file_path <=", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathLike(String value) {
            addCriterion("extend_file_path like", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathNotLike(String value) {
            addCriterion("extend_file_path not like", value, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathIn(List<String> values) {
            addCriterion("extend_file_path in", values, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathNotIn(List<String> values) {
            addCriterion("extend_file_path not in", values, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathBetween(String value1, String value2) {
            addCriterion("extend_file_path between", value1, value2, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andExtendFilePathNotBetween(String value1, String value2) {
            addCriterion("extend_file_path not between", value1, value2, "extendFilePath");
            return (Criteria) this;
        }

        public Criteria andFdUseFormIsNull() {
            addCriterion("fd_use_form is null");
            return (Criteria) this;
        }

        public Criteria andFdUseFormIsNotNull() {
            addCriterion("fd_use_form is not null");
            return (Criteria) this;
        }

        public Criteria andFdUseFormEqualTo(Byte value) {
            addCriterion("fd_use_form =", value, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormNotEqualTo(Byte value) {
            addCriterion("fd_use_form <>", value, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormGreaterThan(Byte value) {
            addCriterion("fd_use_form >", value, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_use_form >=", value, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormLessThan(Byte value) {
            addCriterion("fd_use_form <", value, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormLessThanOrEqualTo(Byte value) {
            addCriterion("fd_use_form <=", value, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormIn(List<Byte> values) {
            addCriterion("fd_use_form in", values, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormNotIn(List<Byte> values) {
            addCriterion("fd_use_form not in", values, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormBetween(Byte value1, Byte value2) {
            addCriterion("fd_use_form between", value1, value2, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andFdUseFormNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_use_form not between", value1, value2, "fdUseForm");
            return (Criteria) this;
        }

        public Criteria andDocStatusIsNull() {
            addCriterion("doc_status is null");
            return (Criteria) this;
        }

        public Criteria andDocStatusIsNotNull() {
            addCriterion("doc_status is not null");
            return (Criteria) this;
        }

        public Criteria andDocStatusEqualTo(String value) {
            addCriterion("doc_status =", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotEqualTo(String value) {
            addCriterion("doc_status <>", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusGreaterThan(String value) {
            addCriterion("doc_status >", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusGreaterThanOrEqualTo(String value) {
            addCriterion("doc_status >=", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusLessThan(String value) {
            addCriterion("doc_status <", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusLessThanOrEqualTo(String value) {
            addCriterion("doc_status <=", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusLike(String value) {
            addCriterion("doc_status like", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotLike(String value) {
            addCriterion("doc_status not like", value, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusIn(List<String> values) {
            addCriterion("doc_status in", values, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotIn(List<String> values) {
            addCriterion("doc_status not in", values, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusBetween(String value1, String value2) {
            addCriterion("doc_status between", value1, value2, "docStatus");
            return (Criteria) this;
        }

        public Criteria andDocStatusNotBetween(String value1, String value2) {
            addCriterion("doc_status not between", value1, value2, "docStatus");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadIsNull() {
            addCriterion("auth_att_nodownload is null");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadIsNotNull() {
            addCriterion("auth_att_nodownload is not null");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadEqualTo(Byte value) {
            addCriterion("auth_att_nodownload =", value, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadNotEqualTo(Byte value) {
            addCriterion("auth_att_nodownload <>", value, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadGreaterThan(Byte value) {
            addCriterion("auth_att_nodownload >", value, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadGreaterThanOrEqualTo(Byte value) {
            addCriterion("auth_att_nodownload >=", value, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadLessThan(Byte value) {
            addCriterion("auth_att_nodownload <", value, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadLessThanOrEqualTo(Byte value) {
            addCriterion("auth_att_nodownload <=", value, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadIn(List<Byte> values) {
            addCriterion("auth_att_nodownload in", values, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadNotIn(List<Byte> values) {
            addCriterion("auth_att_nodownload not in", values, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadBetween(Byte value1, Byte value2) {
            addCriterion("auth_att_nodownload between", value1, value2, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNodownloadNotBetween(Byte value1, Byte value2) {
            addCriterion("auth_att_nodownload not between", value1, value2, "authAttNodownload");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyIsNull() {
            addCriterion("auth_att_nocopy is null");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyIsNotNull() {
            addCriterion("auth_att_nocopy is not null");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyEqualTo(Byte value) {
            addCriterion("auth_att_nocopy =", value, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyNotEqualTo(Byte value) {
            addCriterion("auth_att_nocopy <>", value, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyGreaterThan(Byte value) {
            addCriterion("auth_att_nocopy >", value, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyGreaterThanOrEqualTo(Byte value) {
            addCriterion("auth_att_nocopy >=", value, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyLessThan(Byte value) {
            addCriterion("auth_att_nocopy <", value, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyLessThanOrEqualTo(Byte value) {
            addCriterion("auth_att_nocopy <=", value, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyIn(List<Byte> values) {
            addCriterion("auth_att_nocopy in", values, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyNotIn(List<Byte> values) {
            addCriterion("auth_att_nocopy not in", values, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyBetween(Byte value1, Byte value2) {
            addCriterion("auth_att_nocopy between", value1, value2, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNocopyNotBetween(Byte value1, Byte value2) {
            addCriterion("auth_att_nocopy not between", value1, value2, "authAttNocopy");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintIsNull() {
            addCriterion("auth_att_noprint is null");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintIsNotNull() {
            addCriterion("auth_att_noprint is not null");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintEqualTo(Byte value) {
            addCriterion("auth_att_noprint =", value, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintNotEqualTo(Byte value) {
            addCriterion("auth_att_noprint <>", value, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintGreaterThan(Byte value) {
            addCriterion("auth_att_noprint >", value, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintGreaterThanOrEqualTo(Byte value) {
            addCriterion("auth_att_noprint >=", value, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintLessThan(Byte value) {
            addCriterion("auth_att_noprint <", value, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintLessThanOrEqualTo(Byte value) {
            addCriterion("auth_att_noprint <=", value, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintIn(List<Byte> values) {
            addCriterion("auth_att_noprint in", values, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintNotIn(List<Byte> values) {
            addCriterion("auth_att_noprint not in", values, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintBetween(Byte value1, Byte value2) {
            addCriterion("auth_att_noprint between", value1, value2, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthAttNoprintNotBetween(Byte value1, Byte value2) {
            addCriterion("auth_att_noprint not between", value1, value2, "authAttNoprint");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagIsNull() {
            addCriterion("auth_reader_flag is null");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagIsNotNull() {
            addCriterion("auth_reader_flag is not null");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagEqualTo(Byte value) {
            addCriterion("auth_reader_flag =", value, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagNotEqualTo(Byte value) {
            addCriterion("auth_reader_flag <>", value, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagGreaterThan(Byte value) {
            addCriterion("auth_reader_flag >", value, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("auth_reader_flag >=", value, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagLessThan(Byte value) {
            addCriterion("auth_reader_flag <", value, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagLessThanOrEqualTo(Byte value) {
            addCriterion("auth_reader_flag <=", value, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagIn(List<Byte> values) {
            addCriterion("auth_reader_flag in", values, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagNotIn(List<Byte> values) {
            addCriterion("auth_reader_flag not in", values, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagBetween(Byte value1, Byte value2) {
            addCriterion("auth_reader_flag between", value1, value2, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andAuthReaderFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("auth_reader_flag not between", value1, value2, "authReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagIsNull() {
            addCriterion("fd_change_reader_flag is null");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagIsNotNull() {
            addCriterion("fd_change_reader_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagEqualTo(Byte value) {
            addCriterion("fd_change_reader_flag =", value, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagNotEqualTo(Byte value) {
            addCriterion("fd_change_reader_flag <>", value, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagGreaterThan(Byte value) {
            addCriterion("fd_change_reader_flag >", value, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_change_reader_flag >=", value, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagLessThan(Byte value) {
            addCriterion("fd_change_reader_flag <", value, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagLessThanOrEqualTo(Byte value) {
            addCriterion("fd_change_reader_flag <=", value, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagIn(List<Byte> values) {
            addCriterion("fd_change_reader_flag in", values, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagNotIn(List<Byte> values) {
            addCriterion("fd_change_reader_flag not in", values, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagBetween(Byte value1, Byte value2) {
            addCriterion("fd_change_reader_flag between", value1, value2, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeReaderFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_change_reader_flag not between", value1, value2, "fdChangeReaderFlag");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttIsNull() {
            addCriterion("fd_change_att is null");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttIsNotNull() {
            addCriterion("fd_change_att is not null");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttEqualTo(Byte value) {
            addCriterion("fd_change_att =", value, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttNotEqualTo(Byte value) {
            addCriterion("fd_change_att <>", value, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttGreaterThan(Byte value) {
            addCriterion("fd_change_att >", value, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_change_att >=", value, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttLessThan(Byte value) {
            addCriterion("fd_change_att <", value, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttLessThanOrEqualTo(Byte value) {
            addCriterion("fd_change_att <=", value, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttIn(List<Byte> values) {
            addCriterion("fd_change_att in", values, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttNotIn(List<Byte> values) {
            addCriterion("fd_change_att not in", values, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttBetween(Byte value1, Byte value2) {
            addCriterion("fd_change_att between", value1, value2, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdChangeAttNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_change_att not between", value1, value2, "fdChangeAtt");
            return (Criteria) this;
        }

        public Criteria andFdModelNameIsNull() {
            addCriterion("fd_model_name is null");
            return (Criteria) this;
        }

        public Criteria andFdModelNameIsNotNull() {
            addCriterion("fd_model_name is not null");
            return (Criteria) this;
        }

        public Criteria andFdModelNameEqualTo(String value) {
            addCriterion("fd_model_name =", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameNotEqualTo(String value) {
            addCriterion("fd_model_name <>", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameGreaterThan(String value) {
            addCriterion("fd_model_name >", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_model_name >=", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameLessThan(String value) {
            addCriterion("fd_model_name <", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameLessThanOrEqualTo(String value) {
            addCriterion("fd_model_name <=", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameLike(String value) {
            addCriterion("fd_model_name like", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameNotLike(String value) {
            addCriterion("fd_model_name not like", value, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameIn(List<String> values) {
            addCriterion("fd_model_name in", values, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameNotIn(List<String> values) {
            addCriterion("fd_model_name not in", values, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameBetween(String value1, String value2) {
            addCriterion("fd_model_name between", value1, value2, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelNameNotBetween(String value1, String value2) {
            addCriterion("fd_model_name not between", value1, value2, "fdModelName");
            return (Criteria) this;
        }

        public Criteria andFdModelIdIsNull() {
            addCriterion("fd_model_id is null");
            return (Criteria) this;
        }

        public Criteria andFdModelIdIsNotNull() {
            addCriterion("fd_model_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdModelIdEqualTo(String value) {
            addCriterion("fd_model_id =", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdNotEqualTo(String value) {
            addCriterion("fd_model_id <>", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdGreaterThan(String value) {
            addCriterion("fd_model_id >", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_model_id >=", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdLessThan(String value) {
            addCriterion("fd_model_id <", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdLessThanOrEqualTo(String value) {
            addCriterion("fd_model_id <=", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdLike(String value) {
            addCriterion("fd_model_id like", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdNotLike(String value) {
            addCriterion("fd_model_id not like", value, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdIn(List<String> values) {
            addCriterion("fd_model_id in", values, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdNotIn(List<String> values) {
            addCriterion("fd_model_id not in", values, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdBetween(String value1, String value2) {
            addCriterion("fd_model_id between", value1, value2, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdModelIdNotBetween(String value1, String value2) {
            addCriterion("fd_model_id not between", value1, value2, "fdModelId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdIsNull() {
            addCriterion("fd_work_id is null");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdIsNotNull() {
            addCriterion("fd_work_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdEqualTo(String value) {
            addCriterion("fd_work_id =", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdNotEqualTo(String value) {
            addCriterion("fd_work_id <>", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdGreaterThan(String value) {
            addCriterion("fd_work_id >", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_work_id >=", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdLessThan(String value) {
            addCriterion("fd_work_id <", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdLessThanOrEqualTo(String value) {
            addCriterion("fd_work_id <=", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdLike(String value) {
            addCriterion("fd_work_id like", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdNotLike(String value) {
            addCriterion("fd_work_id not like", value, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdIn(List<String> values) {
            addCriterion("fd_work_id in", values, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdNotIn(List<String> values) {
            addCriterion("fd_work_id not in", values, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdBetween(String value1, String value2) {
            addCriterion("fd_work_id between", value1, value2, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdWorkIdNotBetween(String value1, String value2) {
            addCriterion("fd_work_id not between", value1, value2, "fdWorkId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdIsNull() {
            addCriterion("fd_phase_id is null");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdIsNotNull() {
            addCriterion("fd_phase_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdEqualTo(String value) {
            addCriterion("fd_phase_id =", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdNotEqualTo(String value) {
            addCriterion("fd_phase_id <>", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdGreaterThan(String value) {
            addCriterion("fd_phase_id >", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_phase_id >=", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdLessThan(String value) {
            addCriterion("fd_phase_id <", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdLessThanOrEqualTo(String value) {
            addCriterion("fd_phase_id <=", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdLike(String value) {
            addCriterion("fd_phase_id like", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdNotLike(String value) {
            addCriterion("fd_phase_id not like", value, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdIn(List<String> values) {
            addCriterion("fd_phase_id in", values, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdNotIn(List<String> values) {
            addCriterion("fd_phase_id not in", values, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdBetween(String value1, String value2) {
            addCriterion("fd_phase_id between", value1, value2, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdPhaseIdNotBetween(String value1, String value2) {
            addCriterion("fd_phase_id not between", value1, value2, "fdPhaseId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdIsNull() {
            addCriterion("fd_template_id is null");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdIsNotNull() {
            addCriterion("fd_template_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdEqualTo(String value) {
            addCriterion("fd_template_id =", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdNotEqualTo(String value) {
            addCriterion("fd_template_id <>", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdGreaterThan(String value) {
            addCriterion("fd_template_id >", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_template_id >=", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdLessThan(String value) {
            addCriterion("fd_template_id <", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdLessThanOrEqualTo(String value) {
            addCriterion("fd_template_id <=", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdLike(String value) {
            addCriterion("fd_template_id like", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdNotLike(String value) {
            addCriterion("fd_template_id not like", value, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdIn(List<String> values) {
            addCriterion("fd_template_id in", values, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdNotIn(List<String> values) {
            addCriterion("fd_template_id not in", values, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdBetween(String value1, String value2) {
            addCriterion("fd_template_id between", value1, value2, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andFdTemplateIdNotBetween(String value1, String value2) {
            addCriterion("fd_template_id not between", value1, value2, "fdTemplateId");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountIsNull() {
            addCriterion("doc_eval_count is null");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountIsNotNull() {
            addCriterion("doc_eval_count is not null");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountEqualTo(Integer value) {
            addCriterion("doc_eval_count =", value, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountNotEqualTo(Integer value) {
            addCriterion("doc_eval_count <>", value, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountGreaterThan(Integer value) {
            addCriterion("doc_eval_count >", value, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("doc_eval_count >=", value, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountLessThan(Integer value) {
            addCriterion("doc_eval_count <", value, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountLessThanOrEqualTo(Integer value) {
            addCriterion("doc_eval_count <=", value, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountIn(List<Integer> values) {
            addCriterion("doc_eval_count in", values, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountNotIn(List<Integer> values) {
            addCriterion("doc_eval_count not in", values, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountBetween(Integer value1, Integer value2) {
            addCriterion("doc_eval_count between", value1, value2, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andDocEvalCountNotBetween(Integer value1, Integer value2) {
            addCriterion("doc_eval_count not between", value1, value2, "docEvalCount");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeIsNull() {
            addCriterion("sync_data_to_calendar_time is null");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeIsNotNull() {
            addCriterion("sync_data_to_calendar_time is not null");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeEqualTo(String value) {
            addCriterion("sync_data_to_calendar_time =", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeNotEqualTo(String value) {
            addCriterion("sync_data_to_calendar_time <>", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeGreaterThan(String value) {
            addCriterion("sync_data_to_calendar_time >", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeGreaterThanOrEqualTo(String value) {
            addCriterion("sync_data_to_calendar_time >=", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeLessThan(String value) {
            addCriterion("sync_data_to_calendar_time <", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeLessThanOrEqualTo(String value) {
            addCriterion("sync_data_to_calendar_time <=", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeLike(String value) {
            addCriterion("sync_data_to_calendar_time like", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeNotLike(String value) {
            addCriterion("sync_data_to_calendar_time not like", value, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeIn(List<String> values) {
            addCriterion("sync_data_to_calendar_time in", values, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeNotIn(List<String> values) {
            addCriterion("sync_data_to_calendar_time not in", values, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeBetween(String value1, String value2) {
            addCriterion("sync_data_to_calendar_time between", value1, value2, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andSyncDataToCalendarTimeNotBetween(String value1, String value2) {
            addCriterion("sync_data_to_calendar_time not between", value1, value2, "syncDataToCalendarTime");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormIsNull() {
            addCriterion("fd_disable_mobile_form is null");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormIsNotNull() {
            addCriterion("fd_disable_mobile_form is not null");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormEqualTo(Byte value) {
            addCriterion("fd_disable_mobile_form =", value, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormNotEqualTo(Byte value) {
            addCriterion("fd_disable_mobile_form <>", value, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormGreaterThan(Byte value) {
            addCriterion("fd_disable_mobile_form >", value, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_disable_mobile_form >=", value, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormLessThan(Byte value) {
            addCriterion("fd_disable_mobile_form <", value, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormLessThanOrEqualTo(Byte value) {
            addCriterion("fd_disable_mobile_form <=", value, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormIn(List<Byte> values) {
            addCriterion("fd_disable_mobile_form in", values, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormNotIn(List<Byte> values) {
            addCriterion("fd_disable_mobile_form not in", values, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormBetween(Byte value1, Byte value2) {
            addCriterion("fd_disable_mobile_form between", value1, value2, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdDisableMobileFormNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_disable_mobile_form not between", value1, value2, "fdDisableMobileForm");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagIsNull() {
            addCriterion("fd_rbp_flag is null");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagIsNotNull() {
            addCriterion("fd_rbp_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagEqualTo(Byte value) {
            addCriterion("fd_rbp_flag =", value, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagNotEqualTo(Byte value) {
            addCriterion("fd_rbp_flag <>", value, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagGreaterThan(Byte value) {
            addCriterion("fd_rbp_flag >", value, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_rbp_flag >=", value, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagLessThan(Byte value) {
            addCriterion("fd_rbp_flag <", value, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagLessThanOrEqualTo(Byte value) {
            addCriterion("fd_rbp_flag <=", value, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagIn(List<Byte> values) {
            addCriterion("fd_rbp_flag in", values, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagNotIn(List<Byte> values) {
            addCriterion("fd_rbp_flag not in", values, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagBetween(Byte value1, Byte value2) {
            addCriterion("fd_rbp_flag between", value1, value2, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdRbpFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_rbp_flag not between", value1, value2, "fdRbpFlag");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationIsNull() {
            addCriterion("fd_title_regulation is null");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationIsNotNull() {
            addCriterion("fd_title_regulation is not null");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationEqualTo(String value) {
            addCriterion("fd_title_regulation =", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationNotEqualTo(String value) {
            addCriterion("fd_title_regulation <>", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationGreaterThan(String value) {
            addCriterion("fd_title_regulation >", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationGreaterThanOrEqualTo(String value) {
            addCriterion("fd_title_regulation >=", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationLessThan(String value) {
            addCriterion("fd_title_regulation <", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationLessThanOrEqualTo(String value) {
            addCriterion("fd_title_regulation <=", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationLike(String value) {
            addCriterion("fd_title_regulation like", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationNotLike(String value) {
            addCriterion("fd_title_regulation not like", value, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationIn(List<String> values) {
            addCriterion("fd_title_regulation in", values, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationNotIn(List<String> values) {
            addCriterion("fd_title_regulation not in", values, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationBetween(String value1, String value2) {
            addCriterion("fd_title_regulation between", value1, value2, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andFdTitleRegulationNotBetween(String value1, String value2) {
            addCriterion("fd_title_regulation not between", value1, value2, "fdTitleRegulation");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdIsNull() {
            addCriterion("auth_area_id is null");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdIsNotNull() {
            addCriterion("auth_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdEqualTo(String value) {
            addCriterion("auth_area_id =", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdNotEqualTo(String value) {
            addCriterion("auth_area_id <>", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdGreaterThan(String value) {
            addCriterion("auth_area_id >", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("auth_area_id >=", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdLessThan(String value) {
            addCriterion("auth_area_id <", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdLessThanOrEqualTo(String value) {
            addCriterion("auth_area_id <=", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdLike(String value) {
            addCriterion("auth_area_id like", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdNotLike(String value) {
            addCriterion("auth_area_id not like", value, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdIn(List<String> values) {
            addCriterion("auth_area_id in", values, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdNotIn(List<String> values) {
            addCriterion("auth_area_id not in", values, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdBetween(String value1, String value2) {
            addCriterion("auth_area_id between", value1, value2, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andAuthAreaIdNotBetween(String value1, String value2) {
            addCriterion("auth_area_id not between", value1, value2, "authAreaId");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeIsNull() {
            addCriterion("fd_can_circularize is null");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeIsNotNull() {
            addCriterion("fd_can_circularize is not null");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeEqualTo(Byte value) {
            addCriterion("fd_can_circularize =", value, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeNotEqualTo(Byte value) {
            addCriterion("fd_can_circularize <>", value, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeGreaterThan(Byte value) {
            addCriterion("fd_can_circularize >", value, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_can_circularize >=", value, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeLessThan(Byte value) {
            addCriterion("fd_can_circularize <", value, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeLessThanOrEqualTo(Byte value) {
            addCriterion("fd_can_circularize <=", value, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeIn(List<Byte> values) {
            addCriterion("fd_can_circularize in", values, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeNotIn(List<Byte> values) {
            addCriterion("fd_can_circularize not in", values, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeBetween(Byte value1, Byte value2) {
            addCriterion("fd_can_circularize between", value1, value2, "fdCanCircularize");
            return (Criteria) this;
        }

        public Criteria andFdCanCircularizeNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_can_circularize not between", value1, value2, "fdCanCircularize");
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