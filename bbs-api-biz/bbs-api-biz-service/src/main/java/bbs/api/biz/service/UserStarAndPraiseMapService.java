package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.dal.service.UserStarAndPraiseMapDalService;
import bbs.api.biz.enumeration.UserStarAndPraiseMapMapTypeEnum;
import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;

public class UserStarAndPraiseMapService {
    public static ApiResponse userStarAndPraiseInsert(BaseRequest baseRequest) {
        UserStarAndPraiseMap userStarAndPraiseMap = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserStarAndPraiseMap.class);

        userStarAndPraiseMap.setCreatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        if (UserStarAndPraiseMapDalService.isExists(userStarAndPraiseMap)) {
            return new ApiResponse();
        }

        UserStarAndPraiseMapDalService.insert(userStarAndPraiseMap);

        if (userStarAndPraiseMap.getMapType() == UserStarAndPraiseMapMapTypeEnum.praise.getIndex()) {
            PostDalService.voteAdd(userStarAndPraiseMap.getPostId());
        }

        return new ApiResponse();
    }

    public static ApiResponse userStarAndPraiseDelete(BaseRequest baseRequest) {
        UserStarAndPraiseMap userStarAndPraiseMap = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserStarAndPraiseMap.class);
        UserStarAndPraiseMapDalService.delete(userStarAndPraiseMap);

        if (userStarAndPraiseMap.getMapType() == UserStarAndPraiseMapMapTypeEnum.praise.getIndex()) {
            PostDalService.voteReduce(userStarAndPraiseMap.getPostId());
        }

        return new ApiResponse();
    }
}
