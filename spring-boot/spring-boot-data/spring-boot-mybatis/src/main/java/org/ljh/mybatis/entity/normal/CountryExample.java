package org.ljh.mybatis.entity.normal;

import lombok.Getter;
import lombok.Setter;
import org.ljh.mybatis.entity.Continent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CountryExample {
    @Setter
    protected String orderByClause;

    @Setter
    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CountryExample() {
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

    protected abstract static class GeneratedCriteria {
        @Getter
        protected List<Criterion> continentCriteria;

        protected List<Criterion> allCriteria;

        @Getter
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
            continentCriteria = new ArrayList<>();
        }

        protected void addContinentCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            continentCriteria.add(new Criterion(condition, value, "org.ljh.mybatis.entity.handler.ContinentTypeHandler"));
            allCriteria = null;
        }

        protected void addContinentCriterion(String condition, Continent value1, Continent value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            continentCriteria.add(new Criterion(condition, value1, value2, "org.ljh.mybatis.entity.handler.ContinentTypeHandler"));
            allCriteria = null;
        }

        public boolean isValid() {
            return !criteria.isEmpty() || !continentCriteria.isEmpty();
        }

        public List<Criterion> getAllCriteria() {
            if (allCriteria == null) {
                allCriteria = new ArrayList<>();
                allCriteria.addAll(criteria);
                allCriteria.addAll(continentCriteria);
            }
            return allCriteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
            allCriteria = null;
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
            allCriteria = null;
        }

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andContinentIsNull() {
            addCriterion("Continent is null");
            return (Criteria) this;
        }

        public Criteria andContinentIsNotNull() {
            addCriterion("Continent is not null");
            return (Criteria) this;
        }

        public Criteria andContinentEqualTo(Continent value) {
            addContinentCriterion("Continent =", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotEqualTo(Continent value) {
            addContinentCriterion("Continent <>", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentGreaterThan(Continent value) {
            addContinentCriterion("Continent >", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentGreaterThanOrEqualTo(Continent value) {
            addContinentCriterion("Continent >=", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentLessThan(Continent value) {
            addContinentCriterion("Continent <", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentLessThanOrEqualTo(Continent value) {
            addContinentCriterion("Continent <=", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentLike(Continent value) {
            addContinentCriterion("Continent like", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotLike(Continent value) {
            addContinentCriterion("Continent not like", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentIn(List<Continent> values) {
            addContinentCriterion("Continent in", values, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotIn(List<Continent> values) {
            addContinentCriterion("Continent not in", values, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentBetween(Continent value1, Continent value2) {
            addContinentCriterion("Continent between", value1, value2, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotBetween(Continent value1, Continent value2) {
            addContinentCriterion("Continent not between", value1, value2, "continent");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("Region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("Region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("Region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("Region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("Region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("Region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("Region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("Region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("Region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("Region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("Region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("Region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("Region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("Region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaIsNull() {
            addCriterion("SurfaceArea is null");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaIsNotNull() {
            addCriterion("SurfaceArea is not null");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea =", value, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaNotEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea <>", value, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaGreaterThan(BigDecimal value) {
            addCriterion("SurfaceArea >", value, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea >=", value, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaLessThan(BigDecimal value) {
            addCriterion("SurfaceArea <", value, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea <=", value, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaIn(List<BigDecimal> values) {
            addCriterion("SurfaceArea in", values, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaNotIn(List<BigDecimal> values) {
            addCriterion("SurfaceArea not in", values, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SurfaceArea between", value1, value2, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andSurfaceAreaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SurfaceArea not between", value1, value2, "surfaceArea");
            return (Criteria) this;
        }

        public Criteria andIndepYearIsNull() {
            addCriterion("IndepYear is null");
            return (Criteria) this;
        }

        public Criteria andIndepYearIsNotNull() {
            addCriterion("IndepYear is not null");
            return (Criteria) this;
        }

        public Criteria andIndepYearEqualTo(Short value) {
            addCriterion("IndepYear =", value, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearNotEqualTo(Short value) {
            addCriterion("IndepYear <>", value, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearGreaterThan(Short value) {
            addCriterion("IndepYear >", value, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearGreaterThanOrEqualTo(Short value) {
            addCriterion("IndepYear >=", value, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearLessThan(Short value) {
            addCriterion("IndepYear <", value, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearLessThanOrEqualTo(Short value) {
            addCriterion("IndepYear <=", value, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearIn(List<Short> values) {
            addCriterion("IndepYear in", values, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearNotIn(List<Short> values) {
            addCriterion("IndepYear not in", values, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearBetween(Short value1, Short value2) {
            addCriterion("IndepYear between", value1, value2, "indepYear");
            return (Criteria) this;
        }

        public Criteria andIndepYearNotBetween(Short value1, Short value2) {
            addCriterion("IndepYear not between", value1, value2, "indepYear");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNull() {
            addCriterion("Population is null");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNotNull() {
            addCriterion("Population is not null");
            return (Criteria) this;
        }

        public Criteria andPopulationEqualTo(Integer value) {
            addCriterion("Population =", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotEqualTo(Integer value) {
            addCriterion("Population <>", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThan(Integer value) {
            addCriterion("Population >", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThanOrEqualTo(Integer value) {
            addCriterion("Population >=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThan(Integer value) {
            addCriterion("Population <", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThanOrEqualTo(Integer value) {
            addCriterion("Population <=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationIn(List<Integer> values) {
            addCriterion("Population in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotIn(List<Integer> values) {
            addCriterion("Population not in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationBetween(Integer value1, Integer value2) {
            addCriterion("Population between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotBetween(Integer value1, Integer value2) {
            addCriterion("Population not between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyIsNull() {
            addCriterion("LifeExpectancy is null");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyIsNotNull() {
            addCriterion("LifeExpectancy is not null");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy =", value, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyNotEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy <>", value, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyGreaterThan(BigDecimal value) {
            addCriterion("LifeExpectancy >", value, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy >=", value, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyLessThan(BigDecimal value) {
            addCriterion("LifeExpectancy <", value, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy <=", value, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyIn(List<BigDecimal> values) {
            addCriterion("LifeExpectancy in", values, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyNotIn(List<BigDecimal> values) {
            addCriterion("LifeExpectancy not in", values, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LifeExpectancy between", value1, value2, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeExpectancyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LifeExpectancy not between", value1, value2, "lifeExpectancy");
            return (Criteria) this;
        }

        public Criteria andGNPIsNull() {
            addCriterion("GNP is null");
            return (Criteria) this;
        }

        public Criteria andGNPIsNotNull() {
            addCriterion("GNP is not null");
            return (Criteria) this;
        }

        public Criteria andGNPEqualTo(BigDecimal value) {
            addCriterion("GNP =", value, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPNotEqualTo(BigDecimal value) {
            addCriterion("GNP <>", value, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPGreaterThan(BigDecimal value) {
            addCriterion("GNP >", value, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GNP >=", value, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPLessThan(BigDecimal value) {
            addCriterion("GNP <", value, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GNP <=", value, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPIn(List<BigDecimal> values) {
            addCriterion("GNP in", values, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPNotIn(List<BigDecimal> values) {
            addCriterion("GNP not in", values, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNP between", value1, value2, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNP not between", value1, value2, "GNP");
            return (Criteria) this;
        }

        public Criteria andGNPOldIsNull() {
            addCriterion("GNPOld is null");
            return (Criteria) this;
        }

        public Criteria andGNPOldIsNotNull() {
            addCriterion("GNPOld is not null");
            return (Criteria) this;
        }

        public Criteria andGNPOldEqualTo(BigDecimal value) {
            addCriterion("GNPOld =", value, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldNotEqualTo(BigDecimal value) {
            addCriterion("GNPOld <>", value, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldGreaterThan(BigDecimal value) {
            addCriterion("GNPOld >", value, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GNPOld >=", value, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldLessThan(BigDecimal value) {
            addCriterion("GNPOld <", value, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GNPOld <=", value, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldIn(List<BigDecimal> values) {
            addCriterion("GNPOld in", values, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldNotIn(List<BigDecimal> values) {
            addCriterion("GNPOld not in", values, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNPOld between", value1, value2, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andGNPOldNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNPOld not between", value1, value2, "GNPOld");
            return (Criteria) this;
        }

        public Criteria andLocalNameIsNull() {
            addCriterion("LocalName is null");
            return (Criteria) this;
        }

        public Criteria andLocalNameIsNotNull() {
            addCriterion("LocalName is not null");
            return (Criteria) this;
        }

        public Criteria andLocalNameEqualTo(String value) {
            addCriterion("LocalName =", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameNotEqualTo(String value) {
            addCriterion("LocalName <>", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameGreaterThan(String value) {
            addCriterion("LocalName >", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameGreaterThanOrEqualTo(String value) {
            addCriterion("LocalName >=", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameLessThan(String value) {
            addCriterion("LocalName <", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameLessThanOrEqualTo(String value) {
            addCriterion("LocalName <=", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameLike(String value) {
            addCriterion("LocalName like", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameNotLike(String value) {
            addCriterion("LocalName not like", value, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameIn(List<String> values) {
            addCriterion("LocalName in", values, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameNotIn(List<String> values) {
            addCriterion("LocalName not in", values, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameBetween(String value1, String value2) {
            addCriterion("LocalName between", value1, value2, "localName");
            return (Criteria) this;
        }

        public Criteria andLocalNameNotBetween(String value1, String value2) {
            addCriterion("LocalName not between", value1, value2, "localName");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormIsNull() {
            addCriterion("GovernmentForm is null");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormIsNotNull() {
            addCriterion("GovernmentForm is not null");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormEqualTo(String value) {
            addCriterion("GovernmentForm =", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormNotEqualTo(String value) {
            addCriterion("GovernmentForm <>", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormGreaterThan(String value) {
            addCriterion("GovernmentForm >", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormGreaterThanOrEqualTo(String value) {
            addCriterion("GovernmentForm >=", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormLessThan(String value) {
            addCriterion("GovernmentForm <", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormLessThanOrEqualTo(String value) {
            addCriterion("GovernmentForm <=", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormLike(String value) {
            addCriterion("GovernmentForm like", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormNotLike(String value) {
            addCriterion("GovernmentForm not like", value, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormIn(List<String> values) {
            addCriterion("GovernmentForm in", values, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormNotIn(List<String> values) {
            addCriterion("GovernmentForm not in", values, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormBetween(String value1, String value2) {
            addCriterion("GovernmentForm between", value1, value2, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andGovernmentFormNotBetween(String value1, String value2) {
            addCriterion("GovernmentForm not between", value1, value2, "governmentForm");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateIsNull() {
            addCriterion("HeadOfState is null");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateIsNotNull() {
            addCriterion("HeadOfState is not null");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateEqualTo(String value) {
            addCriterion("HeadOfState =", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateNotEqualTo(String value) {
            addCriterion("HeadOfState <>", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateGreaterThan(String value) {
            addCriterion("HeadOfState >", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateGreaterThanOrEqualTo(String value) {
            addCriterion("HeadOfState >=", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateLessThan(String value) {
            addCriterion("HeadOfState <", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateLessThanOrEqualTo(String value) {
            addCriterion("HeadOfState <=", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateLike(String value) {
            addCriterion("HeadOfState like", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateNotLike(String value) {
            addCriterion("HeadOfState not like", value, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateIn(List<String> values) {
            addCriterion("HeadOfState in", values, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateNotIn(List<String> values) {
            addCriterion("HeadOfState not in", values, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateBetween(String value1, String value2) {
            addCriterion("HeadOfState between", value1, value2, "headOfState");
            return (Criteria) this;
        }

        public Criteria andHeadOfStateNotBetween(String value1, String value2) {
            addCriterion("HeadOfState not between", value1, value2, "headOfState");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNull() {
            addCriterion("Capital is null");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNotNull() {
            addCriterion("Capital is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalEqualTo(Integer value) {
            addCriterion("Capital =", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotEqualTo(Integer value) {
            addCriterion("Capital <>", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThan(Integer value) {
            addCriterion("Capital >", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThanOrEqualTo(Integer value) {
            addCriterion("Capital >=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThan(Integer value) {
            addCriterion("Capital <", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThanOrEqualTo(Integer value) {
            addCriterion("Capital <=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalIn(List<Integer> values) {
            addCriterion("Capital in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotIn(List<Integer> values) {
            addCriterion("Capital not in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalBetween(Integer value1, Integer value2) {
            addCriterion("Capital between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotBetween(Integer value1, Integer value2) {
            addCriterion("Capital not between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCode2IsNull() {
            addCriterion("Code2 is null");
            return (Criteria) this;
        }

        public Criteria andCode2IsNotNull() {
            addCriterion("Code2 is not null");
            return (Criteria) this;
        }

        public Criteria andCode2EqualTo(String value) {
            addCriterion("Code2 =", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotEqualTo(String value) {
            addCriterion("Code2 <>", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2GreaterThan(String value) {
            addCriterion("Code2 >", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2GreaterThanOrEqualTo(String value) {
            addCriterion("Code2 >=", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2LessThan(String value) {
            addCriterion("Code2 <", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2LessThanOrEqualTo(String value) {
            addCriterion("Code2 <=", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2Like(String value) {
            addCriterion("Code2 like", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotLike(String value) {
            addCriterion("Code2 not like", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2In(List<String> values) {
            addCriterion("Code2 in", values, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotIn(List<String> values) {
            addCriterion("Code2 not in", values, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2Between(String value1, String value2) {
            addCriterion("Code2 between", value1, value2, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotBetween(String value1, String value2) {
            addCriterion("Code2 not between", value1, value2, "code2");
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
