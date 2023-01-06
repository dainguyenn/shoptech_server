package com.dn.shoptech.service.impl;

import com.dn.shoptech.repository.ProductDetailRepo;
import com.dn.shoptech.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ProductDetailServiceImpl implements ProductDetailService {
    private final ProductDetailRepo productDetailRepo;

    @Override
    public List<Long> findAll() {
        return productDetailRepo.findAll();
    }

    @Override
    public Page<Long> findAll(Pageable pageable) {
        return productDetailRepo.findAll(pageable);
    }

    @Override
    public <S extends Long> S save(S entity) {
        return productDetailRepo.save(entity);
    }

    @Override
    public Optional<Long> findById(ProductDetailRepo productDetailRepo) {
        return this.productDetailRepo.findById(productDetailRepo);
    }

    @Override
    public boolean existsById(ProductDetailRepo productDetailRepo) {
        return this.productDetailRepo.existsById(productDetailRepo);
    }

    @Override
    public void deleteById(ProductDetailRepo productDetailRepo) {
        this.productDetailRepo.deleteById(productDetailRepo);
    }
}
