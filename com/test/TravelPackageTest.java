package test;

import nymble.assignment.entity.Activities;
import nymble.assignment.entity.Destination;
import nymble.assignment.exception.TravelPackageFullException;
import com.nymble.assignment.utility.PassengerSubType;
import nymble.assignment.service.TravelPackage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TravelPackageTest {

    private TravelPackage travelPackage;

    @BeforeEach
    public void setUp() {
        String packageName = "Test Package";
        Integer maxPeopleCapacity = 3;
        travelPackage = new TravelPackage(packageName, maxPeopleCapacity);
    }

    @Test
    public void testConstructor() {
        assertEquals("Test Package", travelPackage.getPackageName());
        assertEquals(3, travelPackage.getPeopleCapacity());
        assertEquals(0, travelPackage.getPassengerList().size());
    }

    @Test
    public void testAddPassenger() {
        String name = "John Doe";
        PassengerSubType passengerType = PassengerSubType.STANDARD;
        Double initialPayment = 100.0;

        assertDoesNotThrow(() -> travelPackage.addPassenger(name, passengerType, initialPayment));
        assertEquals(1, travelPackage.getPassengerList().size());

        // Add more passengers up to the capacity
        for (int i = 2; i <= travelPackage.getPeopleCapacity(); i++) {
            String passengerName = "Passenger " + i;
            assertDoesNotThrow(() -> travelPackage.addPassenger(passengerName, passengerType, initialPayment));
        }

        // Adding one more passenger should throw TravelPackageFullException
        String extraPassengerName = "Extra Passenger";
        assertThrows(TravelPackageFullException.class,
                () -> travelPackage.addPassenger(extraPassengerName, passengerType, initialPayment));
    }

    @Test
    public void testAddDestinationToPackage() {
        Destination destination1 = new Destination("Destination 1");
        Activities activity1 = new Activities("Activity 1", 1.0, 5);
        Activities activity2 = new Activities("Activity 2", 2.0, 5);
        destination1.setDestinationActivities(activity1);
        destination1.setDestinationActivities(activity2);

        // Add destination with activities
        assertDoesNotThrow(() -> travelPackage.addDestinationToPackage(destination1));
        assertEquals(1, travelPackage.getDestinations().size());
        assertTrue(travelPackage.getActivityIdMap().containsKey(1));
        assertTrue(travelPackage.getActivityIdMap().containsKey(2));

        // Attempt to add the same destination again (should print message)
        Destination duplicateDestination = new Destination("Destination 1");
        assertDoesNotThrow(() -> travelPackage.addDestinationToPackage(duplicateDestination));

        // Add a new destination with activities
        Destination destination2 = new Destination("Destination 2");
        Activities activity3 = new Activities("Activity 3", 3.0,3);
        destination2.setDestinationActivities(activity3);
        assertDoesNotThrow(() -> travelPackage.addDestinationToPackage(destination2));
        assertEquals(3, travelPackage.getDestinations().size());
        assertTrue(travelPackage.getActivityIdMap().containsKey(3));
    }
}
