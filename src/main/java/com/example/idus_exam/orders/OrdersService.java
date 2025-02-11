package com.example.idus_exam.orders;

import com.example.idus_exam.orders.model.Orders;
import com.example.idus_exam.orders.model.OrdersDto;
import com.example.idus_exam.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrdersService {
    private final OrdersRepository ordersRepository;

    @Transactional
    public void register(OrdersDto.OrderRegister dto, User user) {
        Orders order = ordersRepository.save(dto.toEntity(user));

    }

    public List<OrdersDto.OrdersResponse> readByUser(Long userIdx) {
        List<Orders> result = ordersRepository.findAllByUserIdx(userIdx);
        List<OrdersDto.OrdersResponse> orderList = new ArrayList<>();

        for (Orders order : result) {
            OrdersDto.OrdersResponse dto = OrdersDto.OrdersResponse.from(order);
            orderList.add(dto);
        }
        return orderList;
    }


}
