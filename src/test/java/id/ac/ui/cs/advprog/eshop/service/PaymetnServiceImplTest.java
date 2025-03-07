package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.model.Order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    private Order order;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentService = new PaymentServiceImpl(paymentRepository);
        
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);
        
        order = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e", products, 1708570000L, "Bambang Sudrajat");
    }
    @Test
    void testCreatePayment() {
        Payment payment = paymentService.createPayment(order, "VOUCHER", Map.of("voucherCode", "ESHOP1234ABC5678"));
        assertNotNull(payment);
        assertEquals("VOUCHER", payment.getPaymentType());
        assertEquals("ESHOP1234ABC5678", payment.getPaymentData().get("voucherCode"));
    }
    @Test
    void testCreatePayment_InvalidVoucher() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createPayment(order, "VOUCHER", Map.of("voucherCode", "INVALID12345678"));
        });
    }
    @Test
    void testCreatePayment_InvalidPaymentType() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentService.createPayment(order, "INVALID", new HashMap<>());
        });
    }
    @Test
    void testAddPaymentWithCashOnDelivery_Success() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");

        Payment payment = paymentService.addPayment(order, "Cash on Delivery", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testAddPaymentWithCashOnDelivery_EmptyAddress() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "5000");

        Payment payment = paymentService.addPayment(order, "Cash on Delivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = paymentService.addPayment(order, "Voucher Code", paymentData);
        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testGetPaymentById() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = paymentService.addPayment(order, "Voucher Code", paymentData);
        Payment retrievedPayment = paymentService.getPayment(payment.getId());

        assertNotNull(retrievedPayment);
        assertEquals(payment.getId(), retrievedPayment.getId());
    }
}

}