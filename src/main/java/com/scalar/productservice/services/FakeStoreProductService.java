package com.scalar.productservice.services;

import com.scalar.productservice.ProductserviceApplication;
import com.scalar.productservice.dtos.FakeStoreProductDto;
import com.scalar.productservice.exceptions.ProductNotExistException;
import com.scalar.productservice.models.Category;
import com.scalar.productservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestore")
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;

    @Autowired
    public FakeStoreProductService(RestTemplate restTemplate){
        this.restTemplate=restTemplate;
    }


    @Override
    public List<Product> getAllProduct() {
        FakeStoreProductDto[] response=restTemplate.getForObject("https://fakestoreapi.com/products",FakeStoreProductDto[].class);

        List<Product> p= new ArrayList<>();
        for(FakeStoreProductDto r:response){
            p.add(convertFSPtoP(r));
        }
        return p;

    }

    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
         FakeStoreProductDto ProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);

         if(ProductDto==null) {
             throw new ProductNotExistException("Product with id: " + id + " doesn't exist.");
         }
          return convertFSPtoP(ProductDto);
    }

    @Override
    public Product addNewProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto=convertPtoFSP(product);
         FakeStoreProductDto productDto= restTemplate.postForObject("https://fakestoreapi.com/products",fakeStoreProductDto, FakeStoreProductDto.class);

         return convertFSPtoP(productDto);

    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto=convertPtoFSP(product);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(fakeStoreProductDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto ProductDto= restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFSPtoP(ProductDto);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProduct=convertPtoFSP(product);

        FakeStoreProductDto resp=restTemplate.patchForObject("https://fakestoreapi.com/products/"+id,fakeStoreProduct,FakeStoreProductDto.class);
        return convertFSPtoP(resp);

    }

    @Override
    public void deleteproduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/"+id);
    }

    public Product convertFSPtoP(FakeStoreProductDto fakeStoreProduct){
        Product product=new Product();
        product.setTitle(fakeStoreProduct.getTitle());
        product.setId(fakeStoreProduct.getId());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());
        product.setImageUrl(fakeStoreProduct.getImage());
        product.setCategory(new Category());
        product.getCategory().setName(fakeStoreProduct.getCategory());
        product.getCategory().setId(fakeStoreProduct.getId());

        return product;
    }

    public FakeStoreProductDto convertPtoFSP(Product product){
        FakeStoreProductDto fakeStoreProductDto=new FakeStoreProductDto();
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());

        return fakeStoreProductDto;
    }
}
