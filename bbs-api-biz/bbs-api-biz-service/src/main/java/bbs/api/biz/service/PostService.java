package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.request.NewORModifyPostRequest;
import bbs.api.biz.model.response.NewORModifyPostResponse;
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
        NewORModifyPostRequest newORModifyPostRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), NewORModifyPostRequest.class);
        Post post = new Post();

        post.setUserId(CommonFunction.removeGlobalIdPrefixAndConvertToInt(newORModifyPostRequest.getUserId()));
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
        Post post = PostDalService.getPostByPrimaryKey(CommonFunction.removeGlobalIdPrefixAndConvertToInt(newORModifyPostRequest.getPostId()));

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
