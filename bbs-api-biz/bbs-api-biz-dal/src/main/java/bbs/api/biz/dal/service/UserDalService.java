package bbs.api.biz.dal.service;

import bbs.api.biz.dal.mapper.original.UserMapper;
import bbs.api.biz.model.entity.User;
import bbs.api.common.lib.application.BeanTools;

public class UserDalService {
    private static UserMapper userMapper = (UserMapper) BeanTools.getBean(UserMapper.class);

    public static User getUserByPrimaryKey(int userId) {
        return userMapper.selectByPrimaryKey(userId);
    }
}
