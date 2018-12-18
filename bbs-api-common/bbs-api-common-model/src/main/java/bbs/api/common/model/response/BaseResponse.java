package bbs.api.common.model.response;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    private int code;
    private String message;
    private String errMessage;

    /**
     * 重要：初始化默认值，不能删除
     */
    public BaseResponse() {
        this.code = ResponseCodeEnum.success.getIndex();
        this.message = ResponseCodeEnum.success.getName();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}
