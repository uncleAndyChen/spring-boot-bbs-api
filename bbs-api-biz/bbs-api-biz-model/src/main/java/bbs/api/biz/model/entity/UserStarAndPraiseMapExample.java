package bbs.api.biz.model.entity;

import java.util.ArrayList;
import java.util.List;

public class UserStarAndPraiseMapExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserStarAndPraiseMapExample() {
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

        public Criteria andUserSapmIdIsNull() {
            addCriterion("user_SAPM_id is null");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdIsNotNull() {
            addCriterion("user_SAPM_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdEqualTo(Integer value) {
            addCriterion("user_SAPM_id =", value, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdNotEqualTo(Integer value) {
            addCriterion("user_SAPM_id <>", value, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdGreaterThan(Integer value) {
            addCriterion("user_SAPM_id >", value, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_SAPM_id >=", value, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdLessThan(Integer value) {
            addCriterion("user_SAPM_id <", value, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_SAPM_id <=", value, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdIn(List<Integer> values) {
            addCriterion("user_SAPM_id in", values, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdNotIn(List<Integer> values) {
            addCriterion("user_SAPM_id not in", values, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdBetween(Integer value1, Integer value2) {
            addCriterion("user_SAPM_id between", value1, value2, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserSapmIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_SAPM_id not between", value1, value2, "userSapmId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNull() {
            addCriterion("post_id is null");
            return (Criteria) this;
        }

        public Criteria andPostIdIsNotNull() {
            addCriterion("post_id is not null");
            return (Criteria) this;
        }

        public Criteria andPostIdEqualTo(Integer value) {
            addCriterion("post_id =", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotEqualTo(Integer value) {
            addCriterion("post_id <>", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThan(Integer value) {
            addCriterion("post_id >", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("post_id >=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThan(Integer value) {
            addCriterion("post_id <", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdLessThanOrEqualTo(Integer value) {
            addCriterion("post_id <=", value, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdIn(List<Integer> values) {
            addCriterion("post_id in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotIn(List<Integer> values) {
            addCriterion("post_id not in", values, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdBetween(Integer value1, Integer value2) {
            addCriterion("post_id between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andPostIdNotBetween(Integer value1, Integer value2) {
            addCriterion("post_id not between", value1, value2, "postId");
            return (Criteria) this;
        }

        public Criteria andMapTypeIsNull() {
            addCriterion("map_type is null");
            return (Criteria) this;
        }

        public Criteria andMapTypeIsNotNull() {
            addCriterion("map_type is not null");
            return (Criteria) this;
        }

        public Criteria andMapTypeEqualTo(Integer value) {
            addCriterion("map_type =", value, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeNotEqualTo(Integer value) {
            addCriterion("map_type <>", value, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeGreaterThan(Integer value) {
            addCriterion("map_type >", value, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("map_type >=", value, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeLessThan(Integer value) {
            addCriterion("map_type <", value, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeLessThanOrEqualTo(Integer value) {
            addCriterion("map_type <=", value, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeIn(List<Integer> values) {
            addCriterion("map_type in", values, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeNotIn(List<Integer> values) {
            addCriterion("map_type not in", values, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeBetween(Integer value1, Integer value2) {
            addCriterion("map_type between", value1, value2, "mapType");
            return (Criteria) this;
        }

        public Criteria andMapTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("map_type not between", value1, value2, "mapType");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Integer value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Integer value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Integer value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Integer value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Integer value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Integer value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Integer> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Integer> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Integer value1, Integer value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Integer value1, Integer value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
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