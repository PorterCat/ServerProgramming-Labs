package com.example.demo.service;

import com.example.demo.models.Customer;
import com.example.demo.models.Order;
import com.example.demo.models.entity.Payment;
import com.example.demo.models.payment.Cash;
import com.example.demo.models.payment.Check;
import com.example.demo.models.payment.Credit;
import com.example.demo.repository.OrderRepository;
import jakarta.persistence.criteria.Join;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
public class OrderService
{
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> findOrders(
            String city,
            LocalDateTime startDate,
            LocalDateTime endDate,
            String paymentType,
            String creditCardType
    ) {
        return orderRepository.findAll((root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (city != null) {
                Join<Order, Customer> customerJoin = root.join("customer");
                predicates.add(cb.equal(customerJoin.get("address").get("city"), city));
            }

            if (startDate != null && endDate != null) {
                predicates.add(cb.between(root.get("date"), startDate, endDate));
            }

            if (paymentType != null) {
                Join<Order, Payment> paymentJoin = root.join("payments");
                predicates.add(cb.equal(paymentJoin.get("paymentType"), paymentType));
            }

            if (creditCardType != null) {
                Join<Order, Payment> paymentJoin = root.join("payments");
                predicates.add(cb.equal(paymentJoin.get("type"), creditCardType));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }

    private Class<? extends Payment> getPaymentClass(String type) {
        return switch (type.toUpperCase()) {
            case "CASH" -> Cash.class;
            case "CHECK" -> Check.class;
            case "CREDIT" -> Credit.class;
            default -> throw new IllegalArgumentException("Invalid payment type: " + type);
        };
    }
}
