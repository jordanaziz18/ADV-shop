package id.ac.ui.cs.advprog.eshop.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;


@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;
    public ProductController(ProductService service) {
        this.service = service;
    }
    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }
    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }
    @GetMapping("/list")
    public String listProduct(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }
    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Product product = service.findById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }
    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product, Model model) {
        service.edit(product);
        return "redirect:list";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id, Model model) {
        service.delete(id);
        return "redirect:/product/list";
    }

}