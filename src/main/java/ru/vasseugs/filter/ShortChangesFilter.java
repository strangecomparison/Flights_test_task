package ru.vasseugs.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a filter made for excluding flights where
 * changes take more than 2 hours in general
 */

public class ShortChangesFilter implements FlightFilter {


    @Override
    public List<Flight> filter(List<Flight> listOfFlights) {

        // getting rid of controversial flights - it would be a logical mistake
        // to think that controversial flight has less than 2 hours of changes
        listOfFlights = new NonControversialFlightsFilter().filter(listOfFlights);


        List<Flight> filteredFlights = new ArrayList<>(); // a list for the filtered flights
        int timeSpentOnTheGround = 0;   // an accumulator for the general time spent on the ground during changes


        // for each flight we check the number of segments in order to
        // decide - should we add periods of changes or not
        for(Flight flight : listOfFlights) {

            // creating an array from flight segments
            List<Segment> segmentsList = flight.getSegments();
            Segment[] segments = new Segment[segmentsList.size()];
            segmentsList.toArray(segments);


            /*
            on each iteration we get current segment's arrival date and
            next segment's departure date and find subtraction between them.
            all subtractions are accumulated to find the length of
            general time spent on the ground
             */

            if(segments.length == 1) {
                // a flight without changes should be handled separately
                filteredFlights.add(flight);
            } else {
                // segments.length - 2 because the last element we take is penultimate
                // last element will cause index out of array border. less or equals 2
                // because if an array contains 2 segments, we must take the one with the index 0

                for (int index = 0; index <= segments.length - 2; index++) {

                    LocalDateTime currentArrival = segments[index].getArrivalDate();
                    LocalDateTime upcomingDeparture = segments[index + 1].getDepartureDate();

                    // if the change lasts longer than 1 day, the general changes time
                    // will be more than 2 anyway so we check only changes at the same day
                    if(upcomingDeparture.getDayOfYear() - currentArrival.getDayOfYear() < 1) {

                        int currentDifference = upcomingDeparture.getHour() - currentArrival.getHour();
                        timeSpentOnTheGround += currentDifference;  // increase the time spent on the ground
                    } else {
                        // this value marks that the flight has the time on the ground
                        // more than day so it won't pass the filter
                        timeSpentOnTheGround = 24;
                    }
                }
            }

            if(timeSpentOnTheGround <= 2 & timeSpentOnTheGround > 0) {
                filteredFlights.add(flight);

                // we must nullify this accumulator variable on each flight iteration
                timeSpentOnTheGround = 0;
            }

            if(filteredFlights.isEmpty()) {
                System.out.println("Sorry, we can't offer you a flight without changes time less than 2 hours");
            }

        }
        return filteredFlights;
    }
}
