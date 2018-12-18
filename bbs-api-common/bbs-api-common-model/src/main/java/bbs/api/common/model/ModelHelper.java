package bbs.api.common.model;

import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.BaseResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

public class ModelHelper {
    public static BaseResponse getBaseResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum) {
        return getBaseResponseByResponseCodeEnum(responseCodeEnum, null);
    }

    public static BaseResponse getBaseResponseByResponseCodeEnumAndMessage(ResponseCodeEnum responseCodeEnum, String message) {
        BaseResponse baseResponse = getBaseResponseByResponseCodeEnum(responseCodeEnum, null);
        baseResponse.setMessage(baseResponse.getMessage() + message);
        return baseResponse;
    }

    public static BaseResponse getBaseResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum, Exception e) {
        return getApiResponseByResponseCodeEnum(responseCodeEnum, e);
    }

    public static ApiResponse getApiResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum) {
        return getApiResponseByResponseCodeEnum(responseCodeEnum, null);
    }

    public static ApiResponse getApiResponseByResponseCodeEnumAndMessage(ResponseCodeEnum responseCodeEnum, String message) {
        return getApiResponseByResponseCodeEnumAndMessage(responseCodeEnum, message, false);
    }

    public static ApiResponse getApiResponseByResponseCodeEnumAndMessage(ResponseCodeEnum responseCodeEnum, String message, boolean  isAddMessageToHead) {
        ApiResponse apiResponse = getApiResponseByResponseCodeEnum(responseCodeEnum, null);

        if (isAddMessageToHead) {
            apiResponse.setMessage(message + apiResponse.getMessage());
        } else {
            apiResponse.setMessage(apiResponse.getMessage() + message);
        }


        return apiResponse;
    }

    public static ApiResponse getApiResponseByResponseCodeEnum(ResponseCodeEnum responseCodeEnum, Exception e) {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(responseCodeEnum.getIndex());
        apiResponse.setMessage(responseCodeEnum.getName());

        if (e != null) {
            apiResponse.setErrMessage(getExceptionMessage(e));
        }

        return apiResponse;
    }

    private static String getExceptionMessage(Exception e) {
        Throwable throwable = e.getCause();
        String errMessage = "";

        if (throwable != null) {
            errMessage = e.getCause().toString();
        }

        if (errMessage == null || errMessage.length() == 0) {
            errMessage = e.getMessage();
        } else {
            errMessage += e.getMessage() == null ?  "" : "\n" + e.getMessage();
        }

        return errMessage;
    }
}
