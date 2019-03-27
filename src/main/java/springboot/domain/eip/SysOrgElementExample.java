package springboot.domain.eip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysOrgElementExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SysOrgElementExample() {
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

        public Criteria andFdOrgTypeIsNull() {
            addCriterion("fd_org_type is null");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeIsNotNull() {
            addCriterion("fd_org_type is not null");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeEqualTo(Integer value) {
            addCriterion("fd_org_type =", value, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeNotEqualTo(Integer value) {
            addCriterion("fd_org_type <>", value, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeGreaterThan(Integer value) {
            addCriterion("fd_org_type >", value, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_org_type >=", value, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeLessThan(Integer value) {
            addCriterion("fd_org_type <", value, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeLessThanOrEqualTo(Integer value) {
            addCriterion("fd_org_type <=", value, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeIn(List<Integer> values) {
            addCriterion("fd_org_type in", values, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeNotIn(List<Integer> values) {
            addCriterion("fd_org_type not in", values, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeBetween(Integer value1, Integer value2) {
            addCriterion("fd_org_type between", value1, value2, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdOrgTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_org_type not between", value1, value2, "fdOrgType");
            return (Criteria) this;
        }

        public Criteria andFdNameIsNull() {
            addCriterion("fd_name is null");
            return (Criteria) this;
        }

        public Criteria andFdNameIsNotNull() {
            addCriterion("fd_name is not null");
            return (Criteria) this;
        }

        public Criteria andFdNameEqualTo(String value) {
            addCriterion("fd_name =", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameNotEqualTo(String value) {
            addCriterion("fd_name <>", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameGreaterThan(String value) {
            addCriterion("fd_name >", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_name >=", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameLessThan(String value) {
            addCriterion("fd_name <", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameLessThanOrEqualTo(String value) {
            addCriterion("fd_name <=", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameLike(String value) {
            addCriterion("fd_name like", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameNotLike(String value) {
            addCriterion("fd_name not like", value, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameIn(List<String> values) {
            addCriterion("fd_name in", values, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameNotIn(List<String> values) {
            addCriterion("fd_name not in", values, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameBetween(String value1, String value2) {
            addCriterion("fd_name between", value1, value2, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNameNotBetween(String value1, String value2) {
            addCriterion("fd_name not between", value1, value2, "fdName");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinIsNull() {
            addCriterion("fd_name_pinyin is null");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinIsNotNull() {
            addCriterion("fd_name_pinyin is not null");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinEqualTo(String value) {
            addCriterion("fd_name_pinyin =", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinNotEqualTo(String value) {
            addCriterion("fd_name_pinyin <>", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinGreaterThan(String value) {
            addCriterion("fd_name_pinyin >", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinGreaterThanOrEqualTo(String value) {
            addCriterion("fd_name_pinyin >=", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinLessThan(String value) {
            addCriterion("fd_name_pinyin <", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinLessThanOrEqualTo(String value) {
            addCriterion("fd_name_pinyin <=", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinLike(String value) {
            addCriterion("fd_name_pinyin like", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinNotLike(String value) {
            addCriterion("fd_name_pinyin not like", value, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinIn(List<String> values) {
            addCriterion("fd_name_pinyin in", values, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinNotIn(List<String> values) {
            addCriterion("fd_name_pinyin not in", values, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinBetween(String value1, String value2) {
            addCriterion("fd_name_pinyin between", value1, value2, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNamePinyinNotBetween(String value1, String value2) {
            addCriterion("fd_name_pinyin not between", value1, value2, "fdNamePinyin");
            return (Criteria) this;
        }

        public Criteria andFdOrderIsNull() {
            addCriterion("fd_order is null");
            return (Criteria) this;
        }

        public Criteria andFdOrderIsNotNull() {
            addCriterion("fd_order is not null");
            return (Criteria) this;
        }

        public Criteria andFdOrderEqualTo(Integer value) {
            addCriterion("fd_order =", value, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderNotEqualTo(Integer value) {
            addCriterion("fd_order <>", value, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderGreaterThan(Integer value) {
            addCriterion("fd_order >", value, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("fd_order >=", value, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderLessThan(Integer value) {
            addCriterion("fd_order <", value, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderLessThanOrEqualTo(Integer value) {
            addCriterion("fd_order <=", value, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderIn(List<Integer> values) {
            addCriterion("fd_order in", values, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderNotIn(List<Integer> values) {
            addCriterion("fd_order not in", values, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderBetween(Integer value1, Integer value2) {
            addCriterion("fd_order between", value1, value2, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("fd_order not between", value1, value2, "fdOrder");
            return (Criteria) this;
        }

        public Criteria andFdNoIsNull() {
            addCriterion("fd_no is null");
            return (Criteria) this;
        }

        public Criteria andFdNoIsNotNull() {
            addCriterion("fd_no is not null");
            return (Criteria) this;
        }

        public Criteria andFdNoEqualTo(String value) {
            addCriterion("fd_no =", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoNotEqualTo(String value) {
            addCriterion("fd_no <>", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoGreaterThan(String value) {
            addCriterion("fd_no >", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_no >=", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoLessThan(String value) {
            addCriterion("fd_no <", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoLessThanOrEqualTo(String value) {
            addCriterion("fd_no <=", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoLike(String value) {
            addCriterion("fd_no like", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoNotLike(String value) {
            addCriterion("fd_no not like", value, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoIn(List<String> values) {
            addCriterion("fd_no in", values, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoNotIn(List<String> values) {
            addCriterion("fd_no not in", values, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoBetween(String value1, String value2) {
            addCriterion("fd_no between", value1, value2, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdNoNotBetween(String value1, String value2) {
            addCriterion("fd_no not between", value1, value2, "fdNo");
            return (Criteria) this;
        }

        public Criteria andFdKeywordIsNull() {
            addCriterion("fd_keyword is null");
            return (Criteria) this;
        }

        public Criteria andFdKeywordIsNotNull() {
            addCriterion("fd_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andFdKeywordEqualTo(String value) {
            addCriterion("fd_keyword =", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordNotEqualTo(String value) {
            addCriterion("fd_keyword <>", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordGreaterThan(String value) {
            addCriterion("fd_keyword >", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("fd_keyword >=", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordLessThan(String value) {
            addCriterion("fd_keyword <", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordLessThanOrEqualTo(String value) {
            addCriterion("fd_keyword <=", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordLike(String value) {
            addCriterion("fd_keyword like", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordNotLike(String value) {
            addCriterion("fd_keyword not like", value, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordIn(List<String> values) {
            addCriterion("fd_keyword in", values, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordNotIn(List<String> values) {
            addCriterion("fd_keyword not in", values, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordBetween(String value1, String value2) {
            addCriterion("fd_keyword between", value1, value2, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdKeywordNotBetween(String value1, String value2) {
            addCriterion("fd_keyword not between", value1, value2, "fdKeyword");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableIsNull() {
            addCriterion("fd_is_available is null");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableIsNotNull() {
            addCriterion("fd_is_available is not null");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableEqualTo(Byte value) {
            addCriterion("fd_is_available =", value, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableNotEqualTo(Byte value) {
            addCriterion("fd_is_available <>", value, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableGreaterThan(Byte value) {
            addCriterion("fd_is_available >", value, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_is_available >=", value, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableLessThan(Byte value) {
            addCriterion("fd_is_available <", value, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableLessThanOrEqualTo(Byte value) {
            addCriterion("fd_is_available <=", value, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableIn(List<Byte> values) {
            addCriterion("fd_is_available in", values, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableNotIn(List<Byte> values) {
            addCriterion("fd_is_available not in", values, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableBetween(Byte value1, Byte value2) {
            addCriterion("fd_is_available between", value1, value2, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAvailableNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_is_available not between", value1, value2, "fdIsAvailable");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonIsNull() {
            addCriterion("fd_is_abandon is null");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonIsNotNull() {
            addCriterion("fd_is_abandon is not null");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonEqualTo(Byte value) {
            addCriterion("fd_is_abandon =", value, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonNotEqualTo(Byte value) {
            addCriterion("fd_is_abandon <>", value, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonGreaterThan(Byte value) {
            addCriterion("fd_is_abandon >", value, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_is_abandon >=", value, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonLessThan(Byte value) {
            addCriterion("fd_is_abandon <", value, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonLessThanOrEqualTo(Byte value) {
            addCriterion("fd_is_abandon <=", value, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonIn(List<Byte> values) {
            addCriterion("fd_is_abandon in", values, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonNotIn(List<Byte> values) {
            addCriterion("fd_is_abandon not in", values, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonBetween(Byte value1, Byte value2) {
            addCriterion("fd_is_abandon between", value1, value2, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsAbandonNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_is_abandon not between", value1, value2, "fdIsAbandon");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessIsNull() {
            addCriterion("fd_is_business is null");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessIsNotNull() {
            addCriterion("fd_is_business is not null");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessEqualTo(Byte value) {
            addCriterion("fd_is_business =", value, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessNotEqualTo(Byte value) {
            addCriterion("fd_is_business <>", value, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessGreaterThan(Byte value) {
            addCriterion("fd_is_business >", value, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_is_business >=", value, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessLessThan(Byte value) {
            addCriterion("fd_is_business <", value, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessLessThanOrEqualTo(Byte value) {
            addCriterion("fd_is_business <=", value, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessIn(List<Byte> values) {
            addCriterion("fd_is_business in", values, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessNotIn(List<Byte> values) {
            addCriterion("fd_is_business not in", values, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessBetween(Byte value1, Byte value2) {
            addCriterion("fd_is_business between", value1, value2, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdIsBusinessNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_is_business not between", value1, value2, "fdIsBusiness");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoIsNull() {
            addCriterion("fd_import_info is null");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoIsNotNull() {
            addCriterion("fd_import_info is not null");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoEqualTo(String value) {
            addCriterion("fd_import_info =", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoNotEqualTo(String value) {
            addCriterion("fd_import_info <>", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoGreaterThan(String value) {
            addCriterion("fd_import_info >", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_import_info >=", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoLessThan(String value) {
            addCriterion("fd_import_info <", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoLessThanOrEqualTo(String value) {
            addCriterion("fd_import_info <=", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoLike(String value) {
            addCriterion("fd_import_info like", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoNotLike(String value) {
            addCriterion("fd_import_info not like", value, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoIn(List<String> values) {
            addCriterion("fd_import_info in", values, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoNotIn(List<String> values) {
            addCriterion("fd_import_info not in", values, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoBetween(String value1, String value2) {
            addCriterion("fd_import_info between", value1, value2, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdImportInfoNotBetween(String value1, String value2) {
            addCriterion("fd_import_info not between", value1, value2, "fdImportInfo");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedIsNull() {
            addCriterion("fd_flag_deleted is null");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedIsNotNull() {
            addCriterion("fd_flag_deleted is not null");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedEqualTo(Byte value) {
            addCriterion("fd_flag_deleted =", value, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedNotEqualTo(Byte value) {
            addCriterion("fd_flag_deleted <>", value, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedGreaterThan(Byte value) {
            addCriterion("fd_flag_deleted >", value, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedGreaterThanOrEqualTo(Byte value) {
            addCriterion("fd_flag_deleted >=", value, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedLessThan(Byte value) {
            addCriterion("fd_flag_deleted <", value, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedLessThanOrEqualTo(Byte value) {
            addCriterion("fd_flag_deleted <=", value, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedIn(List<Byte> values) {
            addCriterion("fd_flag_deleted in", values, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedNotIn(List<Byte> values) {
            addCriterion("fd_flag_deleted not in", values, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedBetween(Byte value1, Byte value2) {
            addCriterion("fd_flag_deleted between", value1, value2, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdFlagDeletedNotBetween(Byte value1, Byte value2) {
            addCriterion("fd_flag_deleted not between", value1, value2, "fdFlagDeleted");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnIsNull() {
            addCriterion("fd_ldap_dn is null");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnIsNotNull() {
            addCriterion("fd_ldap_dn is not null");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnEqualTo(String value) {
            addCriterion("fd_ldap_dn =", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnNotEqualTo(String value) {
            addCriterion("fd_ldap_dn <>", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnGreaterThan(String value) {
            addCriterion("fd_ldap_dn >", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnGreaterThanOrEqualTo(String value) {
            addCriterion("fd_ldap_dn >=", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnLessThan(String value) {
            addCriterion("fd_ldap_dn <", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnLessThanOrEqualTo(String value) {
            addCriterion("fd_ldap_dn <=", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnLike(String value) {
            addCriterion("fd_ldap_dn like", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnNotLike(String value) {
            addCriterion("fd_ldap_dn not like", value, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnIn(List<String> values) {
            addCriterion("fd_ldap_dn in", values, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnNotIn(List<String> values) {
            addCriterion("fd_ldap_dn not in", values, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnBetween(String value1, String value2) {
            addCriterion("fd_ldap_dn between", value1, value2, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdLdapDnNotBetween(String value1, String value2) {
            addCriterion("fd_ldap_dn not between", value1, value2, "fdLdapDn");
            return (Criteria) this;
        }

        public Criteria andFdMemoIsNull() {
            addCriterion("fd_memo is null");
            return (Criteria) this;
        }

        public Criteria andFdMemoIsNotNull() {
            addCriterion("fd_memo is not null");
            return (Criteria) this;
        }

        public Criteria andFdMemoEqualTo(String value) {
            addCriterion("fd_memo =", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoNotEqualTo(String value) {
            addCriterion("fd_memo <>", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoGreaterThan(String value) {
            addCriterion("fd_memo >", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoGreaterThanOrEqualTo(String value) {
            addCriterion("fd_memo >=", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoLessThan(String value) {
            addCriterion("fd_memo <", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoLessThanOrEqualTo(String value) {
            addCriterion("fd_memo <=", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoLike(String value) {
            addCriterion("fd_memo like", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoNotLike(String value) {
            addCriterion("fd_memo not like", value, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoIn(List<String> values) {
            addCriterion("fd_memo in", values, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoNotIn(List<String> values) {
            addCriterion("fd_memo not in", values, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoBetween(String value1, String value2) {
            addCriterion("fd_memo between", value1, value2, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdMemoNotBetween(String value1, String value2) {
            addCriterion("fd_memo not between", value1, value2, "fdMemo");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdIsNull() {
            addCriterion("fd_hierarchy_id is null");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdIsNotNull() {
            addCriterion("fd_hierarchy_id is not null");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdEqualTo(String value) {
            addCriterion("fd_hierarchy_id =", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdNotEqualTo(String value) {
            addCriterion("fd_hierarchy_id <>", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdGreaterThan(String value) {
            addCriterion("fd_hierarchy_id >", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdGreaterThanOrEqualTo(String value) {
            addCriterion("fd_hierarchy_id >=", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdLessThan(String value) {
            addCriterion("fd_hierarchy_id <", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdLessThanOrEqualTo(String value) {
            addCriterion("fd_hierarchy_id <=", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdLike(String value) {
            addCriterion("fd_hierarchy_id like", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdNotLike(String value) {
            addCriterion("fd_hierarchy_id not like", value, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdIn(List<String> values) {
            addCriterion("fd_hierarchy_id in", values, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdNotIn(List<String> values) {
            addCriterion("fd_hierarchy_id not in", values, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdBetween(String value1, String value2) {
            addCriterion("fd_hierarchy_id between", value1, value2, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdHierarchyIdNotBetween(String value1, String value2) {
            addCriterion("fd_hierarchy_id not between", value1, value2, "fdHierarchyId");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeIsNull() {
            addCriterion("fd_create_time is null");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeIsNotNull() {
            addCriterion("fd_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeEqualTo(Date value) {
            addCriterion("fd_create_time =", value, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeNotEqualTo(Date value) {
            addCriterion("fd_create_time <>", value, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeGreaterThan(Date value) {
            addCriterion("fd_create_time >", value, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_create_time >=", value, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeLessThan(Date value) {
            addCriterion("fd_create_time <", value, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("fd_create_time <=", value, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeIn(List<Date> values) {
            addCriterion("fd_create_time in", values, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeNotIn(List<Date> values) {
            addCriterion("fd_create_time not in", values, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeBetween(Date value1, Date value2) {
            addCriterion("fd_create_time between", value1, value2, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("fd_create_time not between", value1, value2, "fdCreateTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeIsNull() {
            addCriterion("fd_alter_time is null");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeIsNotNull() {
            addCriterion("fd_alter_time is not null");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeEqualTo(Date value) {
            addCriterion("fd_alter_time =", value, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeNotEqualTo(Date value) {
            addCriterion("fd_alter_time <>", value, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeGreaterThan(Date value) {
            addCriterion("fd_alter_time >", value, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fd_alter_time >=", value, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeLessThan(Date value) {
            addCriterion("fd_alter_time <", value, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeLessThanOrEqualTo(Date value) {
            addCriterion("fd_alter_time <=", value, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeIn(List<Date> values) {
            addCriterion("fd_alter_time in", values, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeNotIn(List<Date> values) {
            addCriterion("fd_alter_time not in", values, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeBetween(Date value1, Date value2) {
            addCriterion("fd_alter_time between", value1, value2, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdAlterTimeNotBetween(Date value1, Date value2) {
            addCriterion("fd_alter_time not between", value1, value2, "fdAlterTime");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkIsNull() {
            addCriterion("fd_portal_link is null");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkIsNotNull() {
            addCriterion("fd_portal_link is not null");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkEqualTo(String value) {
            addCriterion("fd_portal_link =", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkNotEqualTo(String value) {
            addCriterion("fd_portal_link <>", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkGreaterThan(String value) {
            addCriterion("fd_portal_link >", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkGreaterThanOrEqualTo(String value) {
            addCriterion("fd_portal_link >=", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkLessThan(String value) {
            addCriterion("fd_portal_link <", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkLessThanOrEqualTo(String value) {
            addCriterion("fd_portal_link <=", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkLike(String value) {
            addCriterion("fd_portal_link like", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkNotLike(String value) {
            addCriterion("fd_portal_link not like", value, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkIn(List<String> values) {
            addCriterion("fd_portal_link in", values, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkNotIn(List<String> values) {
            addCriterion("fd_portal_link not in", values, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkBetween(String value1, String value2) {
            addCriterion("fd_portal_link between", value1, value2, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalLinkNotBetween(String value1, String value2) {
            addCriterion("fd_portal_link not between", value1, value2, "fdPortalLink");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameIsNull() {
            addCriterion("fd_portal_name is null");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameIsNotNull() {
            addCriterion("fd_portal_name is not null");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameEqualTo(String value) {
            addCriterion("fd_portal_name =", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameNotEqualTo(String value) {
            addCriterion("fd_portal_name <>", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameGreaterThan(String value) {
            addCriterion("fd_portal_name >", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameGreaterThanOrEqualTo(String value) {
            addCriterion("fd_portal_name >=", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameLessThan(String value) {
            addCriterion("fd_portal_name <", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameLessThanOrEqualTo(String value) {
            addCriterion("fd_portal_name <=", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameLike(String value) {
            addCriterion("fd_portal_name like", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameNotLike(String value) {
            addCriterion("fd_portal_name not like", value, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameIn(List<String> values) {
            addCriterion("fd_portal_name in", values, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameNotIn(List<String> values) {
            addCriterion("fd_portal_name not in", values, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameBetween(String value1, String value2) {
            addCriterion("fd_portal_name between", value1, value2, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdPortalNameNotBetween(String value1, String value2) {
            addCriterion("fd_portal_name not between", value1, value2, "fdPortalName");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidIsNull() {
            addCriterion("fd_this_leaderid is null");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidIsNotNull() {
            addCriterion("fd_this_leaderid is not null");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidEqualTo(String value) {
            addCriterion("fd_this_leaderid =", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidNotEqualTo(String value) {
            addCriterion("fd_this_leaderid <>", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidGreaterThan(String value) {
            addCriterion("fd_this_leaderid >", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidGreaterThanOrEqualTo(String value) {
            addCriterion("fd_this_leaderid >=", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidLessThan(String value) {
            addCriterion("fd_this_leaderid <", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidLessThanOrEqualTo(String value) {
            addCriterion("fd_this_leaderid <=", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidLike(String value) {
            addCriterion("fd_this_leaderid like", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidNotLike(String value) {
            addCriterion("fd_this_leaderid not like", value, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidIn(List<String> values) {
            addCriterion("fd_this_leaderid in", values, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidNotIn(List<String> values) {
            addCriterion("fd_this_leaderid not in", values, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidBetween(String value1, String value2) {
            addCriterion("fd_this_leaderid between", value1, value2, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdThisLeaderidNotBetween(String value1, String value2) {
            addCriterion("fd_this_leaderid not between", value1, value2, "fdThisLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidIsNull() {
            addCriterion("fd_super_leaderid is null");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidIsNotNull() {
            addCriterion("fd_super_leaderid is not null");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidEqualTo(String value) {
            addCriterion("fd_super_leaderid =", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidNotEqualTo(String value) {
            addCriterion("fd_super_leaderid <>", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidGreaterThan(String value) {
            addCriterion("fd_super_leaderid >", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidGreaterThanOrEqualTo(String value) {
            addCriterion("fd_super_leaderid >=", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidLessThan(String value) {
            addCriterion("fd_super_leaderid <", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidLessThanOrEqualTo(String value) {
            addCriterion("fd_super_leaderid <=", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidLike(String value) {
            addCriterion("fd_super_leaderid like", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidNotLike(String value) {
            addCriterion("fd_super_leaderid not like", value, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidIn(List<String> values) {
            addCriterion("fd_super_leaderid in", values, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidNotIn(List<String> values) {
            addCriterion("fd_super_leaderid not in", values, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidBetween(String value1, String value2) {
            addCriterion("fd_super_leaderid between", value1, value2, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdSuperLeaderidNotBetween(String value1, String value2) {
            addCriterion("fd_super_leaderid not between", value1, value2, "fdSuperLeaderid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidIsNull() {
            addCriterion("fd_parentorgid is null");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidIsNotNull() {
            addCriterion("fd_parentorgid is not null");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidEqualTo(String value) {
            addCriterion("fd_parentorgid =", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidNotEqualTo(String value) {
            addCriterion("fd_parentorgid <>", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidGreaterThan(String value) {
            addCriterion("fd_parentorgid >", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidGreaterThanOrEqualTo(String value) {
            addCriterion("fd_parentorgid >=", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidLessThan(String value) {
            addCriterion("fd_parentorgid <", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidLessThanOrEqualTo(String value) {
            addCriterion("fd_parentorgid <=", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidLike(String value) {
            addCriterion("fd_parentorgid like", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidNotLike(String value) {
            addCriterion("fd_parentorgid not like", value, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidIn(List<String> values) {
            addCriterion("fd_parentorgid in", values, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidNotIn(List<String> values) {
            addCriterion("fd_parentorgid not in", values, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidBetween(String value1, String value2) {
            addCriterion("fd_parentorgid between", value1, value2, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentorgidNotBetween(String value1, String value2) {
            addCriterion("fd_parentorgid not between", value1, value2, "fdParentorgid");
            return (Criteria) this;
        }

        public Criteria andFdParentidIsNull() {
            addCriterion("fd_parentid is null");
            return (Criteria) this;
        }

        public Criteria andFdParentidIsNotNull() {
            addCriterion("fd_parentid is not null");
            return (Criteria) this;
        }

        public Criteria andFdParentidEqualTo(String value) {
            addCriterion("fd_parentid =", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidNotEqualTo(String value) {
            addCriterion("fd_parentid <>", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidGreaterThan(String value) {
            addCriterion("fd_parentid >", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidGreaterThanOrEqualTo(String value) {
            addCriterion("fd_parentid >=", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidLessThan(String value) {
            addCriterion("fd_parentid <", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidLessThanOrEqualTo(String value) {
            addCriterion("fd_parentid <=", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidLike(String value) {
            addCriterion("fd_parentid like", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidNotLike(String value) {
            addCriterion("fd_parentid not like", value, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidIn(List<String> values) {
            addCriterion("fd_parentid in", values, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidNotIn(List<String> values) {
            addCriterion("fd_parentid not in", values, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidBetween(String value1, String value2) {
            addCriterion("fd_parentid between", value1, value2, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdParentidNotBetween(String value1, String value2) {
            addCriterion("fd_parentid not between", value1, value2, "fdParentid");
            return (Criteria) this;
        }

        public Criteria andFdCateidIsNull() {
            addCriterion("fd_cateid is null");
            return (Criteria) this;
        }

        public Criteria andFdCateidIsNotNull() {
            addCriterion("fd_cateid is not null");
            return (Criteria) this;
        }

        public Criteria andFdCateidEqualTo(String value) {
            addCriterion("fd_cateid =", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidNotEqualTo(String value) {
            addCriterion("fd_cateid <>", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidGreaterThan(String value) {
            addCriterion("fd_cateid >", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidGreaterThanOrEqualTo(String value) {
            addCriterion("fd_cateid >=", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidLessThan(String value) {
            addCriterion("fd_cateid <", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidLessThanOrEqualTo(String value) {
            addCriterion("fd_cateid <=", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidLike(String value) {
            addCriterion("fd_cateid like", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidNotLike(String value) {
            addCriterion("fd_cateid not like", value, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidIn(List<String> values) {
            addCriterion("fd_cateid in", values, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidNotIn(List<String> values) {
            addCriterion("fd_cateid not in", values, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidBetween(String value1, String value2) {
            addCriterion("fd_cateid between", value1, value2, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdCateidNotBetween(String value1, String value2) {
            addCriterion("fd_cateid not between", value1, value2, "fdCateid");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailIsNull() {
            addCriterion("fd_org_email is null");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailIsNotNull() {
            addCriterion("fd_org_email is not null");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailEqualTo(String value) {
            addCriterion("fd_org_email =", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailNotEqualTo(String value) {
            addCriterion("fd_org_email <>", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailGreaterThan(String value) {
            addCriterion("fd_org_email >", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailGreaterThanOrEqualTo(String value) {
            addCriterion("fd_org_email >=", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailLessThan(String value) {
            addCriterion("fd_org_email <", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailLessThanOrEqualTo(String value) {
            addCriterion("fd_org_email <=", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailLike(String value) {
            addCriterion("fd_org_email like", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailNotLike(String value) {
            addCriterion("fd_org_email not like", value, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailIn(List<String> values) {
            addCriterion("fd_org_email in", values, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailNotIn(List<String> values) {
            addCriterion("fd_org_email not in", values, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailBetween(String value1, String value2) {
            addCriterion("fd_org_email between", value1, value2, "fdOrgEmail");
            return (Criteria) this;
        }

        public Criteria andFdOrgEmailNotBetween(String value1, String value2) {
            addCriterion("fd_org_email not between", value1, value2, "fdOrgEmail");
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

        public Criteria andFdNameSimplePinyinIsNull() {
            addCriterion("fd_name_simple_pinyin is null");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinIsNotNull() {
            addCriterion("fd_name_simple_pinyin is not null");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinEqualTo(String value) {
            addCriterion("fd_name_simple_pinyin =", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinNotEqualTo(String value) {
            addCriterion("fd_name_simple_pinyin <>", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinGreaterThan(String value) {
            addCriterion("fd_name_simple_pinyin >", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinGreaterThanOrEqualTo(String value) {
            addCriterion("fd_name_simple_pinyin >=", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinLessThan(String value) {
            addCriterion("fd_name_simple_pinyin <", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinLessThanOrEqualTo(String value) {
            addCriterion("fd_name_simple_pinyin <=", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinLike(String value) {
            addCriterion("fd_name_simple_pinyin like", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinNotLike(String value) {
            addCriterion("fd_name_simple_pinyin not like", value, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinIn(List<String> values) {
            addCriterion("fd_name_simple_pinyin in", values, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinNotIn(List<String> values) {
            addCriterion("fd_name_simple_pinyin not in", values, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinBetween(String value1, String value2) {
            addCriterion("fd_name_simple_pinyin between", value1, value2, "fdNameSimplePinyin");
            return (Criteria) this;
        }

        public Criteria andFdNameSimplePinyinNotBetween(String value1, String value2) {
            addCriterion("fd_name_simple_pinyin not between", value1, value2, "fdNameSimplePinyin");
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