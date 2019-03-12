package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.dal.service.UserStarAndPraiseMapDalService;
import bbs.api.biz.enumeration.UserStarAndPraiseMapMapTypeEnum;
import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.request.NewORModifyPostRequest;
import bbs.api.biz.model.request.UserStarAndPraiseMapRequest;
import bbs.api.biz.model.response.NewORModifyPostResponse;
import bbs.api.biz.model.response.PostResponse;
import bbs.api.biz.model.view.AuthorView;
import bbs.api.biz.model.view.GlobalView;
import bbs.api.common.lib.CommonFunction;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostService {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse getPostList(BaseRequest baseRequest) {
        CommonRequest commonRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), CommonRequest.class);
        List<Post> postList = PostDalService.getPostList(commonRequest);

        if (postList.size() == 0) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        List<PostResponse> postListResponseList = new ArrayList<>();
        List<Integer> postIds = new ArrayList<>();
        List<Integer> userIds = new ArrayList<>();
        int userId = 0;

        if (commonRequest.getUserId() != null && commonRequest.getUserId().length() > 0) {
            userId = Integer.parseInt(commonRequest.getUserId());
        }

        for (Post post : postList) {
            if (!userIds.contains(post.getUserId())) {
                userIds.add(post.getUserId());
            }

            if (userId > 0) {
                postIds.add(post.getPostId());
            }
        }

        List<AuthorView> authorViewList = UserService.getAuthorViewList(userIds);
        List<UserStarAndPraiseMap> userStarAndPraiseMapList = null;

        if (userId > 0) {
            UserStarAndPraiseMapRequest userStarAndPraiseMapRequest = new UserStarAndPraiseMapRequest();
            userStarAndPraiseMapRequest.setUserId(userId);
            userStarAndPraiseMapRequest.setPostIds(postIds);

            userStarAndPraiseMapList = UserStarAndPraiseMapDalService.selectByPostIdsAndUserId(userStarAndPraiseMapRequest);
        }

        for (Post post : postList) {
            postListResponseList.add(getPostResponseByPost(post, authorViewList, userStarAndPraiseMapList));
        }

        return new ApiResponse(postListResponseList);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse getPostByPrimaryKey(BaseRequest baseRequest) {
        int postId = Integer.parseInt(baseRequest.getExtendValue().replace(GlobalView.idPrefix, ""));
        Post post = PostDalService.getPostByPrimaryKey(postId);

        if (post == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        return new ApiResponse(getPostResponseByPost(post));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse insertPost(BaseRequest baseRequest) {
        NewORModifyPostRequest newORModifyPostRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), NewORModifyPostRequest.class);
        Post post = new Post();

        post.setUserId(CommonService.removeGlobalIdPrefixAndConvertToInt(newORModifyPostRequest.getUserId()));
        post.setTitle(newORModifyPostRequest.getTitle());
        post.setContent(newORModifyPostRequest.getContent());
        post.setVote(0);
        post.setUpdatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        PostDalService.insert(post);
        return new ApiResponse(getModifyPostResponse(post));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse updatePost(BaseRequest baseRequest) {
        NewORModifyPostRequest newORModifyPostRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), NewORModifyPostRequest.class);
        Post post = PostDalService.getPostByPrimaryKey(CommonService.removeGlobalIdPrefixAndConvertToInt(newORModifyPostRequest.getPostId()));

        if (post == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        post.setTitle(newORModifyPostRequest.getTitle());
        post.setContent(newORModifyPostRequest.getContent());
        post.setUpdatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        PostDalService.update(post);
        return new ApiResponse(getModifyPostResponse(post));
    }

    private static NewORModifyPostResponse getModifyPostResponse(Post post) {
        NewORModifyPostResponse newORModifyPostResponse = new NewORModifyPostResponse();

        newORModifyPostResponse.setId(GlobalView.idPrefix + post.getPostId());
        newORModifyPostResponse.setAuthor(GlobalView.idPrefix + post.getUserId());
        newORModifyPostResponse.setUpdatedAt(DateHelper.stampToDate(post.getUpdatedAt()));
        newORModifyPostResponse.setTitle(post.getTitle());
        newORModifyPostResponse.setContent(post.getContent());
        newORModifyPostResponse.setVote(post.getVote());

        return newORModifyPostResponse;
    }

    private static PostResponse getPostResponseByPost(Post post, List<AuthorView> authorViewList, List<UserStarAndPraiseMap> userStarAndPraiseMapList) {
        PostResponse postResponse = new PostResponse();
        setPostResponseByPost(postResponse, post);

        Optional optional = authorViewList.stream().filter(o -> CommonService.removeGlobalIdPrefixAndConvertToInt(o.getId()) == post.getUserId()).findFirst();

        if (!CommonFunction.optionalIsNull(optional)) {
            postResponse.setAuthor((AuthorView) optional.get());
        }

        if (userStarAndPraiseMapList != null) {
            if (isUserStarOrPraisePost(post, userStarAndPraiseMapList, UserStarAndPraiseMapMapTypeEnum.star)) {
                postResponse.setFlagStar(true);
            }

            if (isUserStarOrPraisePost(post, userStarAndPraiseMapList, UserStarAndPraiseMapMapTypeEnum.praise)) {
                postResponse.setFlagPraise(true);
            }
        }

        return postResponse;
    }

    private static boolean isUserStarOrPraisePost(Post post,
                                                  List<UserStarAndPraiseMap> userStarAndPraiseMapList,
                                                  UserStarAndPraiseMapMapTypeEnum userStarAndPraiseMapMapTypeEnum) {
        Optional optional = userStarAndPraiseMapList.stream()
                .filter(o -> o.getPostId().equals(post.getPostId()) && o.getMapType() == userStarAndPraiseMapMapTypeEnum.getIndex())
                .findFirst();

        return !CommonFunction.optionalIsNull(optional);
    }

    private static PostResponse getPostResponseByPost(Post post) {
        PostResponse postResponse = new PostResponse();
        setPostResponseByPost(postResponse, post);
        postResponse.setAuthor(UserService.getAuthorView(post.getUserId()));

        return postResponse;
    }

    private static void setPostResponseByPost(PostResponse postResponse, Post post) {
        postResponse.setId(GlobalView.idPrefix + post.getPostId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setVote(post.getVote());
        postResponse.setUpdatedAt(DateHelper.stampToDate(post.getUpdatedAt()));
    }
}
