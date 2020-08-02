package com.bates.spring.services.implementations;


import com.bates.spring.dto.UserDto;
import com.bates.spring.exceptionHandler.ApiRequestException;
import com.bates.spring.model.Users;
import com.bates.spring.model.response.UserResponse;
import com.bates.spring.services.UserService;
import com.bates.spring.dao.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.*;

@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userrepository;

    public UserServiceImplementation(UserRepository userrepository) {
        this.userrepository = userrepository;
    }


    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<Users> returnValue;
        if (page > 0 ) page--;
        Pageable request = PageRequest.of(page, limit);
        Page<Users> usersPage = userrepository.findAll(request);
        returnValue = usersPage.getContent();
        ArrayList<UserDto> userDtoArrayList = new ArrayList<UserDto>();
        for(Object x : returnValue){
            UserDto target = new UserDto();
            BeanUtils.copyProperties(x, target);
            userDtoArrayList.add(target);
        }
        System.out.println(userDtoArrayList.get(0).getId() + " This is the return Value");
        return userDtoArrayList;
    }

    @Override
    public UserDto createUser(UserDto userdto) {
        if(userdto.getEmail() == null) throw new ApiRequestException("Most Have an Email");
        Users newUser = new Users();
        BeanUtils.copyProperties(userdto, newUser);
        Users currentUser = userrepository.save(newUser);
        UserDto returnUser = new UserDto();
        BeanUtils.copyProperties(currentUser, returnUser);
        return returnUser;
    }

    @Override
    public UserResponse getUser(Long id) {
        ArrayList<Users> users = (ArrayList<Users>) userrepository.findAll();
        UserResponse userResponse = new UserResponse();
        for(int i =0; i < users.size(); i++){
            if(users.get(i).getId().equals(id)){
                BeanUtils.copyProperties(users.get(i), userResponse);
                return userResponse;
            }
        }

        throw new ApiRequestException("ID did not match any in the database");
    }

    @Override
    public UserResponse getUsersByEmail(String email) {
        ArrayList<Users> users = (ArrayList<Users>) userrepository.findAll();
        UserResponse currentUser = new UserResponse();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                BeanUtils.copyProperties(users.get(i), currentUser);
                System.out.println("We are in the user email section");
                return currentUser;
            }
        }
        throw new ApiRequestException("Email did not match any in the database");
    }

    @Override
    public UserResponse updateUser(Users user) {
        ArrayList<Users> users = (ArrayList<Users>) userrepository.findAll();
        UserResponse currentUser = new UserResponse();
        for (int i = 0; i < users.size(); i++) {
            System.out.println("In the for");
            if (users.get(i).getId().equals(user.getId())) {
                userrepository.save(user);
                System.out.println(user + " This is the user");
                BeanUtils.copyProperties(user, currentUser);
                System.out.println(currentUser + " This is what should be returned");
                return currentUser;
            }
        }
        throw new ApiRequestException("It Appears that user does not exist.");
    }

    @Override
    public void deleteUser(Long id) {
        if(userrepository.findById(id) == null) throw new ApiRequestException("That User Doesn't Exist.");
        userrepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<Users> returnValue;
        ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
        returnValue = (List<Users>) userrepository.findAll();
        for(Object x : returnValue){
            UserResponse target = new UserResponse();
            BeanUtils.copyProperties(x, target);
            userResponses.add(target);
        }
        return userResponses;
    }

}
