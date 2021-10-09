package com.tugas.project1;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class AuthMiddleware implements Filter {

    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        System.out.println("Sending request to: " + req.getRequestURI());
        String[] urls = {"/login", "/register"};
        boolean contains = Arrays.stream(urls)
                .anyMatch(req.getRequestURI()::equals);

        HttpSession session = req.getSession(true);

        Pattern pattern = Pattern.compile("(png|jpg|images|css|js|ico)");
        Matcher matcher = pattern.matcher(req.getRequestURI());
        boolean matchFound = matcher.find();

        if (!matchFound) {
            if (!contains && (session.getAttribute("loggedIn") == null)) {
                System.out.println("User should be logged in!");
                res.sendRedirect("/login");
            } else if (contains && (session.getAttribute("loggedIn") != null)) {
                System.out.println("User has been logged in!");
                res.sendRedirect("/");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }

        System.out.println("Finishing request from: " + req.getRequestURI());
    }
}
