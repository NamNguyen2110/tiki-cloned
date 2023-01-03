package com.example.tikicloned.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class AuthenticateExceptionHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException {
        httpServletResponse.setContentType("text/json");
        PrintWriter out = httpServletResponse.getWriter();
        httpServletResponse.setHeader("Cache-Control", "no-cache");
        out.println("{\n" +
                "    \"code\": \"4\",\n" +
                "    \"message\": \"You don't have permission to access this resource, please check your access token " +
                "or contact to admin\",\n" +
                "    \"data\": null\n" +
                "}");
        log.error("You don't have permission to access this resource");
    }

}
