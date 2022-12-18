package com.dn.shoptech.service.impl;

import com.dn.shoptech.dto.UserDto;
import com.dn.shoptech.model.User;
import com.dn.shoptech.repository.UserRepo;
import com.dn.shoptech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public final UserRepo USER_REPO;

    public Page<User> findAll(Pageable pageable) {
        return USER_REPO.findAll(pageable);
    }

    @Override
    public <S extends User> S save(S entity) {
        return USER_REPO.save(entity);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        return USER_REPO.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return USER_REPO.existsById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        USER_REPO.deleteById(aLong);
    }

    @Override
    public void updateInfoUser(UserDto userDto) {
        USER_REPO.updateInfoUser(userDto);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return USER_REPO.findByUsername(username);
    }


    @Override
    public <S extends User> long count(Example<S> example) {
        return USER_REPO.count(example);
    }
}
