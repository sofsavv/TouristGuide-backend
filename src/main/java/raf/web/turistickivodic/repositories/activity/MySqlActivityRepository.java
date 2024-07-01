package raf.web.turistickivodic.repositories.activity;

import raf.web.turistickivodic.entities.Activity;
import raf.web.turistickivodic.repositories.MySqlAbstractRepository;

import java.util.List;

public class MySqlActivityRepository extends MySqlAbstractRepository implements ActivityRepository {
    @Override
    public Activity addActivity(Activity activity) {
        return null;
    }

    @Override
    public List<Activity> allActivities() {
        return null;
    }

    @Override
    public Activity findActivity(Integer id) {
        return null;
    }

    @Override
    public void deleteActivity(Integer id) {

    }

    @Override
    public Activity editActivity(Integer id, Activity activity) {
        return null;
    }
}
