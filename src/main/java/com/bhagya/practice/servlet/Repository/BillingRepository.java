package com.bhagya.practice.servlet.Repository;

import com.bhagya.practice.servlet.Service.ConnectionService;
import com.bhagya.practice.servlet.model.Billing;
import com.bhagya.practice.servlet.model.Passenger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BillingRepository {

    private String PaymentMethod;

    public boolean createBilling(Billing billing) throws SQLException {

        String query = "INSERT INTO billing (PaymentMethod , upiId )" +
                " VALUES (?, ?)";

        Connection connection = new ConnectionService().getConnection();// GET connection here

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, billing.getPaymentMethod());
            preparedStatement.setInt(2, billing.getupiId());
           
            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting passenger: " + e.getMessage(), e);
        }
    }

    public List<Billing> displayBilling() throws SQLException {
        List<Billing> billingList = new ArrayList<>();

        Connection connection = new ConnectionService().getConnection();

        String query = "SELECT * FROM billing";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Billing billing = new Billing(
                        resultSet.getString("PaymentMethod"),
                        resultSet.getInt("upiId")
                       
                );
                billingList.add(billing);
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving passengers: " + e.getMessage());
        }
        return billingList;
    }

    public boolean deleteBilling(int upiId) throws SQLException {
        String query = "DELETE FROM billing WHERE upiId = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query())){

            preparedStatement.setString(1 , String.valueOf(upiId));
            return preparedStatement.executeUpdate() >0;

        } catch (SQLException e){
            throw new RuntimeException("Error deleting passenger: " + e.getMessage() , e);
        }
    }

    private String query() {
        return "";
    }

    public boolean updateBilling(int upiId , String seatPreference) {
        String query = "UPDATE billing SET PaymentMethod = ? WHERE upiId = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1 , upiId);
            preparedStatement.setString(2 , PaymentMethod);

            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){

            throw new RuntimeException("Error updating passenger: " + e.getMessage() , e);
        }
    }

}
