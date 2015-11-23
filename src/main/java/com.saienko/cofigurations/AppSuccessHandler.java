package com.saienko.cofigurations;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by gleb on 21.11.2015.
 */
@Component
public class AppSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {


    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();


    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }


    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }


    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            logger.info("redirect failed in AppHandler");
            return;
        }


        redirectStrategy.sendRedirect(request, response, targetUrl);

    }

    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority auth : authorities) {
            roles.add(auth.getAuthority());
        }

        if (isAdmin(roles)) {
            url = "/dba/";
        } else if (isDba(roles)) {
            url = "/admin/";
        } else if (isUser(roles)) {
            url = "/user/";
        } else {
            url = "/accessDenied";
        }
        return url;
    }

    private boolean isUser(List<String> roles) {
        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }

    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }

    private boolean isDba(List<String> roles) {
        if (roles.contains("ROLE_DBA")) {
            return true;
        }
        return false;
    }

}
