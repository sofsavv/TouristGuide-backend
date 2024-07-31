package raf.web.turistickivodic.entities;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Comment {

    private Integer commId;
    @NotNull(message = "Author is required")
    @NotEmpty(message = "Author is required")
    private String author;
    @NotNull(message = "Comment text is required")
    @NotEmpty(message = "Comment text is required")
    private String comment;
    private String date;

    private Comment(){}

    public Comment(Integer commId, String author, String comment, String date) {
        this.commId = commId;
        this.author = author;
        this.comment = comment;
        this.date = date;
    }

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
