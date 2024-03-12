//package com.huxton.microservice.sample.apigateway.security.filter;
//
//import com.huxton.microservice.sample.apigateway.security.service.JwtService;
//import com.huxton.microservice.sample.apigateway.security.service.UserService;
//import com.huxton.microservice.sample.apigateway.security.validation.JwtValidation;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.NonNull;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//import java.util.Arrays;
//import java.util.Objects;
//
//@Component
//@RequiredArgsConstructor
//public class JwtAuthenticationFilter extends OncePerRequestFilter {
//
//    private final JwtValidation jwtValidation;
//    private final JwtService jwtService;
//    private final UserDetailsService userDetailsService;
//    private final UserService userService;
//
//    private final String[] notFilterPaths = new String[]{
//            "/api/v1/authentications"
//    };
//
//    @Override
//    protected boolean shouldNotFilter(@NonNull HttpServletRequest request) throws ServletException {
//
//        return Arrays.stream(notFilterPaths).anyMatch(path -> request.getServletPath().startsWith(path));
//    }
//
//    @Override
//    protected void doFilterInternal(
//            @NonNull HttpServletRequest request,
//            @NonNull HttpServletResponse response,
//            @NonNull FilterChain filterChain) throws ServletException, IOException {
//
//        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        jwtValidation.validateAuthHeader(authHeader);
//
//        final String jwtToken = authHeader.substring(7);
//        final String email = jwtService.extractUsername(jwtToken);
//
//        if (Objects.nonNull(email) &&
//                Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
//
//            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
//
//            jwtValidation.validateJwtToken(jwtToken, userDetails);
//
//            UsernamePasswordAuthenticationToken authenticationToken =
//                    new UsernamePasswordAuthenticationToken(
//                            userDetails,
//                            null,
//                            userDetails.getAuthorities()
//                    );
//
//            authenticationToken.setDetails(
//                    new WebAuthenticationDetailsSource().buildDetails(request)
//            );
//
//            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//            filterChain.doFilter(request, response);
//        }
//    }
//}
