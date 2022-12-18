package com.dn.shoptech.service.impl;

import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.model.Category;
import com.dn.shoptech.model.Product;
import com.dn.shoptech.repository.CategoryRepo;
import com.dn.shoptech.repository.ProductRepo;
import com.dn.shoptech.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepo.findAll(pageable);
    }

    @Override
    public <S extends Product> S save(S entity) {
        if(entity.getId()!=null){
            return productRepo.save(entity);
        }else if(!categoryRepo.existsByName(entity.getCategory_name())){
            throw new ApiRequestException("Category "+entity.getCategory_name()+" is not exists");
        }else {
            return productRepo.save(entity);
        }

    }

    @Override
    public Product findById(Long aLong) {
        return productRepo.findById(aLong).orElseThrow(() -> {
            throw new ApiRequestException("Product with id "+aLong+" not exist");
        });
    }

    @Override
    public boolean existsById(Long aLong) {
        return productRepo.existsById(aLong);
    }

    @Override
    public boolean existsByName(String name) {
        return productRepo.existsByName(name);
    }

    @Override
    public long count() {
        return productRepo.count();
    }


    @Override
    public void deleteById(Long aLong) {
        productRepo.deleteById(aLong);
    }



    public void deleteAllByIdInBatch(Iterable<Long> longs) {
        productRepo.deleteAllByIdInBatch(longs);
    }
}
