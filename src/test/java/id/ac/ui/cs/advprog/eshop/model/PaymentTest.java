package id.ac.ui.cs.advprog.eshop.model;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;


class PaymentTest {
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
    void testAddPaymentWithVoucher() {
        Payment payment = new Payment(null, "Voucher", voucherData);

        assertNotNull(payment);
        assertEquals("Voucher", payment.getMethod());
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testAddPaymentWithInvalidVoucher() {
        Map<String, String> invalidVoucherData = new HashMap<>();
        invalidVoucherData.put("voucherCode", "INVALID12345678");

        Payment payment = new Payment(null, "Voucher", invalidVoucherData);

        assertNotNull(payment);
        assertEquals("Voucher", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testAddPaymentWithCashOnDelivery() {
        Payment payment = new Payment(null, "CashOnDelivery", codData);

        assertNotNull(payment);
        assertEquals("CashOnDelivery", payment.getMethod());
        assertEquals("PENDING", payment.getStatus());
    }

    @Test
    void testAddPaymentWithInvalidCashOnDelivery() {
        Map<String, String> invalidCodData = new HashMap<>();
        invalidCodData.put("address", "");  // Invalid empty address
        invalidCodData.put("deliveryFee", "5000");

        Payment payment = new Payment(null, "CashOnDelivery", invalidCodData);

        assertNotNull(payment);
        assertEquals("CashOnDelivery", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Payment payment = new Payment(null, "Voucher", voucherData);
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testSetStatusToRejected() {
        Payment payment = new Payment(null, "Voucher", voucherData);
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}
