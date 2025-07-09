package com.leonardosanner.programming_courses.service.security.filters;

import com.leonardosanner.programming_courses.dto.owner.OwnerJWTCredentialsDTO;
import com.leonardosanner.programming_courses.service.useCases.auth.AuthRoleOwnerUseCase;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseJWTFilter extends OncePerRequestFilter{

    @Autowired
    private AuthRoleOwnerUseCase authRoleOwnerUseCase;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().startsWith("/course")) {
            String headerToken = request.getHeader("Authorization");

            this.courseJWTAuthentication(headerToken);
        }

        filterChain.doFilter(request, response);
    }

    private void courseJWTAuthentication(String headerToken) {
        if (headerToken == null) {
            return;
        }

        headerToken = headerToken.replace("Bearer ", "");
        OwnerJWTCredentialsDTO ownerJWTCredentialsDTO =  this.authRoleOwnerUseCase.execute(headerToken);

        List<SimpleGrantedAuthority> authorizations = new ArrayList<>();
        authorizations.add(new SimpleGrantedAuthority("ROLE_" + ownerJWTCredentialsDTO.getRoles().toUpperCase())); // stream map list later

        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                ownerJWTCredentialsDTO.getEmail(),
                null,
                authorizations
        );

        SecurityContextHolder.getContext().setAuthentication(auth);
    }
}
