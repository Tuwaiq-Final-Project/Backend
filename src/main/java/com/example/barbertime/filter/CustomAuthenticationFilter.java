package com.example.barbertime.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.barbertime.Repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, UserRepository userRepository ){
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        // Get the user from the request body
        ObjectMapper mapper = new ObjectMapper();
        com.example.barbertime.Entities.User user = null;
        try {
            user = mapper.readValue(request.getInputStream(), com.example.barbertime.Entities.User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // User information that will be used to authenticate
        String email = user.getEmail();
        String password = user.getPassword();

        try{
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,password);
            return authenticationManager.authenticate(authenticationToken);
        }
        catch (Exception e)
        {
            System.out.println("Something error at attemptAuthentication function");
            System.out.println(e);
            throw new BadCredentialsException("Password incorrect",e);
        }

    }

    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException
    {
        User user = (User)authentication.getPrincipal();
        com.example.barbertime.Entities.User dbUser = userRepository.findByEmail(user.getUsername());

        Map<String,String> userDetails = new HashMap<>();
        userDetails.put("id",dbUser.getId().toString());
        userDetails.put("name",dbUser.getName().toString());

        Map<String, Map<String,String>> returnUser = new HashMap<>();
        returnUser.put("userInfo",userDetails);

        Algorithm algorithm = Algorithm.HMAC256("jwt_super_secret".getBytes());
        String access_token = JWT.create()
                .withPayload(returnUser)
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 480 * 60 * 1000))
                .withIssuer(request.getRequestURI().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);
         Map<String, String> token = new HashMap<>();
        token.put("access_token", access_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), token);
    }

}
