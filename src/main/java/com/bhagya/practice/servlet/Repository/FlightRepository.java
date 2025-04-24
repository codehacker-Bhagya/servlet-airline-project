package com.bhagya.practice.servlet.Repository;

import com.bhagya.practice.servlet.Service.ConnectionService;
import com.bhagya.practice.servlet.model.Flight;
import com.bhagya.practice.servlet.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightRepository {

    public boolean createFlight(Flight flight) throws SQLException {

        String query = "INSERT INTO flight (flightName , destination , FlightNo)" +
                " VALUES (?, ?, ?)";

        Connection connection = new ConnectionService().getConnection();// GET connection here

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, flight.getflightName());
            preparedStatement.setString(2, flight.getdestination());
            preparedStatement.setInt(3, flight.getFlightNo());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting passenger: " + e.getMessage(), e);
        }
    }

    public List<Flight> displayFlight() throws SQLException {
        List<Flight> flightList = new ArrayList<>();

        Connection connection = new ConnectionService().getConnection();

        String query = "SELECT * FROM flight";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int FlightNo;
                Flight flight = new Flight(
                        resultSet.getString("flightName"),
                        resultSet.getString("destination"),
                        resultSet.getInt("FlightNo")

                );
                flightList.add(flight);
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving passengers: " + e.getMessage());
        }
        return flightList;
    }

    public boolean deleteFlight(int flightNo) throws SQLException {
        String query = "DELETE FROM flight WHERE flightNo = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query())){

            preparedStatement.setInt(1 , flightNo);
            return preparedStatement.executeUpdate() >0;

        } catch (SQLException e){
            throw new RuntimeException("Error deleting customer: " + e.getMessage() , e);
        }
    }

    private String query() {
        return "";
    }


    public boolean updateFlight(int flightNo , String flightName) {
        String query = "UPDATE flight SET flightNamr = ? WHERE flightNo = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1 , flightNo);
            preparedStatement.setString(2 , flightName);

            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){

            throw new RuntimeException("Error updating passenger: " + e.getMessage() , e);
        }
    }

}
