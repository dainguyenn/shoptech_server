package com.dn.shoptech.api.adminApi;

import com.dn.shoptech.customBody.ListLongId;
import com.dn.shoptech.dto.CategoryDto;
import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.funct.pageable.ResponsePage;
import com.dn.shoptech.funct.token.TokenFunction;
import com.dn.shoptech.model.Category;
import com.dn.shoptech.service.CategoryService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/admin/category")
@RolesAllowed({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EDITOR"})
@Slf4j
public class CategoryApi {
    private final CategoryService categoryService;
    private final TokenFunction tokenFunction;
    private final ResponsePage responsePage;

    @GetMapping("")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_MANAGER", "ROLE_EDITOR","ROLE_USER"})
    public ResponseEntity<Object> getCategories(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                @RequestParam(name = "page", defaultValue = "10") Integer size) {

        try {
            page = page-1;
            Pageable pageable = PageRequest.of(page, size, Sort.by("id"));

            return ResponseEntity.ok(responsePage.response(categoryService.findAll(pageable)));

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("create")
    public ResponseEntity<Object> createCategory(@RequestBody @Valid Category category, BindingResult checkValid,
                                                 HttpServletRequest request) {

        if (checkValid.hasErrors()) throw new ApiRequestException("some field invalid");

        if(!categoryService.existsByName(category.getName())){
            category.setCreate_by(tokenFunction.getUserByToken(request).getUsername());
            categoryService.save(category);
            return ResponseEntity.ok("Create success");
        }else throw new ApiRequestException("Category "+category.getName()+" already exists");


    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok("Delete category success");
        } catch (Exception e) {
            throw new ApiRequestException("Category with id " + id +  "is not exist");
        }

    }

    @PutMapping("edit")
    public ResponseEntity<Object> editCategory(@RequestBody CategoryDto categoryDto,
                                               BindingResult result,
                                               HttpServletRequest request) {

        if (result.hasErrors()) throw new ApiRequestException("some field invalid");

        Category category = categoryService.findById(categoryDto.getId());

        category.setName(categoryDto.getName());
        category.setModified_by(tokenFunction.getUserByToken(request).getUsername());
        category.setModified_at(categoryDto.getCurrentDate());

        categoryService.save(category);

        return ResponseEntity.ok("Edit category success");
    }

    @PatchMapping("active/{id}")
    public ResponseEntity<Object> changeActiveCategory(@PathVariable Long id){
        Category category = categoryService.findById(id);
        category.setActive(!category.getActive());
        categoryService.save(category);

        return ResponseEntity.ok("Change active Category success");
    }

    @DeleteMapping({"delete/{id}","delete"})
    public ResponseEntity deleteCategory(@PathVariable(value = "id",required = false) Long id,
                                         @RequestBody(required = false) ListLongId ids){
        if(id!=null){
            try {
                categoryService.deleteById(id);
                return ResponseEntity.ok("Delete Product success");
            }catch (Exception e){
                return ResponseEntity.ok("Delete Product success");
            }
        }else{
            try {
                categoryService.deleteAllByIdInBatch(ids.getIds());
            }catch (Exception e){
                return ResponseEntity.ok("Delete Products success");
            }
        }

        return ResponseEntity.ok("Delete Product success");
    }

}
