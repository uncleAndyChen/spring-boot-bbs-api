package bbs.api.biz.dal.mapper.original;

import bbs.api.biz.model.entity.UserStarAndPraiseMap;
import bbs.api.biz.model.entity.UserStarAndPraiseMapExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserStarAndPraiseMapMapper {
    long countByExample(UserStarAndPraiseMapExample example);

    int deleteByExample(UserStarAndPraiseMapExample example);

    int deleteByPrimaryKey(Integer userSapmId);

    int insert(UserStarAndPraiseMap record);

    int insertSelective(UserStarAndPraiseMap record);

    List<UserStarAndPraiseMap> selectByExample(UserStarAndPraiseMapExample example);

    UserStarAndPraiseMap selectByPrimaryKey(Integer userSapmId);

    int updateByExampleSelective(@Param("record") UserStarAndPraiseMap record, @Param("example") UserStarAndPraiseMapExample example);

    int updateByExample(@Param("record") UserStarAndPraiseMap record, @Param("example") UserStarAndPraiseMapExample example);

    int updateByPrimaryKeySelective(UserStarAndPraiseMap record);

    int updateByPrimaryKey(UserStarAndPraiseMap record);
}