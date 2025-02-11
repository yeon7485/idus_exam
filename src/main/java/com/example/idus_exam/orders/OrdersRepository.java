package com.example.idus_exam.orders;

import com.example.idus_exam.orders.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

}
