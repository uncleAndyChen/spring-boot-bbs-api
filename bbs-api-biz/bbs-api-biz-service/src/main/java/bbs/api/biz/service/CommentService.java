package bbs.api.biz.service;

import bbs.api.biz.dal.service.CommentDalService;
import bbs.api.biz.model.entity.Comment;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.biz.model.response.CommentResponse;
import bbs.api.common.lib.DateHelper;
import bbs.api.common.lib.JsonHelper;
import bbs.api.common.model.request.BaseRequest;

import java.util.ArrayList;
import java.util.List;

public class CommentService {
    public static List<CommentResponse> getCommentList(BaseRequest baseRequest) {
        CommonRequest commonRequest = JsonHelper.jsonStringToPojo(baseRequest.getJsonStringParameter(), CommonRequest.class);
        List<Comment> commentList = CommentDalService.getCommentList(commonRequest);

        if (commentList.size() == 0) {
            return null;
        }

        List<CommentResponse> commentResponseList = new ArrayList<>();

        for (Comment comment : commentList) {
            commentResponseList.add(getCommentResponse(comment));
        }

        return commentResponseList;
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
