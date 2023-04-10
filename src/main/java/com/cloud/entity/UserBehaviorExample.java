package com.cloud.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserBehaviorExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserBehaviorExample() {
        oredCriteria = new ArrayList<>();
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
            criteria = new ArrayList<>();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBlogNameIsNull() {
            addCriterion("blog_name is null");
            return (Criteria) this;
        }

        public Criteria andBlogNameIsNotNull() {
            addCriterion("blog_name is not null");
            return (Criteria) this;
        }

        public Criteria andBlogNameEqualTo(String value) {
            addCriterion("blog_name =", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameNotEqualTo(String value) {
            addCriterion("blog_name <>", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameGreaterThan(String value) {
            addCriterion("blog_name >", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameGreaterThanOrEqualTo(String value) {
            addCriterion("blog_name >=", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameLessThan(String value) {
            addCriterion("blog_name <", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameLessThanOrEqualTo(String value) {
            addCriterion("blog_name <=", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameLike(String value) {
            addCriterion("blog_name like", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameNotLike(String value) {
            addCriterion("blog_name not like", value, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameIn(List<String> values) {
            addCriterion("blog_name in", values, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameNotIn(List<String> values) {
            addCriterion("blog_name not in", values, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameBetween(String value1, String value2) {
            addCriterion("blog_name between", value1, value2, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogNameNotBetween(String value1, String value2) {
            addCriterion("blog_name not between", value1, value2, "blogName");
            return (Criteria) this;
        }

        public Criteria andBlogIdIsNull() {
            addCriterion("blog_id is null");
            return (Criteria) this;
        }

        public Criteria andBlogIdIsNotNull() {
            addCriterion("blog_id is not null");
            return (Criteria) this;
        }

        public Criteria andBlogIdEqualTo(Long value) {
            addCriterion("blog_id =", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdNotEqualTo(Long value) {
            addCriterion("blog_id <>", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdGreaterThan(Long value) {
            addCriterion("blog_id >", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdGreaterThanOrEqualTo(Long value) {
            addCriterion("blog_id >=", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdLessThan(Long value) {
            addCriterion("blog_id <", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdLessThanOrEqualTo(Long value) {
            addCriterion("blog_id <=", value, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdIn(List<Long> values) {
            addCriterion("blog_id in", values, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdNotIn(List<Long> values) {
            addCriterion("blog_id not in", values, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdBetween(Long value1, Long value2) {
            addCriterion("blog_id between", value1, value2, "blogId");
            return (Criteria) this;
        }

        public Criteria andBlogIdNotBetween(Long value1, Long value2) {
            addCriterion("blog_id not between", value1, value2, "blogId");
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

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andClickTimeIsNull() {
            addCriterion("click_time is null");
            return (Criteria) this;
        }

        public Criteria andClickTimeIsNotNull() {
            addCriterion("click_time is not null");
            return (Criteria) this;
        }

        public Criteria andClickTimeEqualTo(Integer value) {
            addCriterion("click_time =", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeNotEqualTo(Integer value) {
            addCriterion("click_time <>", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeGreaterThan(Integer value) {
            addCriterion("click_time >", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("click_time >=", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeLessThan(Integer value) {
            addCriterion("click_time <", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeLessThanOrEqualTo(Integer value) {
            addCriterion("click_time <=", value, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeIn(List<Integer> values) {
            addCriterion("click_time in", values, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeNotIn(List<Integer> values) {
            addCriterion("click_time not in", values, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeBetween(Integer value1, Integer value2) {
            addCriterion("click_time between", value1, value2, "clickTime");
            return (Criteria) this;
        }

        public Criteria andClickTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("click_time not between", value1, value2, "clickTime");
            return (Criteria) this;
        }

        public Criteria andIsLikeIsNull() {
            addCriterion("is_like is null");
            return (Criteria) this;
        }

        public Criteria andIsLikeIsNotNull() {
            addCriterion("is_like is not null");
            return (Criteria) this;
        }

        public Criteria andIsLikeEqualTo(Boolean value) {
            addCriterion("is_like =", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotEqualTo(Boolean value) {
            addCriterion("is_like <>", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeGreaterThan(Boolean value) {
            addCriterion("is_like >", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_like >=", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeLessThan(Boolean value) {
            addCriterion("is_like <", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeLessThanOrEqualTo(Boolean value) {
            addCriterion("is_like <=", value, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeIn(List<Boolean> values) {
            addCriterion("is_like in", values, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotIn(List<Boolean> values) {
            addCriterion("is_like not in", values, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeBetween(Boolean value1, Boolean value2) {
            addCriterion("is_like between", value1, value2, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsLikeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_like not between", value1, value2, "isLike");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("is_delete is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("is_delete is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Boolean value) {
            addCriterion("is_delete =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Boolean value) {
            addCriterion("is_delete <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Boolean value) {
            addCriterion("is_delete >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_delete >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Boolean value) {
            addCriterion("is_delete <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("is_delete <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Boolean> values) {
            addCriterion("is_delete in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Boolean> values) {
            addCriterion("is_delete not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_delete not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andLikeLevelIsNull() {
            addCriterion("like_level is null");
            return (Criteria) this;
        }

        public Criteria andLikeLevelIsNotNull() {
            addCriterion("like_level is not null");
            return (Criteria) this;
        }

        public Criteria andLikeLevelEqualTo(Integer value) {
            addCriterion("like_level =", value, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelNotEqualTo(Integer value) {
            addCriterion("like_level <>", value, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelGreaterThan(Integer value) {
            addCriterion("like_level >", value, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("like_level >=", value, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelLessThan(Integer value) {
            addCriterion("like_level <", value, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelLessThanOrEqualTo(Integer value) {
            addCriterion("like_level <=", value, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelIn(List<Integer> values) {
            addCriterion("like_level in", values, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelNotIn(List<Integer> values) {
            addCriterion("like_level not in", values, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelBetween(Integer value1, Integer value2) {
            addCriterion("like_level between", value1, value2, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andLikeLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("like_level not between", value1, value2, "likeLevel");
            return (Criteria) this;
        }

        public Criteria andRateIsNull() {
            addCriterion("rate is null");
            return (Criteria) this;
        }

        public Criteria andRateIsNotNull() {
            addCriterion("rate is not null");
            return (Criteria) this;
        }

        public Criteria andRateEqualTo(Double value) {
            addCriterion("rate =", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotEqualTo(Double value) {
            addCriterion("rate <>", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThan(Double value) {
            addCriterion("rate >", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateGreaterThanOrEqualTo(Double value) {
            addCriterion("rate >=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThan(Double value) {
            addCriterion("rate <", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateLessThanOrEqualTo(Double value) {
            addCriterion("rate <=", value, "rate");
            return (Criteria) this;
        }

        public Criteria andRateIn(List<Double> values) {
            addCriterion("rate in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotIn(List<Double> values) {
            addCriterion("rate not in", values, "rate");
            return (Criteria) this;
        }

        public Criteria andRateBetween(Double value1, Double value2) {
            addCriterion("rate between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andRateNotBetween(Double value1, Double value2) {
            addCriterion("rate not between", value1, value2, "rate");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNull() {
            addCriterion("modify_time is null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIsNotNull() {
            addCriterion("modify_time is not null");
            return (Criteria) this;
        }

        public Criteria andModifyTimeEqualTo(Date value) {
            addCriterion("modify_time =", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotEqualTo(Date value) {
            addCriterion("modify_time <>", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThan(Date value) {
            addCriterion("modify_time >", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modify_time >=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThan(Date value) {
            addCriterion("modify_time <", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("modify_time <=", value, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeIn(List<Date> values) {
            addCriterion("modify_time in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotIn(List<Date> values) {
            addCriterion("modify_time not in", values, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeBetween(Date value1, Date value2) {
            addCriterion("modify_time between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andModifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("modify_time not between", value1, value2, "modifyTime");
            return (Criteria) this;
        }

        public Criteria andIsCollectIsNull() {
            addCriterion("is_collect is null");
            return (Criteria) this;
        }

        public Criteria andIsCollectIsNotNull() {
            addCriterion("is_collect is not null");
            return (Criteria) this;
        }

        public Criteria andIsCollectEqualTo(Boolean value) {
            addCriterion("is_collect =", value, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectNotEqualTo(Boolean value) {
            addCriterion("is_collect <>", value, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectGreaterThan(Boolean value) {
            addCriterion("is_collect >", value, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_collect >=", value, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectLessThan(Boolean value) {
            addCriterion("is_collect <", value, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectLessThanOrEqualTo(Boolean value) {
            addCriterion("is_collect <=", value, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectIn(List<Boolean> values) {
            addCriterion("is_collect in", values, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectNotIn(List<Boolean> values) {
            addCriterion("is_collect not in", values, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectBetween(Boolean value1, Boolean value2) {
            addCriterion("is_collect between", value1, value2, "isCollect");
            return (Criteria) this;
        }

        public Criteria andIsCollectNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_collect not between", value1, value2, "isCollect");
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