package nymble.assignment.service;

import nymble.assignment.entity.Account;
import nymble.assignment.entity.Activities;
import nymble.assignment.entity.Destination;
import nymble.assignment.entity.Passenger;
import nymble.assignment.exception.*;
import com.nymble.assignment.utility.PassengerSubType;

import java.util.*;

public class TravelPackage {

    String packageName;
    Integer peopleCapacity;
    List<Destination> destinations;
    Map<Integer, Set<Passenger>> peopleActivityMap;
    List<Passenger> passengerList;
    Map<Integer, Passenger> passengerNoPassengerMap;
    Map<Integer, Activities> activityIdMap;

    public TravelPackage(String packageName, Integer maxPeopleCapacity){
        this.packageName = packageName;
        this.peopleCapacity = maxPeopleCapacity;
        this.destinations = new ArrayList<>();
        this.peopleActivityMap = new HashMap<>();
        this.passengerList = new ArrayList<>();
        this.passengerNoPassengerMap = new HashMap<>();
        this.activityIdMap = new HashMap<>();
    }

    // Adding passengers to travel package with limit on capacity
    public Passenger addPassenger(String name, PassengerSubType passengerType, Double initialPayment) throws TravelPackageFullException{
        if(passengerList.size()>= peopleCapacity){
            throw new TravelPackageFullException("TravelPackage capacity Full. Can not accommodate more");
        }
        Passenger passenger = new Passenger(name, passengerType, initialPayment);
        passengerList.add(passenger);
        passengerNoPassengerMap.put(passenger.getPassengerNumber(),passenger);
        return passenger;
    }

    //adding destination to package
    public void addDestinationToPackage(Destination destination) throws DuplicateEntryException{
        if(destinations.contains(destination)){
            throw new DuplicateEntryException("Destination Already in the package");
        }
        destinations.add(destination);
        //load every activity from destination to activityIdMap
        for(Activities activity:destination.getDestinationActivities()){
            activityIdMap.put(activity.getActivityId(), activity);
            peopleActivityMap.put(activity.getActivityId(), new HashSet<Passenger>());
        }
    }

    //purchase of activity of User
    public void bookActivity(Integer passengerNumber, Integer activityNo) throws KeyNotValidException, ActivityCapacityFullException, BalanceLowException{
        if(!passengerNoPassengerMap.containsKey(passengerNumber)){
            throw new KeyNotValidException("Passenger number Not Valid");
        }
        if(!activityIdMap.containsKey(activityNo)){
            throw new KeyNotValidException("Activity not with corresponding No");
        }
        Passenger passenger = passengerNoPassengerMap.get(passengerNumber);
        Activities activity = activityIdMap.get(activityNo);
        if(peopleActivityMap.get(activityNo).size() >= activity.getMaxCapacity()){
            throw new ActivityCapacityFullException("This activity is at max capacity");
        }
        // Make activity purchase by user
        purchaseActivity(passenger, activity);
    }

    //Cost condition changes with user type: STANDAR, PREMIUM, GOLD implemented here
    public void purchaseActivity(Passenger passenger, Activities activity) throws BalanceLowException{
        Double cost = new Double("0");
        switch (passenger.getPassengerType()){
            case PREMIUM :
                // For premium customer there is no cost
                cost = new Double("0");
                break;
            case GOLD :
                // for gold customer there is 10% off
                cost = activity.getActivityPrice()*.9;
                break;
            case STANDARD :
                cost = activity.getActivityPrice();
                break;
        }
        Account passengerAccount = passenger.getAccount();
        if(passengerAccount.getBalance() < cost){
            throw new BalanceLowException("Passenger does not have enough credit");
        }
        passengerAccount.purchaseOrder(cost, "Debit for activity "+activity.getActivityName());
        Set<Passenger> passengerForActivity = peopleActivityMap.get(activity.getActivityId());
        passengerForActivity.add(passenger);
    }

    public String getPackageName() {
        return packageName;
    }

    public Integer getPeopleCapacity() {
        return peopleCapacity;
    }

    public List<Passenger> getPassengerList() {
        return passengerList;
    }

    public void printItenaryTravelPackage(){
        System.out.printf("Package Name : %s\n", packageName);
        System.out.printf("Capacity of Package : %d\n", peopleCapacity);
        System.out.printf("---- Printing Destination ----\n");
        for(Destination destination: destinations){
            destination.printDestinationAndActivities();;
        }
    }

    public void printPassengerList(){
        System.out.printf("---- Printing Passenger List details for Package ----\n");
        System.out.printf("Package Name : %s\n", packageName);
        System.out.printf("Capacity of Package : %d\n", peopleCapacity);
        System.out.printf("Number of passenger currently enrolled : %d\n", passengerList.size());
        for(Passenger p : passengerList){
            p.printPassengerDetail();
        }
        System.out.printf("----  ----\n");
    }

    public List<Destination> getDestinations() {
        return destinations;
    }

    public Map<Integer, Set<Passenger>> getPeopleActivityMap() {
        return peopleActivityMap;
    }

    public Map<Integer, Passenger> getPassengerNoPassengerMap() {
        return passengerNoPassengerMap;
    }

    public Map<Integer, Activities> getActivityIdMap() {
        return activityIdMap;
    }
}
