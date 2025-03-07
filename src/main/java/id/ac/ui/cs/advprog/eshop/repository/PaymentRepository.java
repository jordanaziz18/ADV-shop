package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.*;

public class PaymentRepository {
    private final Map<String, Payment> payments = new HashMap<>();

    public void addPayment(Payment payment) {
        payments.put(payment.getId(), payment);
    }

    public Optional<Payment> getPayment(String id) {
        return Optional.ofNullable(payments.get(id));
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments.values());
    }
}
