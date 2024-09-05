package raf.web.turistickivodic.services;

import raf.web.turistickivodic.entities.Activity;
import raf.web.turistickivodic.repositories.activity.ActivityRepository;

import javax.inject.Inject;
import java.util.List;

public class ActivityService {

    @Inject
    private ActivityRepository activityRepository;

    public Activity addActivity(Activity activity) {
        return this.activityRepository.addActivity(activity);
    }
    public List<Activity> allActivities(int currentPage, int pageSize) {
        return this.activityRepository.allActivities(currentPage, pageSize);
    }

    public Activity updateActivity(Integer id, Activity activity){
        return this.activityRepository.editActivity(id, activity);
    }
    public void deleteActivity(Integer id) {
        this.activityRepository.deleteActivity(id);
    }

    public List<Activity> findActivitiesByArticleId(Integer articleId){
        return this.activityRepository.getActivitiesByArticleId(articleId);
    }
}
