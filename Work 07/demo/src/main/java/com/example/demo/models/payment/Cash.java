package com.example.demo.models.payment;

import com.example.demo.models.entity.Payment;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("CASH")
public class Cash extends Payment
{
    private float cashTendered;
}
