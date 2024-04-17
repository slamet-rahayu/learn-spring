package com.example.springboot.modules.products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {

    @Autowired
    private ProductsRepository productsRepository;

    public List<ProductsEntity> findAll() {
        return productsRepository.findAll();
    }

    public Optional<ProductsEntity> findById(Long id) {
        return productsRepository.findById(id);
    }

    public ProductsEntity save(ProductsEntity productsEntity) {
        return productsRepository.save(productsEntity);
    }

    public void deleteById(Long id) {
        productsRepository.deleteById(id);
    }

    public List<ProductsEntity> findByName(String name) {
        return productsRepository.findByName(name);
    }
}
