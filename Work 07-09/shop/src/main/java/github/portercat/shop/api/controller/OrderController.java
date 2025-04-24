package github.portercat.shop.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import github.portercat.shop.api.dto.OrderRequestDto;
import github.portercat.shop.api.dto.OrderResponseDto;
import github.portercat.shop.api.dto.SuccessResponseDto;
import github.portercat.shop.core.model.entity.user.Role;
import github.portercat.shop.core.model.entity.user.User;
import github.portercat.shop.core.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@AuthenticationPrincipal User user,
                                                        @RequestBody OrderRequestDto orderRequestDto)
    {
        OrderResponseDto response = orderService.createOrder(user.getId(), orderRequestDto);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getAllOrders(@AuthenticationPrincipal User user)
    {
        if (user.getRole() == Role.ADMIN)
        {
            List<OrderResponseDto> orders = orderService.getAllOrders();
            return ResponseEntity.ok(orders);
        }

        List<OrderResponseDto> orders = orderService.getAllOrders(user.getId());
        return ResponseEntity.ok(orders);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<SuccessResponseDto> deleteOrder(@AuthenticationPrincipal User user,
                                                          @PathVariable Long orderId)
    {
        if (user.getRole() == Role.ADMIN)
            return ResponseEntity.ok(orderService.deleteOrder(orderId));

        return ResponseEntity.ok(orderService.deleteOrder(user.getId(), orderId));
    }
}
