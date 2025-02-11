package com.example.idus_exam.user;

import com.example.idus_exam.orders.OrdersService;
import com.example.idus_exam.orders.model.Orders;
import com.example.idus_exam.orders.model.OrdersDto;
import com.example.idus_exam.user.model.User;
import com.example.idus_exam.user.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final OrdersService ordersService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(UserDto.SignupRequest dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = userRepository.save(dto.toEntity(encodedPassword));
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> result = userRepository.findByEmail(username);

        if(result.isPresent()) {
            User user = result.get();
            return user;
        }
        return null;
    }

    @Transactional(readOnly = true)
    public UserDto.UserResponse read(Long userIdx) {
        User user = userRepository.findById(userIdx).orElseThrow();
        return UserDto.UserResponse.from(user);
    }

    @Transactional(readOnly = true)
    public UserDto.UserPageResponse list(int page, int size) {
        Page<User> result = userRepository.findAll(PageRequest.of(page, size));

        if(result.hasContent()) {
            for(User user : result.getContent()) {
                List<Orders> ordersList = new ArrayList<>();
                List<OrdersDto.OrdersResponse> ordersResList = ordersService.readByUser(user.getIdx());

                for(OrdersDto.OrdersResponse ordersRes: ordersResList) {
                    Orders orders = ordersRes.toEntity();
                    ordersList.add(orders);
                }
                user.updateOrderList(ordersList);
            }
        }
        return UserDto.UserPageResponse.from(result);
    }
}
