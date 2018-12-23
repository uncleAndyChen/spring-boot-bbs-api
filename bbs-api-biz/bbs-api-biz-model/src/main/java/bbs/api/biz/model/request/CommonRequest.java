package bbs.api.biz.model.request;

import bbs.api.biz.model.view.GlobalView;

public class CommonRequest {
    private int recordsLimit = 0;
    private String orderBy = "";
    private String whereFieldValue = "0";
    /**
     * length 大于0表示已登录用户的id，length 为0表示未登录。
     */
    private String userId = "";

    /**
     * 该方法不能删除重新生成，需要在这里去掉前缀
     */
    public void setWhereFieldValue(String whereFieldValue) {
        this.whereFieldValue = whereFieldValue.replace(GlobalView.idPrefix, "");
    }

    /**
     * 该方法不能删除重新生成，需要在这里去掉前缀
     */
    public void setUserId(String userId) {
        this.userId = userId.replace(GlobalView.idPrefix, "");
    }

    public int getRecordsLimit() {
        return recordsLimit;
    }

    public void setRecordsLimit(int recordsLimit) {
        this.recordsLimit = recordsLimit;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getWhereFieldValue() {
        return whereFieldValue;
    }

    public String getUserId() {
        return userId;
    }
}
