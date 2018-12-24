package bbs.api.biz.model.response;

import bbs.api.biz.model.view.AuthorView;

public class PostResponse {
    private String id;
    private String title;
    private String content;
    private int vote;
    private String updatedAt;
    private AuthorView author;
    private boolean flagPraise; // 记录当前登录用户是否点赞
    private boolean flagStar;   // 记录当前登录用户是否标星

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public AuthorView getAuthor() {
        return author;
    }

    public void setAuthor(AuthorView author) {
        this.author = author;
    }

    public boolean isFlagPraise() {
        return flagPraise;
    }

    public void setFlagPraise(boolean flagPraise) {
        this.flagPraise = flagPraise;
    }

    public boolean isFlagStar() {
        return flagStar;
    }

    public void setFlagStar(boolean flagStar) {
        this.flagStar = flagStar;
    }
}
