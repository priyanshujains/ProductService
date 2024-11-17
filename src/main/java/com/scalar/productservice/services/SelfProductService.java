package com.scalar.productservice.services;

import com.scalar.productservice.exceptions.ProductNotExistException;
import com.scalar.productservice.models.Category;
import com.scalar.productservice.models.Product;
import com.scalar.productservice.repositories.CategoryRepo;
import com.scalar.productservice.repositories.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfService")
public class SelfProductService implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo){

        this.productRepo=productRepo;
        this.categoryRepo=categoryRepo;
    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        Optional<Product> optionalProduct=productRepo.findById(id);

        if(optionalProduct.isEmpty()){
            throw new ProductNotExistException("product with id: "+id+" doesnt exist");
        }
        return optionalProduct.get();

    }

    @Override
    public Product addNewProduct(Product product) {
        Optional<Category> categoryOptional =categoryRepo.findByName(product.getCategory().getName());

        if(categoryOptional.isEmpty()){// agar ese category exist nhi krte to banado;
            product.setCategory(categoryRepo.save(product.getCategory()));
        }else{
            product.setCategory(categoryOptional.get());
        }

        return productRepo.save(product);
    }

    @Override
    public List<Product> getAllProduct() {
        List<Product> productList=productRepo.findAll();

        return productList;

    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> optionalProduct=productRepo.findById(id);
        if(optionalProduct.isEmpty()){
            throw new RuntimeException();
        }
        Product savedProduct=optionalProduct.get();
        savedProduct.setTitle(product.getTitle());
        savedProduct.setDescription(product.getDescription());
        savedProduct.setImageUrl(product.getImageUrl());
        savedProduct.setPrice(product.getPrice());

        Optional<Category> categoryOptional =categoryRepo.findByName(product.getCategory().getName());
        if(categoryOptional.isEmpty()){
            savedProduct.setCategory(categoryRepo.save(product.getCategory()));
        }else{
            savedProduct.setCategory(categoryOptional.get());
        }
        return productRepo.save(savedProduct);

    }

    @Override
    public Product updateProduct(Long id, Product product) {

        Optional<Product> productOptional=productRepo.findById(id);
        if(productOptional.isEmpty()){
            throw new RuntimeException();
        }
        Product savedProduct=productOptional.get();
        if(product.getTitle() !=null){
            savedProduct.setTitle(product.getTitle());
        }
        if(product.getDescription() !=null){
            savedProduct.setDescription(product.getDescription());
        }
        if(product.getImageUrl() !=null){
            savedProduct.setImageUrl(product.getImageUrl());
        }
        if(product.getPrice() !=null){
            savedProduct.setPrice(product.getPrice());
        }
        if(product.getCategory().getName() !=null){

            Optional<Category> categoryOptional =categoryRepo.findByName(product.getCategory().getName());// category should not n

            if(categoryOptional.isEmpty()){
                savedProduct.setCategory(categoryRepo.save(product.getCategory()));
            }else{
                savedProduct.setCategory(categoryOptional.get());
            }
           // savedProduct.getCategory().setName(product.getCategory().getName());
        }

        return productRepo.save(savedProduct);

    }

    @Override
    public void deleteproduct(Long id) {
        productRepo.deleteById(id);
    }
}
