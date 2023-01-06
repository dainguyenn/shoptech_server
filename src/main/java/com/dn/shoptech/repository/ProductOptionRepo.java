package com.dn.shoptech.repository;

import com.dn.shoptech.model.ProductOption;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOptionRepo extends JpaRepository<Long, ProductOption> {
}
