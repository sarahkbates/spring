package com.bates.spring;


import com.bates.spring.dto.UserDto;
import main.java.com.bates.spring.model.Users;
import main.java.com.bates.spring.model.request.UserRequest;
import main.java.com.bates.spring.model.response.response.UserResponse;
import com.bates.spring.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.BeanUtils;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userservice;

    public UserController(UserService userservice){
        this.userservice = userservice;
    }

    @GetMapping(path = "/allUsers")
    public List<UserResponse> getAllUsers(){
        List<UserResponse> returnValue = userservice.getAllUsers();
        return returnValue;
    }

    @GetMapping
    public List<UserDto> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                                  @RequestParam(value = "limit", defaultValue = "3") int limit){
        List<UserDto> returnValue = userservice.getUsers(page, limit);
        return returnValue;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){
        UserDto userdto = new UserDto();
        BeanUtils.copyProperties(userRequest, userdto);
        UserDto createUser = userservice.createUser(userdto);
        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createUser, userResponse);
        return userResponse;
    }

    @GetMapping(path = "/{id}")
    public UserResponse getUsers(@PathVariable Long id){
        UserResponse returnValue = userservice.getUser(id);
        System.out.println("Got here in controller");
        return returnValue;
    }
    @GetMapping(path = "/email/{email}")
    public UserResponse getUsersByEmail(@PathVariable String email){
        UserResponse returnValue = userservice.getUsersByEmail(email);
        System.out.println("In the controller with email");
        return returnValue;
    }

    @PutMapping
    public UserResponse updateUser(@RequestBody Users user){
        System.out.println("In the put mapping");
        UserResponse returnValue = userservice.updateUser(user);
        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable Long id){
        userservice.deleteUser(id);

    }

}