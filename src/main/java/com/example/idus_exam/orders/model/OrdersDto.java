package com.example.idus_exam.orders.model;

import com.example.idus_exam.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class OrdersDto {
    @Getter
    public static class OrderRegister {
        @Schema(description = "주문번호", example = "order01")
        private String orderId;
        @Schema(description = "제품명", example = "product01")
        private String name;

        public Orders toEntity(User user) {
            return Orders.builder()
                    .orderId(orderId)
                    .name(name)
                    .user(user)
                    .build();
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class OrdersResponse {
        @Schema(description = "idx", example = "1")
        private Long idx;
        @Schema(description = "주문번호", example = "order01")
        private String orderId;
        @Schema(description = "제품명", example = "product01")
        private String name;
        @Schema(description = "결제일시", example = "2025-02-11T15:24:09.286965")
        private LocalDateTime date;

        public static OrdersResponse from(Orders orders) {
            return OrdersResponse.builder()
                    .idx(orders.getIdx())
                    .orderId(orders.getOrderId())
                    .name(orders.getName())
                    .date(orders.getDate())
                    .build();
        }

        public Orders toEntity() {
            return Orders.builder()
                    .idx(idx)
                    .orderId(orderId)
                    .name(name)
                    .date(date)
                    .build();
        }

    }

}

