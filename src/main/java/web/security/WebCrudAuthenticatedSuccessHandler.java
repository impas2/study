package web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class WebCrudAuthenticatedSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException {

        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

        String targetUrl = "/user/";

        for (final GrantedAuthority grantedAuthority : authentication.getAuthorities()) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ADMIN")) {
                targetUrl = "/admin/users";
            }
        }

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, targetUrl);
    }
}
