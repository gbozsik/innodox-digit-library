package com.innodox.library.core;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

            /**MEGGÁTOLJA, HOGY FELUGORJON A BASICAUTH BELÉPPÓABLAK A BÖNGÉSZÓBEN
            A bejövő autentikációs kérés header-ben elkapja a "X-Requested-By"-t
            és ebben az esetben a kimenő headerben a "Basic realm=Cascade Realm" value-t,
            aminek hatására alőugrik a böngésző basic aut bejelentkező popup-ja, megváltoztatja "Application driven"-re**/


public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String requestedBy = request.getHeader("X-Requested-By");
        if(requestedBy == null || requestedBy.isEmpty()) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.addHeader("WWW-Authenticate", "Basic realm=Cascade Realm");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        } else {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.addHeader("WWW-Authenticate", "Application driven");
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }
}
