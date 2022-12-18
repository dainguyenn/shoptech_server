package com.dn.shoptech.api.athApi;

import com.dn.shoptech.dto.UserDto;
import com.dn.shoptech.exception.ApiRequestException;
import com.dn.shoptech.model.Role;
import com.dn.shoptech.model.User;
import com.dn.shoptech.service.UserService;
import com.dn.shoptech.utils.jwt.AuthRequest;
import com.dn.shoptech.utils.jwt.AuthResponse;
import com.dn.shoptech.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthApi {
    final AuthenticationManager authenticationManager;
    final JwtTokenUtil jwtUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        log.info("USer : {}",request);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            User user = (User) authentication.getPrincipal();
            String accessToken = jwtUtil.GenerateAccessToken(user);
            AuthResponse responseToken = new AuthResponse(user.getUsername(), accessToken);

            HashMap<String, Object> dataResp = new HashMap<>();
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            userDto.setPassword(null);
            dataResp.put("Info", userDto);
            dataResp.put("Token", responseToken);
            return ResponseEntity.ok(dataResp);

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect account or password");
        }
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Object> register(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new ApiRequestException("Error in valid some field");
        }
        Optional<User> check = userService.findByUsername(user.getUsername());
        if (check.isPresent()) {
            throw new ApiRequestException("user already exist");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return ResponseEntity.ok("register success");
        }


    }



}
