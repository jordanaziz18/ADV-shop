package id.ac.ui.cs.advprog.eshop.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepoInterface;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepoInterface productRepository;
    
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public Product create(Product product) {
        productRepository.create(product);
        return product;
    }
    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }
    @Override
    public Product edit(Product updatedProduct) {
        return productRepository.edit(updatedProduct);
    }

    @Override
    public Product findById(String id) {
        return productRepository.findById(id);
    }
    @Override
    public void delete(String id) {
        productRepository.delete(id);
    }
    
}



  
