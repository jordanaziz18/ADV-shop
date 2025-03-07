import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

class PaymentTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;
    private Map<String, String> voucherData;
    private Map<String, String> codData;
    
    @BeforeEach
    void setUp() {
        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        codData = new HashMap<>();
        codData.put("address", "123 Main Street");
        codData.put("deliveryFee", "5000");
    }
    
    @Test
    void testAddPayment() {
        Order order = new Order("ORDER123");
        
        Payment payment = paymentService.addPayment(order, "Voucher", voucherData);
        
        assertNotNull(payment);
        assertEquals("Voucher", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }
    
    @Test
    void testSetStatus() {
        Order order = new Order("ORDER123");
        
        Payment payment = paymentService.addPayment(order, "Voucher", voucherData);
        paymentService.setStatus(payment, "REJECTED");
        
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void valid
    
    @Test
    void testGetPayment() {
        Order order = new Order("ORDER123");
        
        Payment payment = paymentService.addPayment(order, "Voucher", voucherData);
        Payment fetchedPayment = paymentService.getPayment(payment.getId());
        
        assertNotNull(fetchedPayment);
        assertEquals(payment.getId(), fetchedPayment.getId());
    }
    
    @Test
    void testVoucherCodeValidation() {
        Order order = new Order("ORDER123");
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("voucherCode", "INVALIDCODE12345");
        
        Payment payment = paymentService.addPayment(order, "Voucher", invalidPaymentData);
        
        assertEquals("REJECTED", payment.getStatus());
    }
    
    @Test
    void testCashOnDeliveryValidation() {
        Order order = new Order("ORDER123");
        Map<String, String> invalidPaymentData = new HashMap<>();
        invalidPaymentData.put("address", "");
        invalidPaymentData.put("deliveryFee", "5000");
        
        Payment payment = paymentService.addPayment(order, "CashOnDelivery", invalidPaymentData);
        
        assertEquals("REJECTED", payment.getStatus());
    }
    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment("6", "Voucher Code", voucherData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment("7", "Voucher Code", voucherData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}
