package com.testehan.springsecurity.chpt6.ex1.repo;

import com.testehan.springsecurity.chpt6.ex1.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
