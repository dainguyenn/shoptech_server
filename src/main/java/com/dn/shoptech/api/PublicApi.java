package com.dn.shoptech.api;

import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.model.User;
import com.dn.shoptech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/public")
@RequiredArgsConstructor
public class PublicApi {
    private final UserService userService;


    @RolesAllowed("ROLE_ADMIN")
    @CrossOrigin("http://localhost:3000")
    @GetMapping("")
    public String GetHome(){
        return "Home";
    }

    @CrossOrigin("http://localhost:3000")
    @GetMapping("/user")
    public ResponseEntity<Object> GetAllUser(){

        return ResponseEntity.ok((User) userService.findById(1L).get());
    }
}
