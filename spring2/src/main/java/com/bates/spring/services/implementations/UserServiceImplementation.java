package main.java.com.bates.spring.services.implementations;


import com.bates.spring.dto.UserDto;
import com.bates.spring.exceptionHandler.ApiRequestException;
import main.java.com.bates.spring.dao.UserRepository;
import main.java.com.bates.spring.model.Users;
import main.java.com.bates.spring.model.response.response.UserResponse;
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

    private final UserRepository useRrepository;

    public UserServiceImplementation(UserRepository userrepository, UserRepository useRrepository) {
        this.useRrepository = useRrepository;

    }


    @Override
    public List<UserDto> getUsers(int page, int limit) {
        List<Users> returnValue;
        if (page > 0 ) page--;
        Pageable request = PageRequest.of(page, limit);
        Page<Users> usersPage = userRepository.findAll(request);
        returnValue = usersPage.getContent();
        ArrayList<UserDto> userDtoArrayList = new ArrayList<UserDto>();
        for(Object x : returnValue){
            UserDto target = new UserDto();
            BeanUtils.copyProperties(x, target);
            userDtoArrayList.add(target);
        }
        System.out.println(userDtoArrayList.get(0).getId() + " This is the return value");
        return userDtoArrayList;
    }

    @Override
    public UserDto createUser(UserDto userdto) {
        if(userdto.getEmail() == null) throw new ApiRequestException("You must have an email");
        Users newUser = new Users();
        BeanUtils.copyProperties(userdto, newUser);
        Users currentUser = userRepository.save(newUser);
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

        throw new ApiRequestException("ID did not match any found in the database");
    }

    @Override
    public UserResponse getUsersByEmail(String email) {
        ArrayList<Users> users = (ArrayList<Users>) userRepository.findAll();
        UserResponse currentUser = new UserResponse();
        for(int i = 0; i < users.size(); i++){
            if(users.get(i).getEmail().equals(email)){
                BeanUtils.copyProperties(users.get(i), currentUser);
                System.out.println("This is the user email section");
                return currentUser;
            }
        }
        throw new ApiRequestException("Email did not match any found in the database");
    }

    @Override
    public UserResponse updateUser(Users user) {
        ArrayList<Users> users = (ArrayList<Users>) userRepository.findAll();
        UserResponse currentUser = new UserResponse();
        for (int i = 0; i < users.size(); i++) {
            System.out.println("In the for");
            if (users.get(i).getId().equals(user.getId())) {
                userrepository.save(user);
                System.out.println(user + " This is the user");
                BeanUtils.copyProperties(user, currentUser);
                System.out.println(currentUser + " This is what is expected to be returned");
                return currentUser;
            }
        }
        throw new ApiRequestException("That user does not exist.");
    }

    @Override
    public void deleteUser(Long id) {
        if(userRepository.findById(id) == null) throw new ApiRequestException("That User Doesn't Exist.");
        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<Users> returnValue;
        ArrayList<UserResponse> userResponses = new ArrayList<UserResponse>();
        returnValue = (List<Users>) userRepository.findAll();
        for(Object x : returnValue){
            UserResponse target = new UserResponse();
            BeanUtils.copyProperties(x, target);
            userResponses.add(target);
        }
        return userResponses;
    }

}
