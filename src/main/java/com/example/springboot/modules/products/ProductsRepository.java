package com.example.springboot.modules.products;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductsRepository extends JpaRepository<ProductsEntity, Long> {
    List<ProductsEntity> findByName(String name);
}
