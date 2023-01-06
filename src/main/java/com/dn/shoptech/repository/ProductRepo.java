package com.dn.shoptech.repository;

import com.dn.shoptech.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface ProductRepo extends JpaRepository<Product,Long> {
    Boolean existsByName(String name);

//    Page<Product> getProductDiscount();
}
