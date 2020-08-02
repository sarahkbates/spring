package com.bates.spring.dao;


import com.bates.spring.model.Users;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Long> {
    Users findByEmail(String email);
}
