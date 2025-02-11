package com.example.idus_exam.user.model;

import com.example.idus_exam.orders.model.OrdersDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {
    @Getter
    public static class SignupRequest {
        @Schema(description = "이름", example = "홍길동")
        private String name;
        @Schema(description = "별명", example = "gildong")
        private String nickName;
        @Schema(description = "비밀번호", example = "password123")
        private String password;
        @Schema(description = "전화번호", example = "010-1234-5678")
        private String phone;
        @Schema(description = "이메일", example = "gildong@example.com")
        private String email;
        @Schema(description = "성별", example = "male")
        private String gender;

        public User toEntity(String encodedPassword) {
            return User.builder()
                    .name(name)
                    .nickName(nickName)
                    .password(encodedPassword)
                    .phone(phone)
                    .email(email)
                    .gender(gender)
                    .build();
        }
    }

    @Getter
    public static class LoginRequest {
        @Schema(description = "이메일", example = "gildong@example.com")
        private String email;
        @Schema(description = "비밀번호", example = "password123")
        private String password;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserResponse {
        @Schema(description = "idx", example = "1")
        private Long idx;
        @Schema(description = "이름", example = "홍길동")
        private String name;
        @Schema(description = "별명", example = "gildong")
        private String nickName;
        @Schema(description = "전화번호", example = "010-1234-5678")
        private String phone;
        @Schema(description = "이메일", example = "gildong@example.com")
        private String email;
        @Schema(description = "성별", example = "male")
        private String gender;

        private OrdersDto.OrdersResponse lastOrders;

        public static UserResponse from(User user) {
            OrdersDto.OrdersResponse lastOrderDto = user.getOrderList().isEmpty()
                    ? null
                    : OrdersDto.OrdersResponse.from(user.getOrderList().get(user.getOrderList().size() - 1));

            return UserResponse.builder()
                    .idx(user.getIdx())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .lastOrders(lastOrderDto)
                    .build();
        }

    }


    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UserPageResponse {
        @Schema(description = "페이지 번호", example = "0")
        private int page;
        @Schema(description = "크기", example = "1")
        private int size;
        @Schema(description = "총 개수", example = "1")
        private long totalElements;
        @Schema(description = "총 페이지", example = "1")
        private int totalPages;
        @Schema(description = "다음 페이지", example = "false")
        private boolean hasNext;
        @Schema(description = "이전 페이지", example = "false")
        private boolean hasPrevious;

        private List<UserResponse> users;

        public static UserPageResponse from(Page<User> userPage) {
            return UserPageResponse.builder()
                    .page(userPage.getNumber())
                    .size(userPage.getSize())
                    .totalElements(userPage.getTotalElements())
                    .totalPages(userPage.getTotalPages())
                    .hasNext(userPage.hasNext())
                    .hasPrevious(userPage.hasPrevious())
                    .users(userPage.stream().map(UserDto.UserResponse::from).collect(Collectors.toList()))
                    .build();
        }
    }
}
