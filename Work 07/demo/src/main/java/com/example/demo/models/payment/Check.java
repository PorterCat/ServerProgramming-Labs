package com.example.demo.models.payment;

import com.example.demo.models.entity.Payment;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CHECK")
public class Check extends Payment
{
    private String name;
    private String bankID;
}
