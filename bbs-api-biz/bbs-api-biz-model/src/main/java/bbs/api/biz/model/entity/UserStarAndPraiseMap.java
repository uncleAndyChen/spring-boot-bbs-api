package bbs.api.biz.model.entity;

public class UserStarAndPraiseMap {
    private Integer userSapmId;

    private Integer userId;

    private Integer postId;

    private Integer mapType;

    private Integer createdAt;

    public Integer getUserSapmId() {
        return userSapmId;
    }

    public void setUserSapmId(Integer userSapmId) {
        this.userSapmId = userSapmId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public Integer getMapType() {
        return mapType;
    }

    public void setMapType(Integer mapType) {
        this.mapType = mapType;
    }

    public Integer getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Integer createdAt) {
        this.createdAt = createdAt;
    }
}