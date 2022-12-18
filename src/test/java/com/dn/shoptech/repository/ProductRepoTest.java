package com.dn.shoptech.repository;

import com.dn.shoptech.model.Category;
import com.dn.shoptech.model.Product;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@RequiredArgsConstructor
class ProductRepoTest {
    final ProductRepo ProductRepo;

    @Test
    void addProduct()  {
//        Product product = new Product("product 1","title 1","description 1","img","cate 1","a");
//
//        ProductRepo.save(product);
    }
}