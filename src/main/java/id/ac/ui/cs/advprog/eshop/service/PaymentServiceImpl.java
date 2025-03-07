package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final OrderRepository orderRepository;

    @Override
    public Payment addPayment(String orderId, String method, Map<String, String> paymentData) {
        Order order = orderRepository.findById(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        Payment payment = new Payment(null, method, paymentData);
        paymentRepository.addPayment(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.getPayment(paymentId)
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }

    @Override
    public void setStatus(String paymentId, String status) {
        Payment payment = getPayment(paymentId);
        payment.setStatus(status);

        Order order = orderRepository.findById(paymentId);
        if (order != null) {
            order.setStatus(status.equals("SUCCESS") ? "PAID" : "FAILED");
            orderRepository.save(order);
        }
    }
}
