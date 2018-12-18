package bbs.api.common.model.response;

/**
 * 接口的响应视图
 */
public class ApiResponse<T> extends BaseResponse
{
    private T responseData;
    private String extendValue;

    public T getResponseData() {
        return responseData;
    }

    public ApiResponse(T t) {
        this.responseData = t;
    }

    public ApiResponse() {}

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }

    public String getExtendValue() {
        return extendValue;
    }

    public void setExtendValue(String extendValue) {
        this.extendValue = extendValue;
    }

}
