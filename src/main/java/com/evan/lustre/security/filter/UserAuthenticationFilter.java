package com.evan.lustre.security.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName UserAuthenticationFilter
 * @Description
 * @Author EvanWang
 * @Version 1.0.0
 * @Date 2020/2/10 17:04
 */
public class UserAuthenticationFilter extends AbstractAuthenticationFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //TODO complete and optimize authenticate logic
    }
}
