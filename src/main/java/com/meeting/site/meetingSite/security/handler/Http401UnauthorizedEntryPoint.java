
package com.meeting.site.meetingSite.security.handler;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class Http401UnauthorizedEntryPoint implements AuthenticationEntryPoint {

    private static final Logger LOGGER = LoggerFactory.logger(Http401UnauthorizedEntryPoint.class);
    @Autowired
    HeaderHandler headerHandler;
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException arg2) throws IOException,ServletException {
        LOGGER.debug("Pre-authenticated entry point called. Rejecting access:" + request.getRequestURL());
        headerHandler.process(request, response);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");
    }
}
