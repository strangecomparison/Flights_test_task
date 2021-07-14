package ru.vasseugs.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a filter that excludes flights which have started
 * before now.
 */

public class CorrectDepartureFilter implements FlightFilter {

    // this variable contains current time
    private static final LocalDateTime currentTime = LocalDateTime.now();

    @Override
    public List<Flight> filter(List<Flight> listOfFlights) {

        List<Flight> filteredFlights = new ArrayList<>();       // a list for the filtered flights

        for(Flight flight : listOfFlights) {
            List<Segment> segments = flight.getSegments();

            /* for each flight we get its departure date and
            check if it comes after current time. If the predicate is true,
            we add this flight to filtered flights
             */

            if(currentTime.isBefore(segments.get(0).getDepartureDate())) {
                filteredFlights.add(flight);
            }
        }

        // print remaining flights to console or display error message
        if(filteredFlights.isEmpty()) {
            System.out.println("Sorry, we can't offer you any flights. All flights have started before current time.");
        }

        return filteredFlights;
    }
}
