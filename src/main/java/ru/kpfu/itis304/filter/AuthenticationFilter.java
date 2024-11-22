package ru.kpfu.itis304.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;
/*
@WebFilter(urlPatterns = "/*", filterName = "authFilter")
public class AuthenticationFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String uri = httpServletRequest.getRequestURI();
        if (isPublicPage(uri)) {
            chain.doFilter(request, response);
            return;
        }
        HttpSession session = httpServletRequest.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            httpServletResponse.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicPage(String uri) {
        return uri.contains("/login") || uri.contains("/registration");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

 */