package bbs.api.biz.dal.service;

import bbs.api.biz.dal.mapper.original.UserStarAndPraiseMapMapper;
import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.biz.model.entity.UserStarAndPraiseMapExample;
import bbs.api.common.lib.application.BeanTools;

import java.util.List;

public class UserStarAndPraiseMapDalService {
    private static UserStarAndPraiseMapMapper userStarAndPraiseMapMapper = (UserStarAndPraiseMapMapper) BeanTools.getBean(UserStarAndPraiseMapMapper.class);

    public static void insert(UserStarAndPraiseMap userStarAndPraiseMap) {
        userStarAndPraiseMapMapper.insert(userStarAndPraiseMap);
    }

    public static void delete(int userSAPMId) {
        userStarAndPraiseMapMapper.deleteByPrimaryKey(userSAPMId);
    }

    public static List<UserStarAndPraiseMap> selectByPostIdsAndUserId(List<Integer> postIds, int userId) {
        UserStarAndPraiseMapExample example = new UserStarAndPraiseMapExample();
        example.or().andUserIdEqualTo(userId).andPostIdIn(postIds);

        return userStarAndPraiseMapMapper.selectByExample(example);
    }
}
