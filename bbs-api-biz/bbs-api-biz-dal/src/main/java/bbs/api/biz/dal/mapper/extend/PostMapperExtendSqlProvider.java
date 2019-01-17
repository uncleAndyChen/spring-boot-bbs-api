package bbs.api.biz.dal.mapper.extend;

import bbs.api.biz.model.request.CommonRequest;

public class PostMapperExtendSqlProvider {
    public String getPostList(CommonRequest commonRequest) {
        // mybatis 动态 order by 不生效，所以，排序规则直接写在 sql 语句里了，而不是通过参数，orderBy 参数可不传。
        String sql = "select postId, userId, title, vote, updatedAt from bbsPost order by updatedAt DESC";

        if (commonRequest.getOrderBy().length() > 0) {
            sql += " order by #{orderBy,jdbcType=VARCHAR}";
        }

        if (commonRequest.isOrderByDesc()) {
            sql += " desc";
        }

        if (commonRequest.getRecordsLimit() > 0) {
            sql += " limit #{recordsLimit,jdbcType=INTEGER}";
        }

        return sql;
    }
}
