package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.dal.service.UserStarAndPraiseMapDalService;
import bbs.api.biz.enumeration.UserStarAndPraiseMapMapTypeEnum;
import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.biz.model.request.UserStarAndPraiseMapRequest;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.request.BaseRequest;

import java.util.List;

public class UserStarAndPraiseMapService {
    public static void insert(BaseRequest baseRequest) {
        UserStarAndPraiseMap userStarAndPraiseMap = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserStarAndPraiseMap.class);

        userStarAndPraiseMap.setCreatedAt(DateHelper.getCurrentTimeUnixTimestamp());
        UserStarAndPraiseMapDalService.insert(userStarAndPraiseMap);

        if (userStarAndPraiseMap.getMapType() == UserStarAndPraiseMapMapTypeEnum.star.getIndex()) {
            PostDalService.voteAdd(userStarAndPraiseMap.getPostId());
        }
    }

    public static void delete(BaseRequest baseRequest) {
        UserStarAndPraiseMap userStarAndPraiseMap = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserStarAndPraiseMap.class);
        UserStarAndPraiseMapDalService.delete(userStarAndPraiseMap);

        if (userStarAndPraiseMap.getMapType() == UserStarAndPraiseMapMapTypeEnum.star.getIndex()) {
            PostDalService.voteReduce(userStarAndPraiseMap.getPostId());
        }
    }
}
