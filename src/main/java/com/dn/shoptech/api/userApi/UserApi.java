package com.dn.shoptech.api.userApi;

import com.dn.shoptech.dto.UserDto;
import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.funct.token.TokenFunction;
import com.dn.shoptech.model.User;
import com.dn.shoptech.service.UserService;
import com.dn.shoptech.utils.jwt.JwtTokenUtil;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
@Slf4j
@RolesAllowed({"ROLE_ADMIN" ,"ROLE_USER","ROLE_MANAGER","ROLE_EDITOR"})
public class UserApi {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final TokenFunction tokenFunction;


    @PutMapping("update-info")
    public ResponseEntity<Object> updateInfoUser(@RequestBody @Valid UserDto userDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) throw new ApiRequestException("some field id invalid");

        User user = tokenFunction.getUserByToken(request);
        try {
            userDto.setId(user.getId());
            userService.updateInfoUser(userDto);
            return ResponseEntity.ok("update success");
        }catch (Exception e) {
            throw new ApiRequestException("Failed to update user info");
        }


    }

    @PutMapping("change-password")
    public ResponseEntity<Object> updatePasswordUser(@RequestBody ObjectNode json,
                                                     HttpServletRequest request) {

        User user = tokenFunction.getUserByToken(request);

        if (passwordEncoder.matches(json.get("oldPassword").asText(), user.getPassword())) {
            String newPassword = passwordEncoder.encode(json.get("password").asText());
            user.setPassword(newPassword);
            userService.save(user);

            return ResponseEntity.ok("change password success");
        } else throw new ApiRequestException("Old password is not valid");


    }



}
