package com.example.idus_exam.user;

import com.example.idus_exam.user.model.User;
import com.example.idus_exam.user.model.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입 기능입니다.\n\n" + "성별 : male, female")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto.SignupRequest dto) {
        userService.signup(dto);
        return ResponseEntity.ok("Signup successful");
    }

    @Operation(summary = "로그아웃", description = "로그아웃 기능입니다.")
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("ATOKEN", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful");
    }

    @Operation(summary = "단일 회원 상세 정보 조회", description = "단일 회원의 상세 정보 조회 기능입니다.")
    @GetMapping("/{userIdx}")
    public ResponseEntity<UserDto.UserResponse> getUser(@PathVariable Long userIdx) {
        UserDto.UserResponse response = userService.read(userIdx);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "여러 회원 목록 조회", description = "여러 회원 목록 조회 기능입니다.")
    @GetMapping("/list")
    public ResponseEntity<UserDto.UserPageResponse> getUserList(int page, int size) {
        UserDto.UserPageResponse response = userService.list(page, size);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 검색 (이름)", description = "이름을 이용하여 회원을 검색하는 기능입니다.")
    @GetMapping("/search/name")
    public ResponseEntity<UserDto.UserPageResponse> searchUserByName(String name, int page, int size) {
        UserDto.UserPageResponse response = userService.searchByName(page, size, name);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "회원 검색 (이메일)", description = "이메일을 이용하여 회원을 검색하는 기능입니다.")
    @GetMapping("/search/email")
    public ResponseEntity<UserDto.UserPageResponse> searchUserByEmail(String email, int page, int size) {
        UserDto.UserPageResponse response = userService.searchByEmail(page, size, email);

        return ResponseEntity.ok(response);
    }


}
