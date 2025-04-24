package com.bhagya.practice.servlet.Service;

import com.bhagya.practice.servlet.Repository.BillingRepository;
import com.bhagya.practice.servlet.model.Billing;
import com.bhagya.practice.servlet.model.Flight;

import java.sql.SQLException;
import java.util.List;

public class BillingService {

    private static final BillingRepository billingRepository = new BillingRepository();

    public boolean insertBilling(Billing billing) throws SQLException {
        return billingRepository.createBilling(billing);
    }

    public boolean updateBilling(int upiId, String PaymentMethod) throws SQLException {
        return billingRepository.updateBilling(upiId, PaymentMethod);
    }

    public boolean deleteBilling(int upiId) throws SQLException {
        return billingRepository.deleteBilling(upiId);
    }


    public List<Billing> displayBilling() throws SQLException {
        return billingRepository.displayBilling();
    }

    public List<Billing> displaybilling() {
        return List.of();
    }
}
