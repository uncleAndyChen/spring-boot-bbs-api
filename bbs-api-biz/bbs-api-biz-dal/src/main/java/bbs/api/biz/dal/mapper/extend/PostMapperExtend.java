package bbs.api.biz.dal.mapper.extend;

import bbs.api.biz.model.entity.Post;
import bbs.api.biz.model.request.CommonRequest;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface PostMapperExtend {
    @SelectProvider(type=PostMapperExtendSqlProvider.class, method="getPostList")
    List<Post> getPostList(CommonRequest commonRequest);
}
