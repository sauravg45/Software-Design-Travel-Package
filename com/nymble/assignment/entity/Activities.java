package nymble.assignment.entity;

import com.nymble.assignment.utility.NumberGenerator;
/*
* Activities Entity for activity with each destination
* */
public class Activities {

    Integer activityId;
    String activityName;
    Double activityPrice;
    Integer maxCapacity;

    public Activities(String activityName, Double activityPrice, Integer maxCapacity) {
        this.activityName = activityName;
        this.activityPrice = activityPrice;
        this.maxCapacity = maxCapacity;
        this.activityId = NumberGenerator.getNextActivityNumber();
    }

    public Integer getActivityId() {
        return activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public Double getActivityPrice() {
        return activityPrice;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }
}
