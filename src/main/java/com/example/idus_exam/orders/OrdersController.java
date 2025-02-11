package com.example.idus_exam.orders;

import com.example.idus_exam.orders.model.OrdersDto;
import com.example.idus_exam.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService orderService;

    @Operation(summary = "주문 등록", description = "주문 등록 기능입니다.")
    @PostMapping("/register")
    public ResponseEntity<String> register(@AuthenticationPrincipal User user, @RequestBody OrdersDto.OrderRegister dto) {
        orderService.register(dto, user);

        return ResponseEntity.ok("주문 완료!");
    }
}
