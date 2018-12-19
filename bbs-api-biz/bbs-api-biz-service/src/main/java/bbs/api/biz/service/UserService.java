package bbs.api.biz.service;

import bbs.api.biz.dal.service.UserDalService;
import bbs.api.biz.model.entity.User;
import bbs.api.biz.model.view.AuthorView;

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
}
