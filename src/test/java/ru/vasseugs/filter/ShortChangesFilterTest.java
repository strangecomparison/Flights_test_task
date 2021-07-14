package ru.vasseugs.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShortChangesFilterTest {

    ShortChangesFilter shortChangesFilter;
    List<Flight> listOfFlights;

    @BeforeEach
    void initFlights() {
        listOfFlights = FlightBuilder.createFlights();
        shortChangesFilter = new ShortChangesFilter();
    }

    @Test
    void filter() {
        List<Flight> flights = new ArrayList<>();
        // adding the flight that should not pass the filter
        // it also should display error message
        flights.add(listOfFlights.get(4));

        List<Flight> filter = shortChangesFilter.filter(flights);
        assertEquals(0, filter.size());

    }

}