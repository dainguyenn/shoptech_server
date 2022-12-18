package com.dn.shoptech.api.adminApi;

import com.dn.shoptech.customBody.ListLongId;
import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.funct.pageable.ResponsePage;
import com.dn.shoptech.funct.token.TokenFunction;
import com.dn.shoptech.model.Product;
import com.dn.shoptech.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/product")
@RolesAllowed({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EDITOR"})
@Slf4j
public class ProductApi {
    private final ProductService productService;
    private final ResponsePage responsePage;
    private final TokenFunction tokenFunction;

    @GetMapping("")
    public ResponseEntity<Object> getAllProducts(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                 @RequestParam(name = "page", defaultValue = "10") Integer size) {
        try {
            Pageable pageable = PageRequest.of(page - 1, size, Sort.by("id"));

            return ResponseEntity.ok(responsePage.response(productService.findAll(pageable)));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("create")
    public ResponseEntity<Object> createProduct(@RequestBody @Valid Product product,
                                                BindingResult result,
                                                HttpServletRequest request) {
        if (result.hasErrors()) throw new ApiRequestException("Some field invalid");

        if (!productService.existsByName(product.getName())) {
            product.setCreate_by(tokenFunction.getUserByToken(request).getUsername());
            productService.save(product);

            return ResponseEntity.ok("Create Product success");
        } else throw new ApiRequestException("Product" + product.getName() + " already exists");
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateProduct(@RequestBody @Valid Product product,
                                                BindingResult result,
                                                HttpServletRequest request) {
        if (result.hasErrors()) throw new ApiRequestException("Some field invalid");

        try {
            product.setModified_by(tokenFunction.getUserByToken(request).getUsername());
            productService.save(product);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return ResponseEntity.ok("Update Product success");
    }

    @PatchMapping("active/{id}")
    public ResponseEntity changeActiveProduct(@PathVariable Long id){
        Product product = productService.findById(id);
        product.setActive(!product.getActive());

        productService.save(product);
        return ResponseEntity.ok("Change active Product success");
    }

    @DeleteMapping(value = {"delete/{id}","delete"})
    public ResponseEntity deleteProduct(@PathVariable(value = "id",required = false) Long id,
                                        @RequestBody(required = false) ListLongId ids
                                       ){

        log.info("list id: {}",ids);
        if(id!=null){
            try {
                productService.deleteById(id);
            }finally {
                return ResponseEntity.ok("Delete Product success");

            }
        }else{
            try {
                productService.deleteAllByIdInBatch(ids.getIds());

            }finally {
                return ResponseEntity.ok("Delete Products success");

            }
        }

    }
}

