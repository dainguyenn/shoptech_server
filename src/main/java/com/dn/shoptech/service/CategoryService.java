package com.dn.shoptech.service;

import com.dn.shoptech.model.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    <S extends Category> List<S> saveAll(Iterable<S> entities);

    Page<Category> findAll(Pageable pageable);

    <S extends Category> S save(S entity);

    Category findById(Long aLong);

    boolean existsById(Long aLong);

    boolean existsByName(String name);

    long count();

    void deleteById(Long aLong);

    <S extends Category> Optional<S> findOne(Example<S> example);

    void deleteAllByIdInBatch(List<Long> ids);
}
