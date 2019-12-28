package bbs.api.biz.dal.mapper.extend;

import bbs.api.biz.model.request.CommonRequest;

public class PostMapperExtendSqlProvider {
    public String getPostList(CommonRequest commonRequest) {
        // mybatis 动态 order by 不生效，所以，排序规则直接写在 sql 语句里了，而不是通过参数，orderBy 参数可不传。
        String sql = "select post_id, user_id, title, vote, updated_at from bbs_post";

        if (commonRequest.getPostId() > 0) {
            sql = "select post_id, user_id, title, content, vote, updated_at from bbs_post " +
                    "where post_id=#{postId,jdbcType=INTEGER}";
        }

        sql += " order by updated_at DESC";

        if (commonRequest.getRecordsLimit() > 0) {
            sql += " limit #{recordsLimit,jdbcType=INTEGER}";
        }

        return sql;
    }

    /**
     * 如果用 int 会报错：Cannot invoke a method that holds named argument(@Param) using a specifying parameterObject
     */
    public String voteAdd(Integer postId) {
        return "update bbs_post set vote=vote+1 where post_id=#{postId,jdbcType=INTEGER}";
    }

    public String voteReduce(Integer postId) {
        return "update bbs_post set vote=vote-1 where post_id=#{postId,jdbcType=INTEGER}";
    }
}
