package bbs.api.biz.service;

import bbs.api.biz.dal.service.UserDalService;
import bbs.api.biz.model.entity.User;
import bbs.api.biz.model.request.UserLoginRequest;
import bbs.api.biz.model.response.UserLoginResponse;
import bbs.api.biz.model.view.AuthorView;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

public class UserService {
    /**
     * 获取用户信息
     */
    public static AuthorView getAuthorView(int userId) {
        User user = UserDalService.getUserByPrimaryKey(userId);

        if (user != null) {
            AuthorView authorView = new AuthorView();
            authorView.setId(user.getUserId());
            authorView.setUsername(user.getUsername());

            return authorView;
        }

        return null;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse userLogin(BaseRequest baseRequest) {
        UserLoginRequest userLoginRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserLoginRequest.class);

        if (userLoginRequest == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.parameterError);
        }

        User user = UserDalService.userLogin(userLoginRequest);

        if (user == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.usernameOrPasswordError);
        }

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        userLoginResponse.setUserId(user.getUserId());

        return new ApiResponse(userLoginResponse);
    }
}
