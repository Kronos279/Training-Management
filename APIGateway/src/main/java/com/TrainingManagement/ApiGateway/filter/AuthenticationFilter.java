package com.TrainingManagement.ApiGateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.TrainingManagement.ApiGateway.util.JwtUtil;

import io.jsonwebtoken.Claims;
@CrossOrigin
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	 private static final Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Autowired
    private RouteValidator validator;


    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }
                try {
                	jwtUtil.validateToken(authHeader);
                	Claims claims = jwtUtil.extractClaims(authHeader); // Extract claims from JWT token
                    String role = claims.get("role", String.class);
                	 logger.info("Role extracted from header: {}", role);
                	 if (role != null) {
                		 if ("ROLE_ADMIN".equals(role)) {
                             // If role is admin, route to all microservices
                             exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR,
                                     exchange.getRequest().getURI().resolve("/"));
                           // Route to root path
                             return chain.filter(exchange);
                         } else if ("ROLE_USER".equals(role)) {
                        	 String path = exchange.getRequest().getURI().getPath();
                        	 logger.info("String Path: {}", path);
                             if (path.startsWith("/employeedetails") || path.startsWith("/courses")) {
                                 return chain.filter(exchange);
                             }
		                         else {
		                            	 throw new RuntimeException();
		                             }
                         }
                	 }else {
                	 		throw new RuntimeException();
                	 }
                	
                 	
                }catch (Exception e) {
                    logger.error("Un Authorised Access....!!!");
                    throw new RuntimeException("Un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}