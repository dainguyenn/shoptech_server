package com.dn.shoptech.service.impl;

import com.dn.shoptech.model.ProductOption;
import com.dn.shoptech.repository.ProductOptionRepo;
import com.dn.shoptech.service.ProductOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductOptionServiceImpl implements ProductOptionService {
    private final ProductOptionRepo productOptionRepo;

    @Override
    public List<Long> findAll() {
        return productOptionRepo.findAll();
    }

    @Override
    public Page<Long> findAll(Pageable pageable) {
        return productOptionRepo.findAll(pageable);
    }

    @Override
    public <S extends Long> S save(S entity) {
        return productOptionRepo.save(entity);
    }

    @Override
    public Optional<Long> findById(ProductOption productOption) {
        return productOptionRepo.findById(productOption);
    }

    @Override
    public boolean existsById(ProductOption productOption) {
        return productOptionRepo.existsById(productOption);
    }

    @Override
    public void deleteById(ProductOption productOption) {
        productOptionRepo.deleteById(productOption);
    }

    @Override
    public void deleteAll(Iterable<? extends Long> entities) {
        productOptionRepo.deleteAll(entities);
    }
}
