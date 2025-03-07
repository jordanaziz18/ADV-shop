package id.ac.ui.cs.advprog.eshop.service;

import java.util.Map;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.List;

public interface PaymentService {
    Payment addPayment(String orderId, String method, Map<String, String> paymentData);
    Payment getPayment(String paymentId);
    List<Payment> getAllPayments();
    void setStatus(String paymentId, String status);
}
