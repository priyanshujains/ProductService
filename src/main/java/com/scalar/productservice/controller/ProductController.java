package com.scalar.productservice.controller;


import com.scalar.productservice.commons.AuthenticationCommons;
import com.scalar.productservice.dtos.FakeStoreProductDto;
import com.scalar.productservice.dtos.Role;
import com.scalar.productservice.dtos.UserDto;
import com.scalar.productservice.exceptions.ProductNotExistException;
import com.scalar.productservice.models.Product;
import com.scalar.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private AuthenticationCommons authenticationCommons;

    @Autowired
    public ProductController(@Qualifier("SelfService") ProductService productService, AuthenticationCommons authenticationCommons){
        this.productService=productService;
        this.authenticationCommons=authenticationCommons;
    }

    //this request needs to authenthicate token
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){

//        UserDto userDto= authenticationCommons.validateToken(token);
//        if(userDto==null){
//            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
//        }
//
//        boolean isAdmin=false;
//        for(Role role:userDto.getRoles()){
//            if(role.getName().equals("Admin")){
//                isAdmin=true;
//            }
//        }
       // if(!isAdmin)return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(productService.getAllProduct(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id) throws ProductNotExistException {
        return productService.getSingleProduct(id);
    }

    @PostMapping()
    public Product addNewProduct(@RequestBody Product productDto){
        return productService.addNewProduct(productDto);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.replaceProduct(id,product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") long id){
            productService.deleteproduct(id);
    }
}
