package com.dn.shoptech.api.adminApi;

import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.model.User;
import com.dn.shoptech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin/user-manager")
@RequiredArgsConstructor
@RolesAllowed("ROLE_ADMIN")
public class UserManagerApi {
    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<Object> getUsers(@RequestParam(defaultValue = "1") Integer page,
                                           @RequestParam(defaultValue="10") Integer size){
        page=page-1;
        Pageable pageable = PageRequest.of(page,size, Sort.by("id"));
        Page pageResult = userService.findAll(pageable);

        HashMap<String,Object> response = new HashMap<String,Object>();
        response.put("content",pageResult.getContent());
        response.put("totalPages",pageResult.getTotalPages());
        response.put("currentPage",pageResult.getNumber()+1);
        response.put("totalItems",pageResult.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserByID(@PathVariable Long id){
        Optional<User> OUser = userService.findById(id);

        if(OUser.isPresent()){
            User user = OUser.get();
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found user with id:"+id);
    }

    @PostMapping("/active/{id}")
    public ResponseEntity<Object> changeActiveUser(@PathVariable Long id){
        Optional<User> OUser = userService.findById(id);

        if (OUser.isPresent()){
            User user = OUser.get();
            user.setActive(!user.getActive());
            userService.save(user);
            return ResponseEntity.ok("change active success");
        }else throw new ApiRequestException("change active failed");


    }


}
