package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.Optional;
import java.util.HashMap;
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
    void testPaymentRejectionForInvalidVoucher() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALID12345678"); 

        Payment payment = new Payment("3", "Voucher Code", invalidVoucherData);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("3");

        assertTrue(retrievedPayment.isPresent());
        assertEquals("REJECTED", retrievedPayment.get().getStatus());
    }

    @Test
    void testPaymentRejectionForCashOnDeliveryWithMissingData() {
        Map<String, String> invalidCodData = new HashMap<>();
        invalidCodData.put("address", ""); 

        Payment payment = new Payment("4", "Cash on Delivery", invalidCodData);
        paymentRepository.save(payment);

        Optional<Payment> retrievedPayment = paymentRepository.findById("4");

        assertTrue(retrievedPayment.isPresent());
        assertEquals("REJECTED", retrievedPayment.get().getStatus());
    }
}

    
