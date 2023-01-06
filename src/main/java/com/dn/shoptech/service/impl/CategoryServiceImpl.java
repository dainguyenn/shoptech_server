package com.dn.shoptech.service.impl;

import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.model.Category;
import com.dn.shoptech.repository.CategoryRepo;
import com.dn.shoptech.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public <S extends Category> List<S> saveAll(Iterable<S> entities) {
        return categoryRepo.saveAll(entities);
    }

    @Override
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepo.findAll(pageable);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public <S extends Category> S save(S entity) {
        return categoryRepo.save(entity);
    }

    @Override
    public Category findById(Long aLong) {
        return categoryRepo.findById(aLong).orElseThrow(() -> {
            throw new ApiRequestException("Category with id " + aLong + " is not exist");
        });
    }

    @Override
    public void deleteAllByIdInBatch(List<Long> ids) {
        categoryRepo.deleteAllByIdInBatch(ids);
    }

    @Override
    public List<Category> getMenuCategory() {
        return categoryRepo.getMenuCategory();

    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepo.existsByName(name);
    }

    @Override
    public boolean existsById(Long aLong) {
        return categoryRepo.existsById(aLong);
    }

    @Override
    public long count() {
        return categoryRepo.count();
    }

    @Override
    public void deleteById(Long aLong) {
        categoryRepo.deleteById(aLong);
    }

    @Override
    public <S extends Category> Optional<S> findOne(Example<S> example) {
        return categoryRepo.findOne(example);
    }
}

