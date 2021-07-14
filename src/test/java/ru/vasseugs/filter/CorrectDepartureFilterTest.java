package ru.vasseugs.filter;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class CorrectDepartureFilterTest {

    CorrectDepartureFilter correctFilter;
    List<Flight> listOfFlights;

    @BeforeEach
    void initFlights() {
        listOfFlights = FlightBuilder.createFlights();
        correctFilter = new CorrectDepartureFilter();
    }


    @Test
    void filter() {
        List<Flight> flights = new ArrayList<>();
        // adding the flight that should not pass the filter
        // it also should display error message
        flights.add(listOfFlights.get(2));

        List<Flight> filter = correctFilter.filter(flights);
        assertEquals(0, filter.size());
    }
}