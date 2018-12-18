package bbs.api.biz.dal.mapper.extend;

import bbs.api.biz.model.request.CommonRequest;

public class PostMapperExtendSqlProvider {
    public String getPostList(CommonRequest commonRequest) {
        String sql = "select postId, userId, title, vote, updatedAt from bbsPost";

        if (commonRequest.getOrderBy().length() > 0) {
            sql += " order by " + commonRequest.getOrderBy();
        }

        if (commonRequest.getRecordsLimit() > 0) {
            sql += " limit " + commonRequest.getRecordsLimit();
        }

        return sql;
    }
}
