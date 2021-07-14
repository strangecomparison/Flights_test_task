import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import ru.vasseugs.filter.CorrectDepartureFilter;
import ru.vasseugs.filter.NonControversialFlightsFilter;
import ru.vasseugs.filter.ShortChangesFilter;

import java.util.List;

import static ru.vasseugs.util.UtilityMethods.*;


public class Main {

    public static void main(String[] args) {

        //obtaining the list of flights
        List<Flight> flights = FlightBuilder.createFlights();

        /* I decided to implement polymorphic method
        applyFilter() to use different filters
         */

        // filtering to exclude flights which have departure time before current time
        List<Flight> withoutEarlierDeparture = applyFilter(new CorrectDepartureFilter(), flights);
        printFilteredFlights(withoutEarlierDeparture);
        System.out.println();

        // filtering to exclude flights where arrival time goes before departure time
        List<Flight> withoutControversial = applyFilter(new NonControversialFlightsFilter(), flights);
        printFilteredFlights(withoutControversial);
        System.out.println();

        // a filter to exclude flights where you have to spent more than 2 hours on changes
        List<Flight> shortChanges = applyFilter(new ShortChangesFilter(), flights);
        printFilteredFlights(shortChanges);

    }
}
