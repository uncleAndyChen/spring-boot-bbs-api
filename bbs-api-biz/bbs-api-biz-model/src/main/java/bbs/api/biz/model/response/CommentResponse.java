package bbs.api.biz.model.response;

import bbs.api.biz.model.view.AuthorView;

public class CommentResponse {
    private String id;
    private String content;
    private String updatedAt;
    private AuthorView author;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
