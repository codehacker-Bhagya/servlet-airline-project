package com.bhagya.practice.servlet.Repository;

import com.bhagya.practice.servlet.Service.ConnectionService;
import com.bhagya.practice.servlet.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PassengerRepository {

    public boolean createPassenger(Passenger passenger) throws SQLException {

        String query = "INSERT INTO person (fullName , uidNo , MoNo)" +
                " VALUES (?, ?, ?)";

        Connection connection = new ConnectionService().getConnection();// GET connection here

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, passenger.getfullName());
            preparedStatement.setString(2, passenger.getuidNo());
            preparedStatement.setString(3, passenger.getMoNo());

            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting passenger: " + e.getMessage(), e);
        }
    }

    public List<Passenger> displayPassenger() throws SQLException {
        List<Passenger> passengerList = new ArrayList<>();

        Connection connection = new ConnectionService().getConnection();

        String query = "SELECT * FROM passenger";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Passenger passenger = new Passenger(
                        resultSet.getString("fullName"),
                        resultSet.getString("uidNo"),
                        resultSet.getString("MoNo")

                );
                passengerList.add(passenger);
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving passengers: " + e.getMessage());
        }
        return passengerList;
    }


    public boolean deletePassenger(long uidNo) throws SQLException {
        String query = "DELETE FROM person WHERE uidNo = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query()) ){

            preparedStatement.setLong(1 , uidNo);
            return preparedStatement.executeUpdate() >0;

        } catch (SQLException e){
            throw new RuntimeException("Error deleting customer: " + e.getMessage() , e);
        }
    }

    private String query() {
        return null;
    }


    public boolean updatePassenger(long upiId , String fullName) {
        String query = "UPDATE passenger SET fullNamr = ? WHERE uidNo = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            long uidNo = 0;
            preparedStatement.setLong(1 , uidNo);
            preparedStatement.setString(2 , fullName);

            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){

            throw new RuntimeException("Error updating passenger: " + e.getMessage() , e);
        }
    }
}