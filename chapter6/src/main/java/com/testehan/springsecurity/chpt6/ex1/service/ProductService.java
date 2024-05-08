package com.testehan.springsecurity.chpt6.ex1.service;

import com.testehan.springsecurity.chpt6.ex1.model.Product;
import com.testehan.springsecurity.chpt6.ex1.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
