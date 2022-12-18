package com.dn.shoptech.service;

import com.dn.shoptech.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();

    <S extends Role> S save(S entity);

    Optional<Role> findById(Integer integer);

    boolean existsById(Integer integer);

    long count();

    void deleteById(Integer integer);

    void delete(Role entity);
}
