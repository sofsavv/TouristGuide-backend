package raf.web.turistickivodic.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Article {

    private Integer articleId;
    private String title;
    private Date date;
    private String time;
    private int visits;
    private List<Integer> activityIds;
    private Article(){
        activityIds = new ArrayList<>();
    }
}
