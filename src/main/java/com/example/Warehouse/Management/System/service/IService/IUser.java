package com.example.Warehouse.Management.System.service.IService;

import com.example.Warehouse.Management.System.model.Order;
import com.example.Warehouse.Management.System.model.User;
import com.example.Warehouse.Management.System.payload.dto.UserDto;
import com.example.Warehouse.Management.System.payload.request.SignUpRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUser {

    Page<User> listAllUsers(Pageable pageable);

    User getUserById(Long id);

    User saveNewUser(SignUpRequest userRequest);

    void deleteUserById(Long id);

    User updateUserById(UserDto user, Long id);
}
