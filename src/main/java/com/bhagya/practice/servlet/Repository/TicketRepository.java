package com.bhagya.practice.servlet.Repository;

import com.bhagya.practice.servlet.Service.ConnectionService;
import com.bhagya.practice.servlet.model.Ticket;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {
    public boolean createTicket(Ticket ticket) throws SQLException {

        String query = "INSERT INTO ticket (ticketNo , seatPreference )" +
                " VALUES (?, ?, )";

        Connection connection = new ConnectionService().getConnection();// GET connection here

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, ticket.getseatPreference());
            preparedStatement.setInt(2, ticket.gettickeNo());


            return preparedStatement.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error inserting ticket: " + e.getMessage(), e);
        }
    }

    public List<Ticket> displayTicket() throws SQLException {
        List<Ticket> ticketList = new ArrayList<>();

        Connection connection = new ConnectionService().getConnection();

        String query = "SELECT * FROM ticket";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Ticket ticket = new Ticket(
                        resultSet.getString("seatPreference"),
                        resultSet.getString("ticketNo")

                        );
                ticketList.add(ticket);
            }
        }catch (SQLException e) {
            System.out.println("Error retrieving passengers: " + e.getMessage());
        }
        return ticketList;
    }

    public boolean deleteTicket(int ticketNo) throws SQLException {
        String query = "DELETE FROM ticket WHERE ticketNo = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query())){

            preparedStatement.setInt(1 , ticketNo);
            return preparedStatement.executeUpdate() >0;

        } catch (SQLException e){
            throw new RuntimeException("Error deleting customer: " + e.getMessage() , e);
        }
    }

    private String query() {
        return "";
    }

    public boolean updateticket(int ticketNo, String seatPreference) {
        String query = "UPDATE ticket SET seatPreference = ? WHERE ticketNo = ?" ;

        try (Connection connection = new ConnectionService().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)){

            preparedStatement.setInt(1 , ticketNo);
            preparedStatement.setString(2 , seatPreference);

            return preparedStatement.executeUpdate() > 0;
        }catch (SQLException e){

            throw new RuntimeException("Error updating passenger: " + e.getMessage() , e);
        }
    }

}
