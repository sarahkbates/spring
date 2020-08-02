package com.bates.spring.services;



import com.bates.spring.dto.UserDto;
import com.bates.spring.model.Users;
import com.bates.spring.model.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    List<UserDto> getUsers(int page, int limit);

    UserDto createUser(UserDto userdto);

    UserResponse getUser(Long id);

    UserResponse getUsersByEmail(String email);

    UserResponse updateUser(Users user);

    void deleteUser(Long id);

    List<UserResponse> getAllUsers();
}
