package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.dal.service.UserStarAndPraiseMapDalService;
import bbs.api.biz.enumeration.UserStarAndPraiseMapMapTypeEnum;
import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.biz.model.request.UserStarAndPraiseRequest;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;

public class UserStarAndPraiseMapService {
    public static ApiResponse userStarAndPraiseInsert(BaseRequest baseRequest) {
        UserStarAndPraiseRequest userStarAndPraiseRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserStarAndPraiseRequest.class);
        UserStarAndPraiseMap userStarAndPraiseMap = new UserStarAndPraiseMap();

        setUserStarAndPraiseMapByRequest(userStarAndPraiseRequest, userStarAndPraiseMap);
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
        UserStarAndPraiseRequest userStarAndPraiseRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), UserStarAndPraiseRequest.class);
        UserStarAndPraiseMap userStarAndPraiseMap = new UserStarAndPraiseMap();

        setUserStarAndPraiseMapByRequest(userStarAndPraiseRequest, userStarAndPraiseMap);
        UserStarAndPraiseMapDalService.delete(userStarAndPraiseMap);

        if (userStarAndPraiseMap.getMapType() == UserStarAndPraiseMapMapTypeEnum.praise.getIndex()) {
            PostDalService.voteReduce(userStarAndPraiseMap.getPostId());
        }

        return new ApiResponse();
    }

    private static void setUserStarAndPraiseMapByRequest(UserStarAndPraiseRequest userStarAndPraiseRequest, UserStarAndPraiseMap userStarAndPraiseMap) {
        userStarAndPraiseMap.setUserId(CommonService.removeGlobalIdPrefixAndConvertToInt(userStarAndPraiseRequest.getUserId()));
        userStarAndPraiseMap.setPostId(CommonService.removeGlobalIdPrefixAndConvertToInt(userStarAndPraiseRequest.getPostId()));
        userStarAndPraiseMap.setMapType(userStarAndPraiseRequest.getMapType());
    }
}
