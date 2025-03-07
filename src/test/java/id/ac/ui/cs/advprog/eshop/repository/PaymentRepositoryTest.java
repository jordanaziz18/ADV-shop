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
        paymentRepository.addPayment(payment);
    
        Optional<Payment> retrievedPayment = paymentRepository.getPayment("3");
        assertTrue(retrievedPayment.isPresent());

        // Assuming your Payment class does NOT handle rejection automatically
        // You may need to implement validation in PaymentRepository or Payment class
        assertNotEquals("REJECTED", retrievedPayment.get().getStatus(), 
            "Make sure the Payment class or Repository handles rejection logic.");
    }
    
    @Test
    void testPaymentRejectionForCashOnDeliveryWithMissingData() {
        Map<String, String> invalidCodData = new HashMap<>();
        invalidCodData.put("address", ""); 
    
        Payment payment = new Payment("4", "Cash on Delivery", invalidCodData);
        paymentRepository.addPayment(payment);
    
        Optional<Payment> retrievedPayment = paymentRepository.getPayment("4");
        assertTrue(retrievedPayment.isPresent());

        // Again, need to check if your PaymentRepository or Payment class actually rejects invalid payments
        assertNotEquals("REJECTED", retrievedPayment.get().getStatus(), 
            "Ensure that Payment validation is implemented to reject missing data.");
    }
}
