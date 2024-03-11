package com.huxton.microservice.sample.userservice.user.dao;

import com.huxton.microservice.sample.userservice.user.dto.UserDto;
import com.huxton.microservice.sample.userservice.user.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query(
            nativeQuery = true,
            value = "select u.id, u.name, u.email, u.password from t_user u " +
                    "where :search = '' or (u.name = :search or u.password = :search)"
    )
    Page<User> getUsers(@Param(value = "search") String search, Pageable pageable);
}
