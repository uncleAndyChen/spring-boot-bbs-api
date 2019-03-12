package bbs.api.biz.dal.mapper.extend;

import bbs.api.biz.model.request.CommonRequest;

public class PostMapperExtendSqlProvider {
    public String getPostList(CommonRequest commonRequest) {
        // mybatis 动态 order by 不生效，所以，排序规则直接写在 sql 语句里了，而不是通过参数，orderBy 参数可不传。
        String sql = "select postId, userId, title, vote, updatedAt from bbsPost order by updatedAt DESC";

        if (commonRequest.getRecordsLimit() > 0) {
            sql += " limit #{recordsLimit,jdbcType=INTEGER}";
        }

        return sql;
    }

    public String voteAdd(int postId) {
        return "update bbsPost set vote=vote+1 where postId=#{postId,jdbcType=INTEGER}";
    }

    public String voteReduce(int postId) {
        return "update bbsPost set vote=vote-1 where postId=#{postId,jdbcType=INTEGER}";
    }
}
