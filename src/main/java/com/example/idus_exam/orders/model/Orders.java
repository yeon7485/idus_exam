package com.example.idus_exam.orders.model;

import com.example.idus_exam.user.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Builder
public class Orders {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idx;
    private String orderId;
    private String name;
    @CreationTimestamp
    @Column(name = "date")
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="user_idx")
    private User user;
}
