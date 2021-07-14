package ru.vasseugs.util;

import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.vasseugs.filter.CorrectDepartureFilter;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UtilityMethodsTest {

    CorrectDepartureFilter correctDepartureFilter;
    List<Flight> listOfFlights;

    @BeforeEach
    void init() {
        correctDepartureFilter = new CorrectDepartureFilter();
        listOfFlights = FlightBuilder.createFlights();
    }

    @Test
    void applyFilter() {
        List<Flight> handledByFilter = correctDepartureFilter.filter(listOfFlights);

        List<Flight> handledByUtilityMethod =
                UtilityMethods.applyFilter(correctDepartureFilter, listOfFlights);

        assertEquals(handledByFilter, handledByUtilityMethod);
    }
}