package com.scalar.productservice.dtos;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class UserDto {

    private String name;
    private String email;
    private List<Role> roles;

}
