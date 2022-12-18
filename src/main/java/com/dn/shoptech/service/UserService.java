package com.dn.shoptech.service;

import com.dn.shoptech.dto.UserDto;
import com.dn.shoptech.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Page<User> findAll(Pageable page);

    <S extends User> S save(S entity);

    Optional<User> findById(Long aLong);

    boolean existsById(Long aLong);

    void deleteById(Long aLong);


    void updateInfoUser(UserDto userDto);

    Optional<User> findByUsername(String username);


    <S extends User> long count(Example<S> example);
}
