package com.example.idus_exam.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

        public static UserResponse from(User user) {
            return UserResponse.builder()
                    .idx(user.getIdx())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .email(user.getEmail())
                    .phone(user.getPhone())
                    .gender(user.getGender())
                    .build();
        }
    }
}
