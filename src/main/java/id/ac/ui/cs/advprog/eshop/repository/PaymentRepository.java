package id.ac.ui.cs.advprog.eshop.repository;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;


public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    public void addPayment(Payment payment) {
        payments.put(payment.getId(), payment);
    }

    public Optional<Payment> getPayment(String id) {
        return Optional.ofNullable(payments.get(id));
    }

    public List<Payment> getAllPayments() {
        return new ArrayList<>(payments.values());
    }

    public void save(Payment payment) {
        payments.put(payment.getId(), payment);
    }

    public Optional<Payment> findById(String id) {
        return Optional.ofNullable(payments.get(id));
    }
}
