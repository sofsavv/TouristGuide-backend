package raf.web.turistickivodic.repositories.activity;

import raf.web.turistickivodic.entities.Activity;

import java.util.List;

public interface ActivityRepository {

    public Activity addActivity(Activity activity);
    public List<Activity> allActivities(int currentPage, int pageSize);
    public Activity findActivity(Integer id);
    public void deleteActivity(Integer id);
    public Activity editActivity(Integer id, Activity activity);
    public List<Activity> getActivitiesByArticleId(Integer articleId);
}
