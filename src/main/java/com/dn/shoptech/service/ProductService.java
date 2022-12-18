package com.dn.shoptech.service;

import com.dn.shoptech.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    Page<Product> findAll(Pageable pageable);

    <S extends Product> S save(S entity);

    Product findById(Long aLong);

    boolean existsById(Long aLong);

    boolean existsByName(String name);

    long count();

    void deleteById(Long aLong);

    public void deleteAllByIdInBatch(Iterable<Long> longs);
}
