package bbs.api.biz.model.response;

import bbs.api.biz.model.view.AuthorView;

public class PostResponse {
    private int id;
    private String title;
    private String content;
    private int vote;
    private String updatedAt;
    private AuthorView author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
