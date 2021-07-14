package ru.vasseugs.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a filter which excludes flights that have
 * general departure time coming after general arrival time.
 * I call them controversial flights.
 */

public class NonControversialFlightsFilter implements FlightFilter {

    @Override
    public List<Flight> filter(List<Flight> listOfFlights) {

        List<Flight> filteredFlights = new ArrayList<>();     // a list for the filtered flights

        for(Flight flight : listOfFlights) {

            List<Segment> segments = flight.getSegments();      // we get all segments of a flight

            /* getting the first segment departure time
            and last segment arrival time to compare them
             */

            LocalDateTime departure = segments.get(0).getDepartureDate();
            LocalDateTime arrival = segments.get(segments.size() - 1).getArrivalDate();

            // the comparison

            if(departure.isBefore(arrival)) {
                filteredFlights.add(flight);
            }
        }

        // checking if there are correct flights at all
        // print remaining flights to console or display error message
        if(filteredFlights.isEmpty()) {
            System.out.println("Logical error. In each flight an end must go after its beginning");
        }

        return filteredFlights;
    }
}
