package uk.ac.sheffield.foodpassport;

import java.io.IOException;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException {
        String errorMessage = exception.getMessage();
        System.out.println(errorMessage);
        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username or Password";
        }

        response.sendRedirect("/login?error=" + errorMessage);
    }
}
