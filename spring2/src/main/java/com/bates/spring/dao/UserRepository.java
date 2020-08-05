package main.java.com.bates.spring.dao;


import main.java.com.bates.spring.model.Users;


@Repository
public interface UserRepository extends PagingAndSortingRepository<Users, Long> {
    Users findByEmail(String email);
}
