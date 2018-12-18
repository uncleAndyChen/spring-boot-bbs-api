package bbs.api.biz.model.request;

public class CommonRequest {
    private int recordsLimit = 0;
    private String orderBy = "";
    private int whereFieldValue = 0;

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

    public int getWhereFieldValue() {
        return whereFieldValue;
    }

    public void setWhereFieldValue(int whereFieldValue) {
        this.whereFieldValue = whereFieldValue;
    }
}
