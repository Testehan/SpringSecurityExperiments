package com.testehan.springsecurity.chpt17.service;

import com.testehan.springsecurity.chpt17.model.Product;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    /*
        The service method returns the list exactly as the method receives it. This way, we can
        test and validate that the framework filtered the list as we expected by checking the list
        returned in the HTTP response body.
    */
    @PreFilter("filterObject.owner == authentication.name")
    public List<Product> sellProducts(List<Product> products) {
        // sell products and return the sold products list
        return products;
    }
}