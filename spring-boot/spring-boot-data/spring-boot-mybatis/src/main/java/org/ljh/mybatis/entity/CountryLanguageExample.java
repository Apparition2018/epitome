package org.ljh.mybatis.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CountryLanguageExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CountryLanguageExample() {
        oredCriteria = new ArrayList<>();
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
        if (oredCriteria.isEmpty()) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        return new Criteria();
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    @Getter
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return !criteria.isEmpty();
        }

        public List<Criterion> getAllCriteria() {
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

        public Criteria andCountryCodeIsNull() {
            addCriterion("CountryCode is null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIsNotNull() {
            addCriterion("CountryCode is not null");
            return (Criteria) this;
        }

        public Criteria andCountryCodeEqualTo(String value) {
            addCriterion("CountryCode =", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotEqualTo(String value) {
            addCriterion("CountryCode <>", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThan(String value) {
            addCriterion("CountryCode >", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CountryCode >=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThan(String value) {
            addCriterion("CountryCode <", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLessThanOrEqualTo(String value) {
            addCriterion("CountryCode <=", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeLike(String value) {
            addCriterion("CountryCode like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotLike(String value) {
            addCriterion("CountryCode not like", value, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeIn(List<String> values) {
            addCriterion("CountryCode in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotIn(List<String> values) {
            addCriterion("CountryCode not in", values, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeBetween(String value1, String value2) {
            addCriterion("CountryCode between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andCountryCodeNotBetween(String value1, String value2) {
            addCriterion("CountryCode not between", value1, value2, "countryCode");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNull() {
            addCriterion("Language is null");
            return (Criteria) this;
        }

        public Criteria andLanguageIsNotNull() {
            addCriterion("Language is not null");
            return (Criteria) this;
        }

        public Criteria andLanguageEqualTo(String value) {
            addCriterion("Language =", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotEqualTo(String value) {
            addCriterion("Language <>", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThan(String value) {
            addCriterion("Language >", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageGreaterThanOrEqualTo(String value) {
            addCriterion("Language >=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThan(String value) {
            addCriterion("Language <", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLessThanOrEqualTo(String value) {
            addCriterion("Language <=", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageLike(String value) {
            addCriterion("Language like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotLike(String value) {
            addCriterion("Language not like", value, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageIn(List<String> values) {
            addCriterion("Language in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotIn(List<String> values) {
            addCriterion("Language not in", values, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageBetween(String value1, String value2) {
            addCriterion("Language between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andLanguageNotBetween(String value1, String value2) {
            addCriterion("Language not between", value1, value2, "language");
            return (Criteria) this;
        }

        public Criteria andIsOfficialIsNull() {
            addCriterion("IsOfficial is null");
            return (Criteria) this;
        }

        public Criteria andIsOfficialIsNotNull() {
            addCriterion("IsOfficial is not null");
            return (Criteria) this;
        }

        public Criteria andIsOfficialEqualTo(String value) {
            addCriterion("IsOfficial =", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialNotEqualTo(String value) {
            addCriterion("IsOfficial <>", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialGreaterThan(String value) {
            addCriterion("IsOfficial >", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialGreaterThanOrEqualTo(String value) {
            addCriterion("IsOfficial >=", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialLessThan(String value) {
            addCriterion("IsOfficial <", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialLessThanOrEqualTo(String value) {
            addCriterion("IsOfficial <=", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialLike(String value) {
            addCriterion("IsOfficial like", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialNotLike(String value) {
            addCriterion("IsOfficial not like", value, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialIn(List<String> values) {
            addCriterion("IsOfficial in", values, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialNotIn(List<String> values) {
            addCriterion("IsOfficial not in", values, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialBetween(String value1, String value2) {
            addCriterion("IsOfficial between", value1, value2, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andIsOfficialNotBetween(String value1, String value2) {
            addCriterion("IsOfficial not between", value1, value2, "isOfficial");
            return (Criteria) this;
        }

        public Criteria andPercentageIsNull() {
            addCriterion("Percentage is null");
            return (Criteria) this;
        }

        public Criteria andPercentageIsNotNull() {
            addCriterion("Percentage is not null");
            return (Criteria) this;
        }

        public Criteria andPercentageEqualTo(BigDecimal value) {
            addCriterion("Percentage =", value, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageNotEqualTo(BigDecimal value) {
            addCriterion("Percentage <>", value, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageGreaterThan(BigDecimal value) {
            addCriterion("Percentage >", value, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("Percentage >=", value, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageLessThan(BigDecimal value) {
            addCriterion("Percentage <", value, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageLessThanOrEqualTo(BigDecimal value) {
            addCriterion("Percentage <=", value, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageIn(List<BigDecimal> values) {
            addCriterion("Percentage in", values, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageNotIn(List<BigDecimal> values) {
            addCriterion("Percentage not in", values, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Percentage between", value1, value2, "percentage");
            return (Criteria) this;
        }

        public Criteria andPercentageNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("Percentage not between", value1, value2, "percentage");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    @Getter
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

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
