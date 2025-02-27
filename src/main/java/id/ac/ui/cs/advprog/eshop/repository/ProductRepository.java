package id.ac.ui.cs.advprog.eshop.repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
@Repository
public class ProductRepository {
    private final List<Product> productData = new ArrayList<>();
    
    public Product create(Product product) {
        product.setProductId("P" + (productData.size() + 1));
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public Product findById(String id) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product edit(Product updatedProduct) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(updatedProduct.getProductId())) {
                Product existingProduct = productData.get(i);
                existingProduct.setProductName(updatedProduct.getProductName());
                existingProduct.setProductQuantity(updatedProduct.getProductQuantity());
                return existingProduct;
            }
        }
        return null;
    }


    public Product delete(String id) {
        for (int i = 0; i < productData.size(); i++) {
            if (productData.get(i).getProductId().equals(id)) {
                return productData.remove(i);
            }
        }
        return null;
    }
}