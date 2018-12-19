package bbs.api.biz.dal.mapper.extend;

import bbs.api.biz.model.entity.Comment;
import bbs.api.biz.model.request.CommonRequest;

import java.util.List;

public interface CommentMapperExtend {
    List<Comment> getCommentList(CommonRequest commonRequest);
}
