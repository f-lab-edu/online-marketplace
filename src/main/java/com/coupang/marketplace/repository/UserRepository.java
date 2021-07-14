package com.coupang.marketplace.repository;

import com.coupang.marketplace.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Mapper
@Repository
public interface UserRepository {

    void insertUser(User user);

    Optional<User> findByEmail(String email);

}
