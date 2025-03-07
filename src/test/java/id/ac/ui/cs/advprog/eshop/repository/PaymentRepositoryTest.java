package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.Optional;
import java.util.List;
import java.util.Map;

class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testAddPayment() {
        Payment payment = new Payment("1", "VOUCHER", Map.of("voucherCode", "ESHOP1234ABC5678")); 
        paymentRepository.addPayment(payment);
        
        Optional<Payment> retrievedPayment = paymentRepository.getPayment("1");
        assertTrue(retrievedPayment.isPresent());
        assertEquals("1", retrievedPayment.get().getId());
    }

    @Test
    void testGetPayment_NotFound() {
        Optional<Payment> payment = paymentRepository.getPayment("999");
        assertFalse(payment.isPresent());
    }

    @Test
    void testGetAllPayments() {
        Payment payment1 = new Payment("1", "VOUCHER", Map.of("voucherCode", "ESHOP1234ABC5678"));
        Payment payment2 = new Payment("2", "COD", Map.of("address", "123 Street", "deliveryFee", "10"));
        
        paymentRepository.addPayment(payment1);
        paymentRepository.addPayment(payment2);
        
        List<Payment> payments = paymentRepository.getAllPayments();
        assertEquals(2, payments.size());
    }

    @Test
    void testGetAllPayments_Empty() {
        List<Payment> payments = paymentRepository.getAllPayments();
        assertEquals(0, payments.size());
    }

    @Test
    void testSaveAndFindById() {
        Payment payment = new Payment("1", "VOUCHER", Map.of("voucherCode", "ESHOP1234ABC5678"));

        paymentRepository.save(payment);
        Optional<Payment> retrievedPayment = paymentRepository.findById("1");

        assertTrue(retrievedPayment.isPresent());
        assertEquals("1", retrievedPayment.get().getId());
        assertEquals("VOUCHER", retrievedPayment.get().getMethod());
    }

    @Test
    void testFindById_NotFound() {
        Optional<Payment> payment = paymentRepository.findById("999");
        assertFalse(payment.isPresent());
    }
}
