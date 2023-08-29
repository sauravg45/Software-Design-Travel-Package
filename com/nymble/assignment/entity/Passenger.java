package nymble.assignment.entity;

import com.nymble.assignment.utility.NumberGenerator;
import com.nymble.assignment.utility.PassengerSubType;

/*
* Passenger class having details of passengers
* */
public class Passenger {
    Integer passengerNumber;
    String passengerName;
    PassengerSubType passengerType;
    Account account;

    public Passenger(String passengerName, PassengerSubType passengerType, Double amountCredited) {
        this.passengerName = passengerName;
        this.passengerType = passengerType;
        this.account = new Account(amountCredited);
        this.passengerNumber = NumberGenerator.getNextPassengerNumber();
    }

    public Integer getPassengerNumber() {
        return passengerNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public PassengerSubType getPassengerType() {
        return passengerType;
    }

    public Account getAccount() {
        return account;
    }

    public void printPassengerDetail(){
        System.out.printf("Passenger Number:%d Passenger Name: %s\n", passengerNumber,passengerName);
    }

    public void printPassengerDetailedAcount(){
        System.out.printf("Passenger Number:%d Passenger Name: %s\n", passengerNumber,passengerName);
        System.out.printf("Passenger Type: %s\n", passengerType.name());
        System.out.printf("Passenger Balance: %f\n",account.getBalance());
        System.out.println("---- Printing Transaction Details");
        for(Transaction transaction: account.getTransactions()){
            System.out.println(transaction.toString());
        }
    }

}
