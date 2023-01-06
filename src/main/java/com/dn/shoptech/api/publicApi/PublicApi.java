package com.dn.shoptech.api.publicApi;

import com.dn.shoptech.model.Category;
import com.dn.shoptech.service.CategoryService;
import com.dn.shoptech.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/v1")
public class PublicApi {
    private final CategoryService categoryService;
    private final ProductService productService;
    @GetMapping("category")
    public ResponseEntity<Object> getCategories(){
        try {
            List<Category> categories = categoryService.findAll();
            return ResponseEntity.ok(categories);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("home")
    public ResponseEntity<Object> getHome() {
        Map<String, Object> data = new HashMap<>();
        return null;
    }

}
