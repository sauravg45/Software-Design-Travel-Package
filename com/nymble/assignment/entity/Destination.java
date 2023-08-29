package nymble.assignment.entity;

import java.util.HashSet;
import java.util.Set;

public class Destination {
    String DestinationName;
    Set<Activities> destinationActivities;

    public Destination(String destinationName) {
        DestinationName = destinationName;
        this.destinationActivities = new HashSet<>();
    }

    public void setDestinationActivities(Activities destinationActivity) {
        this.destinationActivities.add(destinationActivity);
    }

    public Set<Activities> getDestinationActivities() {
        return destinationActivities;
    }

    public String getDestinationName() {
        return DestinationName;
    }

    public void printDestinationAndActivities(){
        System.out.printf(" Destination  Name : %s\n",getDestinationName());
        System.out.printf("Printing Activities for Destination %s\n", getDestinationName());
        for(Activities activity: destinationActivities){
            System.out.println("---------------------");
            System.out.printf("Activity id : %d\n", activity.getActivityId());
            System.out.printf("Activity Name : %s\n", activity.getActivityName());
            System.out.printf("Activity Price : %f\n", activity.getActivityPrice());
            System.out.printf("Activity Capacity %d \n",activity.getMaxCapacity());
        }
        System.out.println("---------------------");
    }

}
