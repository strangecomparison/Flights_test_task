package ru.vasseugs.filter;

import com.gridnine.testing.Flight;
import java.util.List;

public interface FlightFilter {

    List<Flight> filter(List<Flight> listOfFlights);

}
