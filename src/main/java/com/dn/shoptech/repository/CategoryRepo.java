package com.dn.shoptech.repository;

import com.dn.shoptech.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CategoryRepo extends JpaRepository<Category,Long> {
    @Query("SELECT u FROM Category u WHERE u.active = true")
    Page<Category> findAll(Pageable pageable);

    Boolean existsByName(String name);

    Optional<Category> findByName(String name);

    @Query("SELECT new Category(c.id,c.name) FROM Category c")
    List<Category> getMenuCategory();
}
