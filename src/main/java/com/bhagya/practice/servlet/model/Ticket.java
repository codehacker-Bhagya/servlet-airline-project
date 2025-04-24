package com.bhagya.practice.servlet.model;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Ticket {
    private String seatPreference;
    private int ticketNo;
    private String AddMeal;
    private String Destination;
    private double TicketAmount;
    private String Status;

    public Ticket(String seatPreference, String ticketNo) {
    }

    public String getseatPreference() {
        return "";
    }

    public int gettickeNo() {
        return 0;
    }
}
