package bbs.api.biz.dal.service;

import bbs.api.biz.dal.mapper.original.UserMapper;
import bbs.api.biz.model.entity.User;
import bbs.api.biz.model.entity.UserExample;
import bbs.api.biz.model.request.UserLoginRequest;
import bbs.api.common.lib.application.BeanTools;

import java.util.List;

public class UserDalService {
    private static UserMapper userMapper = (UserMapper) BeanTools.getBean(UserMapper.class);

    public static User getUserByPrimaryKey(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    public static User userLogin(UserLoginRequest userLoginRequest) {
        UserExample userExample = new UserExample();
        userExample.or()
                .andUsernameEqualTo(userLoginRequest.getUsername())
                .andPasswordEqualTo(userLoginRequest.getPassword());

        List<User> userList = userMapper.selectByExample(userExample);

        if (userList.size() == 0) {
            return null;
        }

        return userList.get(0);
    }
}
