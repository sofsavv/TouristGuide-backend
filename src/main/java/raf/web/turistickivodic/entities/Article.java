package raf.web.turistickivodic.entities;

import java.util.ArrayList;
import java.util.List;

public class Article {

    private Integer articleId;
    private String title;
    private String date;
    private int visits;
    private List<Integer> activityIds;

    private Integer destinationId;
    private String user;

    private Article(){
        activityIds = new ArrayList<>();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Integer destinationId) {
        this.destinationId = destinationId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}
