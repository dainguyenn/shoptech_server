package com.dn.shoptech.funct.token;

import com.dn.shoptech.model.User;
import com.dn.shoptech.service.UserService;
import com.dn.shoptech.utils.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class TokenFunction {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    public User getUserByToken(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization").split(" ")[1];
        Optional<User> OUser = userService.findByUsername(jwtTokenUtil.getSubject(authorization));

        return OUser.get();

    }
}
