package com.example.idus_exam.orders.model;

import com.example.idus_exam.user.model.User;
import lombok.Getter;

public class OrdersDto {
    @Getter
    public static class OrderRegister {
        private String name;

        public Orders toEntity(User user) {
            return Orders.builder()
                    .name(name)
                    .user(user)
                    .build();
        }
    }
}

