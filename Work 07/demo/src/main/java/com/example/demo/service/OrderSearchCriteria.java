package com.example.demo.service;

import com.example.demo.models.entity.Payment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSearchCriteria 
{
    private String customerName;
    private String addressCity;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String orderStatus;
    private String paymentType;
}
