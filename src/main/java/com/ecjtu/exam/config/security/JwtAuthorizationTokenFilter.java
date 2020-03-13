package com.ecjtu.exam.config.security;

import com.ecjtu.exam.util.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    @Autowired
    PeopleDetailsService peopleDetailsService;
    @Autowired
    JwtUtils jwtUtils;
    private final String tokenHeader ="Authorization";



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestHeader = request.getHeader(this.tokenHeader);
//        System.out.println("JwtAuthorizationTokenFilter");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Content-Length, Authorization, Accept, X-Requested-With ");
        System.out.println("*********************************过滤器被使用**************************");
        if("OPTIONS".equals(request.getMethod())){
            response.setStatus(HttpStatus.NO_CONTENT.value());
            System.out.println("OPTIONS处理，直接返回");
            return;
        }
        String username = null;
        String authToken = null;
        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            authToken = requestHeader.substring(7);
            try {
                Claims claims = jwtUtils.parseJwt(authToken);
                username = (String)claims.get("account");
            } catch (ExpiredJwtException e) {
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = this.peopleDetailsService.loadUserByUsername(username);

            Claims claims = jwtUtils.parseJwt(authToken);
            String password = (String)claims.get("password");

            System.out.println(password+"  "+ userDetails.getPassword());
            if (password.equals(userDetails.getPassword())) {
//                System.out.println("password.equals(userDetails.getPassword())");
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        }
//        System.out.println("JwtAuthorizationTokenFilter out");
        chain.doFilter(request, response);
    }
}
