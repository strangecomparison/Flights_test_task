package ru.vasseugs.util;

import com.gridnine.testing.Flight;
import ru.vasseugs.filter.FlightFilter;

import java.util.List;

public class UtilityMethods {

    // a method to print list of flights into console
    public static void printFilteredFlights(List<Flight> filteredFlights) {
        int numberOfFlight = 1;

        for(Flight flight : filteredFlights) {

            System.out.print("Flight " + numberOfFlight++ + " : ");
            System.out.println(flight.toString());
        }
    }

    // a method to apply filters that implement FlightFilter interface
    public static List<Flight> applyFilter(FlightFilter ff, List<Flight> flights) {
        return ff.filter(flights);
    }

}
