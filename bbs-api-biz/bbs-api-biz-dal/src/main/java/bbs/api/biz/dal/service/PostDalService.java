package bbs.api.biz.dal.service;

import bbs.api.common.lib.application.BeanTools;
import bbs.api.biz.dal.mapper.extend.PostMapperExtend;
import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.request.CommonRequest;

import java.util.List;

public class PostDalService {
    private static PostMapperExtend postMapperExtend = (PostMapperExtend) BeanTools.getBean(PostMapperExtend.class);

    public static List<Post> getPostList(CommonRequest commonRequest) {
        return postMapperExtend.getPostList(commonRequest);
    }
}
