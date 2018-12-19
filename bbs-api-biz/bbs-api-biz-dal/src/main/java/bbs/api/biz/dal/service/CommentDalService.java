package bbs.api.biz.dal.service;

import bbs.api.biz.dal.mapper.extend.CommentMapperExtend;
import bbs.api.biz.dal.mapper.original.CommentMapper;
import bbs.api.biz.model.entity.Comment;
import bbs.api.biz.model.request.CommonRequest;
import bbs.api.common.lib.application.BeanTools;

import java.util.List;

public class CommentDalService {
    private static CommentMapper commentMapper = (CommentMapper) BeanTools.getBean(CommentMapper.class);
    private static CommentMapperExtend commentMapperExtend = (CommentMapperExtend) BeanTools.getBean(CommentMapperExtend.class);

    public static List<Comment> getCommentList(CommonRequest commonRequest) {
        return commentMapperExtend.getCommentList(commonRequest);
    }

    public static void insert(Comment comment) {
        commentMapper.insert(comment);
    }
}
