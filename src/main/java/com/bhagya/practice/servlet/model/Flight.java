package com.bhagya.practice.servlet.model;

import lombok.*;

@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    private String FlightName;
    private int FlightNo;
    private int FlightTime;
    private String Destination;
    private String Status;

    public Flight(String flightName, String destination, int flightNo) {
    }

    public String getflightName() {
        return "";
    }

    public String getdestination() {
        return "";
    }

    public int getFlightNo() {
        return 0;
    }
}
