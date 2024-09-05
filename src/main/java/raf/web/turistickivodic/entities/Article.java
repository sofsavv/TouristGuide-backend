package raf.web.turistickivodic.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class Article {

    private Integer articleId;
    @NotNull(message = "Title is required")
    @NotEmpty(message = "Title is required")
    private String title;
    @NotNull(message = "Text is required")
    @NotEmpty(message = "Text is required")
    private String text;
    @NotNull(message = "Time of creation is required")
    @NotEmpty(message = "Time of creation is required")
    private String dateTime;
    @NotNull(message = "Number of visits is required")
    @NotEmpty(message = "Number of visits is required")
    private int visits;
    @NotNull(message = "Activities are required")
    @NotEmpty(message = "Activities are required")
    private List<Integer> activityIds;
    @NotNull(message = "Destination is required")
    @NotEmpty(message = "Destination is required")
    private Integer destinationId;
    @NotNull(message = "User is required")
    @NotEmpty(message = "User is required")
    private String author;

    private Article(){}

    public Article(Integer articleId, String title, String text, String dateTime, int visits, Integer destinationId, String author) {
        this.articleId = articleId;
        this.title = title;
        this.text = text;
        this.dateTime = dateTime;
        this.visits = visits;
        this.destinationId = destinationId;
        this.author = author;
        this.activityIds = new ArrayList<>();
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getVisits() {
        return visits;
    }

    public void setVisits(int visits) {
        this.visits = visits;
    }

    public List<Integer> getActivityIds() {
        return activityIds;
    }

    public void setActivityIds(List<Integer> activityIds) {
        this.activityIds = activityIds;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
