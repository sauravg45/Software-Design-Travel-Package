package com.nymble.assignment.utility;

public class NumberGenerator {
    private static int accountNumber = 1;
    private static int activityNumber = 1;
    private static int passengerNumber = 1;
    private static int transactionNumber = 1;
    public static int getNextAccountNumber(){
        return accountNumber++;
    }
    public static int getNextActivityNumber(){
        return activityNumber++;
    }
    public static int getNextPassengerNumber(){
        return passengerNumber++;
    }
    public static int getNextTransactionNumber(){
        return transactionNumber++;
    }

}
