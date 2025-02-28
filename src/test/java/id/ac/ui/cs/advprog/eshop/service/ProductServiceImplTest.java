package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository ProductRepository;

    @InjectMocks
    private ProductServiceImpl ProductService;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(50);
    }

    @Test
    void testCreate() {
        when(ProductRepository.create(product)).thenReturn(product);

        Product createdProduct = ProductService.create(product);

        assertEquals(product, createdProduct);
    }

    @Test
    void testFindAll() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        Iterator<Product> productIterator = productList.iterator();

        when(ProductRepository.findAll()).thenReturn(productIterator);

        List<Product> allProduct = ProductService.findAll();

        assertEquals(productList, allProduct);
    }

    @Test
    void testEdit() {
        when(ProductRepository.edit(product)).thenReturn(product);

        Product editedProduct = ProductService.edit(product);

        assertEquals(product, editedProduct);
    }

    @Test
    void testFindById() {
        when(ProductRepository.findById("123")).thenReturn(product);

        Product foundProduct = ProductService.findById("123");

        assertEquals(product, foundProduct);
    }

    @Test
    void testDelete() {
        ProductService.delete("123");

        verify(ProductRepository, times(1)).delete("123");
    }
}