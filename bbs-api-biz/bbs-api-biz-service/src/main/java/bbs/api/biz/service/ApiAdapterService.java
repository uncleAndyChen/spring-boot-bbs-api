package bbs.api.biz.service;

import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApiAdapterService {
    public static ApiResponse getApiResponse(BaseRequest baseRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            return getApiResponseFactory(baseRequest, request, response);
        } catch (Exception e) {
            ErrorLogService.addErrorLogApiCall(e, baseRequest, request);
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.unknownException, e);
        }
    }

    private static ApiResponse getApiResponseFactory(BaseRequest baseRequest, HttpServletRequest request, HttpServletResponse response) {
        ApiResponse apiResponse = new ApiResponse();
        Object result;

        switch (baseRequest.getMethod()) {
            case "getPostList":
                result = PostService.getPostList(baseRequest);
                break;
            case "getCommentList":
                result = CommentService.getCommentList(baseRequest);
                break;
            default:
                return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
        }

        if (result == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        apiResponse.setResponseData(result);

        return apiResponse;
    }
}
