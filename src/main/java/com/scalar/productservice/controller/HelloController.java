package com.scalar.productservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")

public class HelloController {

    @GetMapping("/say/{name}/{time}")
    public String sayHello(@PathVariable("name") String name ,@PathVariable("time") int n){

        String s="";

        for(int i=0; i<n; i++){
            s+=name;
            s+="</br>";
        }
        return s   ;
    }
}
