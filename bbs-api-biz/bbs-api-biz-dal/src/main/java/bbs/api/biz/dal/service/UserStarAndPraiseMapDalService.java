package bbs.api.biz.dal.service;

import bbs.api.biz.dal.mapper.original.UserStarAndPraiseMapMapper;
import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.biz.model.entity.UserStarAndPraiseMapExample;
import bbs.api.biz.model.request.UserStarAndPraiseMapRequest;
import bbs.api.common.lib.application.BeanTools;

import java.util.List;

public class UserStarAndPraiseMapDalService {
    private static UserStarAndPraiseMapMapper userStarAndPraiseMapMapper = (UserStarAndPraiseMapMapper) BeanTools.getBean(UserStarAndPraiseMapMapper.class);

    public static void insert(UserStarAndPraiseMap userStarAndPraiseMap) {
        userStarAndPraiseMapMapper.insert(userStarAndPraiseMap);
    }

    public static void delete(UserStarAndPraiseMap userStarAndPraiseMap) {
        UserStarAndPraiseMapExample example = new UserStarAndPraiseMapExample();
        example.or()
                .andUserIdEqualTo(userStarAndPraiseMap.getUserId())
                .andPostIdEqualTo(userStarAndPraiseMap.getPostId())
                .andMapTypeEqualTo(userStarAndPraiseMap.getMapType());

        userStarAndPraiseMapMapper.deleteByExample(example);
    }

    public static List<UserStarAndPraiseMap> selectByPostIdsAndUserId(UserStarAndPraiseMapRequest userStarAndPraiseMapRequest) {
        UserStarAndPraiseMapExample example = new UserStarAndPraiseMapExample();
        example.or()
                .andUserIdEqualTo(userStarAndPraiseMapRequest.getUserId())
                .andPostIdIn(userStarAndPraiseMapRequest.getPostIds());

        return userStarAndPraiseMapMapper.selectByExample(example);
    }

    public static boolean isExists(UserStarAndPraiseMap userStarAndPraiseMap) {
        UserStarAndPraiseMapExample example = new UserStarAndPraiseMapExample();
        example.or()
                .andUserIdEqualTo(userStarAndPraiseMap.getUserId())
                .andPostIdEqualTo(userStarAndPraiseMap.getPostId())
                .andMapTypeEqualTo(userStarAndPraiseMap.getMapType());
        List<UserStarAndPraiseMap> userStarAndPraiseMapList = userStarAndPraiseMapMapper.selectByExample(example);

        return userStarAndPraiseMapList.size() > 0;
    }
}
