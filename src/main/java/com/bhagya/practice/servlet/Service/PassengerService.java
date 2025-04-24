package com.bhagya.practice.servlet.Service;

import com.bhagya.practice.servlet.Repository.PassengerRepository;
import com.bhagya.practice.servlet.model.Passenger;

import java.sql.SQLException;
import java.util.List;

public class PassengerService {

    private static final PassengerRepository passengerRepository = new PassengerRepository();

    public boolean insertPassenger(Passenger passenger) throws SQLException {
        return passengerRepository.createPassenger(passenger);
    }

    public boolean updatePassenger(long UIDNo, String fullName) throws SQLException {
        return passengerRepository.updatePassenger(UIDNo, fullName);
    }

    public boolean deletePassenger(long uidNo) throws SQLException {
        return passengerRepository.deletePassenger(uidNo);
    }


    public List<Passenger> displayPassenger() throws SQLException {
        return passengerRepository.displayPassenger();
    }
}
