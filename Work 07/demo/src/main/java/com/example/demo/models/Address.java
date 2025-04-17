package com.example.demo.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Address
{
    private String city;
    private String street;
    private String zipcode;
}
