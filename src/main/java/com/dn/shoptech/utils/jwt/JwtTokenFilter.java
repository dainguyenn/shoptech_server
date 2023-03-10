package com.dn.shoptech.utils.jwt;

import com.dn.shoptech.model.Role;
import com.dn.shoptech.model.User;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (!hasAuthorizationHeader(request)) {
            filterChain.doFilter(request,response);
            return;
        }
        String accessToken = getAccessToken(request);
        if (!jwtTokenUtil.validateAccessToken(accessToken)){
            filterChain.doFilter(request,response);
            return;
        }

        setAuthenticationContext(accessToken,request);
        filterChain.doFilter(request, response);

    }

    private void setAuthenticationContext(String accessToken, HttpServletRequest request) {
        UserDetails userDetails = getUserDetail(accessToken);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    private UserDetails getUserDetail(String accessToken) {
        User userDetail = new User();
        Claims claim = jwtTokenUtil.pareClaims(accessToken);

        String claimRoles = (String) claim.get("roles");
        claimRoles = claimRoles.replace("[","").replace("]","");
        String[] roleNames = claimRoles.split(",");

        for (String aRoleName : roleNames){
            userDetail.addRole(new Role(aRoleName));
        }

        String subject =  jwtTokenUtil.getSubject(accessToken);
        userDetail.setUsername(subject);
        return userDetail;
    }


    private boolean hasAuthorizationHeader(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer") ){
            return false;
        }


        return true;
    }

    private String getAccessToken(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();

        return token;
    }
}
