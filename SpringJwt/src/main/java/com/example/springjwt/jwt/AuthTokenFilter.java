package com.example.springjwt.jwt;

import com.example.springjwt.Service.StudentServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    private String parseToken(HttpServletRequest request){
//        String header = request.getHeader("Authorization");
        Cookie[] cookies = request.getCookies();

//        if(header != null && header.startsWith("Bearer ")){
//            return header.substring(7, header.length());
//        }
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("jwt")){
                    return cookie.getValue();
                }
            }
        }

        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


       try{
           String token = parseToken(request);
           if(token != null && jwtUtil.verifyToken(token)){
               String username  = jwtUtil.getUserNameFromToken(token);
               UserDetails userDetails = studentServiceImpl.loadUserByUsername(username);

               UsernamePasswordAuthenticationToken
                       authenticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
               SecurityContextHolder.getContext().setAuthentication(authenticationToken);
           }
       }catch (Exception e) {
           e.printStackTrace();
       }
        filterChain.doFilter(request, response);

    }
}
