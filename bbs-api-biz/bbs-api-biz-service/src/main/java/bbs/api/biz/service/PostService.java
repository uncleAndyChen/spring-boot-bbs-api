package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.request.ModifyPostRequest;
import bbs.api.biz.model.response.NewPostResponse;
import bbs.api.biz.model.response.PostResponse;
import bbs.api.biz.model.view.GlobalView;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

import java.util.ArrayList;
import java.util.List;

public class PostService {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse getPostList(BaseRequest baseRequest) {
        CommonRequest commonRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), CommonRequest.class);
        List<Post> postList = PostDalService.getPostList(commonRequest);

        if (postList.size() == 0) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        List<PostResponse> postListResponseList = new ArrayList<>();

        for (Post post : postList) {
            postListResponseList.add(getPostResponseByPost(post));
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
        ModifyPostRequest modifyPostRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), ModifyPostRequest.class);
        Post post = new Post();

        post.setUserId(CommonFunction.removeGlobalIdPrefixAndConvertToInt(modifyPostRequest.getUserId()));
        post.setTitle(modifyPostRequest.getTitle());
        post.setContent(modifyPostRequest.getContent());
        post.setVote(0);
        post.setUpdatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        PostDalService.insert(post);
        return new ApiResponse(getNewPostResponse(post));
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse updatePost(BaseRequest baseRequest) {
        ModifyPostRequest modifyPostRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), ModifyPostRequest.class);
        Post post = PostDalService.getPostByPrimaryKey(CommonFunction.removeGlobalIdPrefixAndConvertToInt(modifyPostRequest.getPostId()));

        if (post == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        post.setTitle(modifyPostRequest.getTitle());
        post.setContent(modifyPostRequest.getContent());
        post.setUpdatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        PostDalService.update(post);
        return new ApiResponse(getNewPostResponse(post));
    }

    private static NewPostResponse getNewPostResponse(Post post) {
        NewPostResponse newPostResponse = new NewPostResponse();

        newPostResponse.setId(GlobalView.idPrefix + post.getPostId());
        newPostResponse.setAuthor(GlobalView.idPrefix + post.getUserId());
        newPostResponse.setUpdatedAt(DateHelper.stampToDate(post.getUpdatedAt()));
        newPostResponse.setTitle(post.getTitle());
        newPostResponse.setContent(post.getContent());
        newPostResponse.setVote(post.getVote());

        return newPostResponse;
    }

    private static PostResponse getPostResponseByPost(Post post) {
        PostResponse postResponse = new PostResponse();

        postResponse.setId(GlobalView.idPrefix + post.getPostId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setVote(post.getVote());
        postResponse.setUpdatedAt(DateHelper.stampToDate(post.getUpdatedAt()));
        postResponse.setAuthor(UserService.getAuthorView(post.getUserId()));

        return postResponse;
    }
}
