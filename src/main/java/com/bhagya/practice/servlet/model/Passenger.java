package com.bhagya.practice.servlet.model;

import lombok.*;

@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Passenger {
    private String fullName;
    private long MoNo;
    private long UIDno;

    public Passenger(String fullName, String moNo, String UIDdNo) {
    }

    public String getfullName() {
        return "";
    }

    public String getMoNo() {
        return "";
    }

    public String uidNo() {
        return "";
    }

    public String getuidNo() {
        return "";
    }

}