package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;

public interface PaymetService {
    Payment createPayment(Order order, String paymentType, Map<String, String> paymentData);
    Payment setPaymentStatus(Payment payment, String status);
    Payment getPayment(String PaymetId);
    List<Payment> getAllPayments();
    
}
