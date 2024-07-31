package raf.web.turistickivodic.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Activity {

    private Integer activityId;
    @NotNull(message = "Activity description is required")
    @NotEmpty(message = "Activity description is required")
    private String activity;
    private Activity(){}

    public Activity(Integer activityId, String activity) {
        this.activityId = activityId;
        this.activity = activity;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }
}
