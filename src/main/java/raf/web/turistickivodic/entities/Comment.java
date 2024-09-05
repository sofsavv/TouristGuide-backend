package raf.web.turistickivodic.entities;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    private Integer commentId;
    @NotNull(message = "Author is required")
    @NotEmpty(message = "Author is required")
    private String author;
    @NotNull(message = "Comment text is required")
    @NotEmpty(message = "Comment text is required")
    private String comment;
    private String dateTime;
    @NotNull(message = "Article ID is required")
    private Integer articleId;

    private Comment(){}

    public Comment(Integer commId, String author, String comment, String date, Integer articleId) {
        this.commentId = commId;
        this.author = author;
        this.comment = comment;
        this.dateTime = date;
        this.articleId = articleId;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
