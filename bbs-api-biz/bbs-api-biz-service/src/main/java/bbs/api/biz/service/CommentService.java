package bbs.api.biz.service;

import bbs.api.biz.dal.service.CommentDalService;
import bbs.api.biz.model.entity.Comment;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.request.CreateCommentRequest;
import bbs.api.biz.model.response.CommentResponse;
import bbs.api.biz.model.response.CreateCommentResponse;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.ModelHelper;
import bbs.api.common.model.request.BaseRequest;
import bbs.api.common.model.response.ApiResponse;
import bbs.api.common.model.response.ResponseCodeEnum;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse getCommentList(BaseRequest baseRequest) {
        CommonRequest commonRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), CommonRequest.class);
        List<Comment> commentList = CommentDalService.getCommentList(commonRequest);

        if (commentList.size() == 0) {
            return ModelHelper.getApiResponseByResponseCodeEnum(ResponseCodeEnum.noRecord);
        }

        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseList.add(getCommentResponse(comment));
        }

        return new ApiResponse(commentResponseList);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static ApiResponse insert(BaseRequest baseRequest) {
        CreateCommentRequest createCommentRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), CreateCommentRequest.class);

        Comment comment = new Comment();
        CreateCommentResponse createCommentResponse = new CreateCommentResponse();

        comment.setPostId(createCommentRequest.getPostId());
        comment.setContent(createCommentRequest.getContent());
        comment.setUserId(createCommentRequest.getUserId());
        comment.setUpdatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        CommentDalService.insert(comment);

        createCommentResponse.setId(comment.getCommentId());
        createCommentResponse.setPost(comment.getPostId());
        createCommentResponse.setAuthor(comment.getUserId());
        createCommentResponse.setContent(comment.getContent());
        createCommentResponse.setUpdatedAt(DateHelper.stampToDate(comment.getUpdatedAt()));

        return new ApiResponse(createCommentResponse);
    }

    private static CommentResponse getCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId(comment.getCommentId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setUpdatedAt(DateHelper.stampToDate(comment.getUpdatedAt()));
        commentResponse.setAuthor(UserService.getAuthorView(comment.getUserId()));

        return commentResponse;
    }
}
