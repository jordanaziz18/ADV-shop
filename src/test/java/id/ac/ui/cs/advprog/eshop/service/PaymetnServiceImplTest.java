package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private OrderRepository orderRepository; // ✅ Fix: Mock OrderRepository

    private Order order;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order = new Order("e334ef40-9eff-4da8-9487-8ee697ecbf1e", products, 1708570000L, "Bambang Sudrajat");

        when(orderRepository.findById(order.getId())).thenReturn(order); // ✅ Fix: Stub order retrieval
    }

    @Test
    void testAddPaymentWithValidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment(null, "Voucher", paymentData);
        when(paymentRepository.getPayment(payment.getId())).thenReturn(Optional.of(payment));

        Payment result = paymentService.addPayment(order.getId(), "Voucher", paymentData);

        assertEquals("SUCCESS", result.getStatus());
        verify(paymentRepository).addPayment(any(Payment.class)); // ✅ Verify repository was used
    }

    @Test
    void testAddPaymentWithInvalidVoucherCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "INVALIDCODE1234");

        Payment payment = new Payment(null, "Voucher", paymentData);
        when(paymentRepository.getPayment(payment.getId())).thenReturn(Optional.of(payment));

        Payment result = paymentService.addPayment(order.getId(), "Voucher", paymentData);

        assertEquals("REJECTED", result.getStatus());
        verify(paymentRepository).addPayment(any(Payment.class));
    }

    @Test
    void testSetPaymentStatusToSuccess() {
        Payment payment = new Payment(null, "Voucher", new HashMap<>());
        when(paymentRepository.getPayment(payment.getId())).thenReturn(Optional.of(payment));

        paymentService.setStatus(payment.getId(), "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testGetPaymentById() {
        Payment payment = new Payment(null, "Voucher", new HashMap<>());
        when(paymentRepository.getPayment(payment.getId())).thenReturn(Optional.of(payment));

        Payment retrievedPayment = paymentService.getPayment(payment.getId());

        assertNotNull(retrievedPayment);
        assertEquals(payment.getId(), retrievedPayment.getId());
    }
}
