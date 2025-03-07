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
        
    }

    @Override
    public Payment getPayment(String paymentId) {
        
    }

    @Override
    public List<Payment> getAllPayments() {
        
    }

    @Override
    public void setStatus(String paymentId, String status) {
       
    }
}
