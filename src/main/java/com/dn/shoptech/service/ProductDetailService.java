package com.dn.shoptech.service;

import com.dn.shoptech.repository.ProductDetailRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductDetailService {
    List<Long> findAll();

    Page<Long> findAll(Pageable pageable);

    <S extends Long> S save(S entity);

    Optional<Long> findById(ProductDetailRepo productDetailRepo);

    boolean existsById(ProductDetailRepo productDetailRepo);

    void deleteById(ProductDetailRepo productDetailRepo);
}
