package com.example.idus_exam.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

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
}
