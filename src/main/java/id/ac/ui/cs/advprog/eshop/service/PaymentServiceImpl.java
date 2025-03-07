package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment createPayment(Order order, String paymentType, Map<String, String> paymentData) {
        Payment payment = new Payment(order, paymentType, paymentData);
        paymentRepository.addPayment(payment);
        return payment;
    }
    @Override
    public Payment setPaymentStatus(Payment payment, String status) {
        payment.setStatus(status);
        return payment;
    }
    @Override
    public Payment getPayment(String paymentId) {
        Optional<Payment> payment = paymentRepository.getPayment(paymentId);
        if (payment.isPresent()) {
            return payment.get();
        }
        return null;
    }
    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

}
