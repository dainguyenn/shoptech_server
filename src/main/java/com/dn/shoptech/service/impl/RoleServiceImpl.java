package com.dn.shoptech.service.impl;

import com.dn.shoptech.model.Role;
import com.dn.shoptech.repository.RoleRepo;
import com.dn.shoptech.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo RoleRepo;

    @Override
    public List<Role> findAll() {
        return RoleRepo.findAll();
    }

    @Override
    public <S extends Role> S save(S entity) {
        return RoleRepo.save(entity);
    }

    @Override
    public Optional<Role> findById(Integer integer) {
        return RoleRepo.findById(integer);
    }

    @Override
    public boolean existsById(Integer integer) {
        return RoleRepo.existsById(integer);
    }

    @Override
    public long count() {
        return RoleRepo.count();
    }

    @Override
    public void deleteById(Integer integer) {
        RoleRepo.deleteById(integer);
    }

    @Override
    public void delete(Role entity) {
        RoleRepo.delete(entity);
    }
}
