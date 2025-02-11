package com.example.idus_exam.user.model;

import com.example.idus_exam.orders.model.Orders;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String name;
    private String nickName;
    private String password;
    private String phone;
    private String email;
    private String gender;

    @OneToMany(mappedBy = "user")
    private List<Orders> orderList = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    public void updateOrderList(List<Orders> orderList) {
        this.orderList = orderList;
    }
}
