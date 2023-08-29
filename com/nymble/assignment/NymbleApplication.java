package nymble.assignment;

import com.nymble.assignment.utility.PassengerSubType;
import nymble.assignment.entity.Activities;
import nymble.assignment.entity.Destination;
import nymble.assignment.entity.Passenger;
import nymble.assignment.exception.*;
import nymble.assignment.service.TravelPackage;

public class NymbleApplication {

	public static void main(String[] args) {
		// Create Travel Package
		TravelPackage southIndiaPackage = new TravelPackage("South India", 3);
		//Create activities
		Activities activity1 = new Activities("Mysore Palace Visit", 500.00, 2);
		Activities activity2 = new Activities("Nandi hills Visit", 1000.00, 3);
		Activities activity3 = new Activities("trekking", 2000.00, 4);
		// Create destination
		Destination mysore =  new Destination("Mysore");
		Destination bengaluru = new Destination("Bengaluru");
		// add activity to destination mysore
		mysore.setDestinationActivities(activity1);
		// add activity to destination bengaluru
		bengaluru.setDestinationActivities(activity2);
		bengaluru.setDestinationActivities(activity3);
		// add destination to package
		try{
			southIndiaPackage.addDestinationToPackage(mysore);
			southIndiaPackage.addDestinationToPackage(bengaluru);
			// add duplicate destination to test Exception
			southIndiaPackage.addDestinationToPackage(bengaluru);
		}catch (DuplicateEntryException d){
			System.out.println(d.getMessage());
		}
		// Print travel package destination activities
		southIndiaPackage.printItenaryTravelPackage();
		Passenger saurabh = null;
		Passenger shekhar = null;
		Passenger kanhaiya = null;
		try {
			saurabh = southIndiaPackage.addPassenger("Saurabh", PassengerSubType.PREMIUM,0.0);
			shekhar = southIndiaPackage.addPassenger("Shekhar", PassengerSubType.GOLD,500.0);
			kanhaiya = southIndiaPackage.addPassenger("Kanhaiya", PassengerSubType.STANDARD,600.0);
			// Adding 4 passenger to capacity of 3 to south Indian Package to test exception
			Passenger shivi = southIndiaPackage.addPassenger("Shivi", PassengerSubType.STANDARD,600.0);
		} catch (TravelPackageFullException t){
			System.out.println(t.getMessage());
		}
		// Printing Passenger details for travel Package
		southIndiaPackage.printPassengerList();
		// booking activity for passengers
		try{
			southIndiaPackage.bookActivity(1,1);
			southIndiaPackage.bookActivity(2,1);
			southIndiaPackage.bookActivity(2,2);
			// adding this to test low balance for passenger number 2
			southIndiaPackage.bookActivity(2,3);
			southIndiaPackage.bookActivity(3,2);
		}catch (KeyNotValidException | ActivityCapacityFullException | BalanceLowException e){
			System.out.println(e.getMessage());
		}
		try {
			// adding this to test activity with capacity 2 adding 3 people to activity 1
			southIndiaPackage.bookActivity(3,1);
		}catch (KeyNotValidException | ActivityCapacityFullException | BalanceLowException e){
			System.out.println(e.getMessage());
		}
		// printing passenger details
		saurabh.printPassengerDetailedAcount();
		kanhaiya.printPassengerDetailedAcount();
		shekhar.printPassengerDetailedAcount();
	}

}
