package com.scalar.productservice.services;

import com.scalar.productservice.dtos.FakeStoreProductDto;
import com.scalar.productservice.exceptions.ProductNotExistException;
import com.scalar.productservice.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id)throws ProductNotExistException;
    Product addNewProduct(Product product);

    List<Product> getAllProduct();

    Product replaceProduct(Long id, Product product);

    Product updateProduct(Long id, Product product);

    void deleteproduct(Long id);

}
