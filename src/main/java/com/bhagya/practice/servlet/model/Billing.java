package com.bhagya.practice.servlet.model;

import lombok.*;

@Setter
@Getter
@ToString
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Billing {

    private String PaymentMethod;
    private int upiId;

    public Billing(String paymentMethod, int upiId) {
    }


    public String getPaymentMethod() {
        return "";
    }

    public int getupiId() {
        return 0;
    }
}
