package bbs.api.biz.model.request;

import java.util.List;

public class UserStarAndPraiseMapRequest {
    private List<Integer> postIds;
    private int userId;

    public List<Integer> getPostIds() {
        return postIds;
    }

    public void setPostIds(List<Integer> postIds) {
        this.postIds = postIds;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
