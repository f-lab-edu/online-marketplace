package com.coupang.marketplace.user.repository;

import com.coupang.marketplace.user.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Mapper
@Repository
public interface UserRepository {

    void insertUser(User user);

    Optional<User> findByEmail(String email);

    void updateUser(User user);
}
