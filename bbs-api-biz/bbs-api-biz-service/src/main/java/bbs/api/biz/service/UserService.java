package bbs.api.biz.service;

import bbs.api.biz.dal.service.UserDalService;
import bbs.api.biz.model.entity.User;
import bbs.api.biz.model.request.UserLoginRequest;
import bbs.api.biz.model.response.UserLoginResponse;
import bbs.api.biz.model.view.AuthorView;
import bbs.api.biz.model.view.GlobalView;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    /**
     * 获取用户信息
     */
    public static AuthorView getAuthorView(int userId) {
        User user = UserDalService.getUserByPrimaryKey(userId);

        if (user == null) {
            return null;
        }

        return getAuthorViewByUser(user);
    }

    public static List<AuthorView> getAuthorViewList(List<Integer> userIds) {
        List<User> userList = UserDalService.getUserByUserIds(userIds);
        List<AuthorView> authorViewList = new ArrayList<>();

        for(User user : userList) {
            authorViewList.add(getAuthorViewByUser(user));
        }

        return authorViewList;
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
        userLoginResponse.setUserId(GlobalView.idPrefix + user.getUserId());

        return new ApiResponse(userLoginResponse);
    }

    private static AuthorView getAuthorViewByUser(User user) {
        AuthorView authorView = new AuthorView();
        authorView.setId(GlobalView.idPrefix + user.getUserId());
        authorView.setUsername(user.getUsername());

        return authorView;
    }
}
