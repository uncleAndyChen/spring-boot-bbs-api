package bbs.api.biz.dal.service;

import bbs.api.biz.dal.mapper.original.PostMapper;
import bbs.api.common.lib.application.BeanTools;
import bbs.api.biz.dal.mapper.extend.PostMapperExtend;
import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.request.CommonRequest;

import java.util.List;

public class PostDalService {
    private static PostMapperExtend postMapperExtend = (PostMapperExtend) BeanTools.getBean(PostMapperExtend.class);
    private static PostMapper postMapper = (PostMapper) BeanTools.getBean(PostMapper.class);

    public static List<Post> getPostList(CommonRequest commonRequest) {
        return postMapperExtend.getPostList(commonRequest);
    }

   public static void insert(Post post) {
       postMapper.insert(post);
   }

   public static void update(Post post) {
        postMapper.updateByPrimaryKey(post);
   }

   public static Post getPostByPrimaryKey(int postId) {
        return postMapper.selectByPrimaryKey(postId);
    }
}
