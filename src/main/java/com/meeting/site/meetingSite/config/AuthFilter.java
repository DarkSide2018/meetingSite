package com.meeting.site.meetingSite.config;

import lombok.SneakyThrows;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
public class AuthFilter extends GenericFilterBean {
    private Logger logger = LoggerFactory.logger(this.getClass());
    @Value("${token}")
    private String token;
    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorization = ((HttpServletRequest) request).getHeader("Authorization");
        logger.info("authorization -> " + authorization);
        if(token.equals(authorization)){
            chain.doFilter(request, response);
        }else{
            throw new Exception("wrong token");
        }

    }
}
