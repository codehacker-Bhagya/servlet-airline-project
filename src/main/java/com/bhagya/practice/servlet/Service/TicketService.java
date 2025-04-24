package com.bhagya.practice.servlet.Service;

import com.bhagya.practice.servlet.Repository.PassengerRepository;
import com.bhagya.practice.servlet.Repository.TicketRepository;
import com.bhagya.practice.servlet.model.Passenger;
import com.bhagya.practice.servlet.model.Ticket;

import java.sql.SQLException;
import java.util.List;



public class TicketService {

    private static final TicketRepository ticketRepository = new TicketRepository();

    public boolean insertTicket(Ticket ticket) throws SQLException {
        return ticketRepository.createTicket(ticket);
    }

    public boolean updateTicket(int ticketNo, String seatPreference) throws SQLException {
        return ticketRepository.updateticket(ticketNo, seatPreference);
    }

    public boolean deleteTicket(int ticketNo) throws SQLException {
        return ticketRepository.deleteTicket(ticketNo);
    }


    public List<Ticket> displayTicket() throws SQLException {
        return ticketRepository.displayTicket();
    }
}
