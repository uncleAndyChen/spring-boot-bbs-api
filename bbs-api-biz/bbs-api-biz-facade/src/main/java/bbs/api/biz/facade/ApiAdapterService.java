package bbs.api.biz.facade;

import bbs.api.biz.service.CommentService;
import bbs.api.biz.service.ErrorLogService;
import bbs.api.biz.service.PostService;
import bbs.api.biz.service.UserService;
import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

public class ApiAdapterService {
    public static ApiResponse getApiResponse(BaseRequest baseRequest) {
        try {
            return getApiResponseFactory(baseRequest);
        } catch (Exception e) {
            ErrorLogService.addErrorLogApiCall(e, baseRequest);
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.unknownException, e);
        }
    }

    private static ApiResponse getApiResponseFactory(BaseRequest baseRequest) {
         if (baseRequest.getMethod() == null) {
             return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
         }

       switch (baseRequest.getMethod()) {
           case "userLogin":
               return UserService.userLogin(baseRequest);
            case "getPostList":
                return PostService.getPostList(baseRequest);
           case "getPostByPrimaryKey":
               return PostService.getPostByPrimaryKey(baseRequest);
           case "insertPost":
               return PostService.insertPost(baseRequest);
           case "updatePost":
               return PostService.updatePost(baseRequest);
            case "getCommentList":
                return CommentService.getCommentList(baseRequest);
           case "createComment":
               return CommentService.insert(baseRequest);
            default:
                return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noSuchMethodException);
        }
    }
}
