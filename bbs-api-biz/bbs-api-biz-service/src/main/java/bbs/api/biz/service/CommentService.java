package bbs.api.biz.service;

import bbs.api.biz.dal.service.CommentDalService;
import bbs.api.biz.model.entity.Comment;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.request.NewCommentRequest;
import bbs.api.biz.model.response.CommentResponse;
import bbs.api.biz.model.response.NewCommentResponse;
import bbs.api.biz.model.view.GlobalView;
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
        NewCommentRequest newCommentRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), NewCommentRequest.class);

        Comment comment = new Comment();
        NewCommentResponse newCommentResponse = new NewCommentResponse();

        comment.setPostId(CommonFunction.removeGlobalIdPrefixAndConvertToInt(newCommentRequest.getPostId()));
        comment.setContent(newCommentRequest.getContent());
        comment.setUserId(CommonFunction.removeGlobalIdPrefixAndConvertToInt(newCommentRequest.getUserId()));
        comment.setUpdatedAt(DateHelper.getCurrentTimeUnixTimestamp());

        CommentDalService.insert(comment);

        newCommentResponse.setId(GlobalView.idPrefix + comment.getCommentId());
        newCommentResponse.setPostId(GlobalView.idPrefix + comment.getPostId());
        newCommentResponse.setAuthor(GlobalView.idPrefix + comment.getUserId());
        newCommentResponse.setContent(comment.getContent());
        newCommentResponse.setUpdatedAt(DateHelper.stampToDate(comment.getUpdatedAt()));

        return new ApiResponse(newCommentResponse);
    }

    private static CommentResponse getCommentResponse(Comment comment) {
        CommentResponse commentResponse = new CommentResponse();

        commentResponse.setId(GlobalView.idPrefix + comment.getCommentId());
        commentResponse.setContent(comment.getContent());
        commentResponse.setUpdatedAt(DateHelper.stampToDate(comment.getUpdatedAt()));
        commentResponse.setAuthor(UserService.getAuthorView(comment.getUserId()));

        return commentResponse;
    }
}
