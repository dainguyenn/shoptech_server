package com.dn.shoptech.service;

import com.dn.shoptech.model.ProductOption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductOptionService {
    List<Long> findAll();

    Page<Long> findAll(Pageable pageable);

    <S extends Long> S save(S entity);

    Optional<Long> findById(ProductOption productOption);

    boolean existsById(ProductOption productOption);

    void deleteById(ProductOption productOption);

    void deleteAll(Iterable<? extends Long> entities);
}
