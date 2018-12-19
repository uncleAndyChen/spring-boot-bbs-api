package bbs.api.biz.service;

import bbs.api.biz.dal.service.PostDalService;
import bbs.api.biz.dal.service.UserDalService;
import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.entity.User;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.response.PostResponse;
import bbs.api.biz.model.view.AuthorView;
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
        int postId = Integer.parseInt(baseRequest.getExtendValue());
        Post post = PostDalService.getPostByPrimaryKey(postId);

        if (post == null) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        return new ApiResponse(getPostResponseByPost(post));
    }

//    @SuppressWarnings({"unchecked", "rawtypes"})
//    public static ApiResponse insert(BaseRequest baseRequest) {
//
//    }

    private static PostResponse getPostResponseByPost(Post post) {
        PostResponse postResponse = new PostResponse();

        postResponse.setId(post.getPostId());
        postResponse.setTitle(post.getTitle());
        postResponse.setContent(post.getContent());
        postResponse.setVote(post.getVote());
        postResponse.setUpdatedAt(DateHelper.stampToDate(post.getUpdatedAt()));
        postResponse.setAuthor(UserService.getAuthorView(post.getUserId()));

        return postResponse;
    }
}
