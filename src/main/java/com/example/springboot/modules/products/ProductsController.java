package com.example.springboot.modules.products;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public List<ProductsEntity> findAll() {
        return productsService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<ProductsEntity> findById(@PathVariable Long id) {
        return productsService.findById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ProductsEntity create(@RequestBody ProductsEntity product) {
        return productsService.save(product);
    }

    @PutMapping
    public ProductsEntity update(@RequestBody ProductsEntity product) {
        return productsService.save(product);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productsService.deleteById(id);
    }

    @GetMapping("/find/{name}")
    public List<ProductsEntity> findByName(@PathVariable String name) {
        return productsService.findByName(name);
    }

    @GetMapping("/test")
    public String testProduct() { return "success"; }
}
