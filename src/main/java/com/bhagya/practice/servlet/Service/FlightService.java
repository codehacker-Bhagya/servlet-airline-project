package com.bhagya.practice.servlet.Service;

import com.bhagya.practice.servlet.Repository.FlightRepository;
import com.bhagya.practice.servlet.Repository.TicketRepository;
import com.bhagya.practice.servlet.model.Flight;
import com.bhagya.practice.servlet.model.Passenger;

import java.sql.SQLException;
import java.util.List;

public class FlightService {

    private static final FlightRepository flightRepository = new FlightRepository();

    public boolean insertFlight(Flight flight) throws SQLException {
        return flightRepository.createFlight(flight);
    }

    public boolean updateFlight(int flightNo, String flightName) throws SQLException {
        return flightRepository.updateFlight(flightNo, flightName);
    }

    public boolean deleteFlight(int flightNo) throws SQLException {
        return flightRepository.deleteFlight(flightNo);
    }


    public List<Flight> displayFlight() throws SQLException {
        return flightRepository.displayFlight();
    }
}
