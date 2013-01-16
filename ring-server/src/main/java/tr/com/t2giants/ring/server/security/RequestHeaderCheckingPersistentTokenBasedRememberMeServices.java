package tr.com.t2giants.ring.server.security;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

import tr.com.t2giants.ring.server.data.*;
import tr.com.t2giants.ring.server.util.*;

/**
 * User: mertcaliskan
 * Date: 6/20/12
 */
public class RequestHeaderCheckingPersistentTokenBasedRememberMeServices extends PersistentTokenBasedRememberMeServices implements WebDesignParameters {

    private final PersistentTokenRepository tokenRepository;

    public RequestHeaderCheckingPersistentTokenBasedRememberMeServices(String key,
                                                                       UserDetailsService userDetailsService,
                                                                       PersistentTokenRepository tokenRepository) {
        super(key, userDetailsService, tokenRepository);
        this.tokenRepository = tokenRepository;
    }

    protected UserDetails processAutoLoginCookie(String[] cookieTokens, HttpServletRequest request, HttpServletResponse response) {

        PersistentRememberMeToken token = tokenProcess(cookieTokens);

        // Token also matches, so login is valid. Update the token value, keeping the *same* series number.
        if (logger.isDebugEnabled()) {
            logger.debug("Refreshing persistent login token for user '" + token.getUsername() + "', series '" +
                    token.getSeries() + "'");
        }

        PersistentRememberMeToken newToken = new PersistentRememberMeToken(token.getUsername(),
                token.getSeries(), generateTokenData(), new Date());

        try {
            tokenRepository.updateToken(newToken.getSeries(), newToken.getTokenValue(), newToken.getDate());
            addCookie(newToken, request, response);
        } catch (DataAccessException e) {
            logger.error("Failed to update token: ", e);
            throw new RememberMeAuthenticationException("Auto login failed due to data access problem");
        }

        UserDetails userDetails = getUserDetailsService().loadUserByUsername(token.getUsername());
        if (userDetails instanceof User) {
            request.setAttribute(CLUBS_USER_ID, ((User) userDetails).getId());
        } else {
            throw new RuntimeException("Panic! userDetails is not the user. System halt.....!");
        }
        return userDetails;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String rememberMeCookie = extractRememberMeCookie(request);

        if (rememberMeCookie != null) {
            String[] cookieTokens = decodeCookie(rememberMeCookie);

            PersistentRememberMeToken token = tokenProcess(cookieTokens);

            //token is valid, delete all tokens of user
            // TODO maybe we should do this per series basis
            tokenRepository.removeUserTokens(token.getUsername());
        }
        else {
            // TODO crap..we should handle this somehow..
        }
    }

    private PersistentRememberMeToken tokenProcess(String[] cookieTokens) {
        if (cookieTokens.length != 2) {
            throw new InvalidCookieException("Cookie token did not contain " + 2 +
                    " tokens, but contained '" + Arrays.asList(cookieTokens) + "'");
        }

        final String presentedSeries = cookieTokens[0];
        final String presentedToken = cookieTokens[1];

        PersistentRememberMeToken token = tokenRepository.getTokenForSeries(presentedSeries);

        if (token == null) {
            // No series match, so we can't authenticate using this cookie
            throw new RememberMeAuthenticationException("No persistent token found for series id: " + presentedSeries);
        }

        // We have a match for this user/series combination
        if (!presentedToken.equals(token.getTokenValue())) {
            // Token doesn't match series value. Delete all login information for this user and throw an exception to warn them.
            tokenRepository.removeUserTokens(token.getUsername());

            throw new CookieTheftException(messages.getMessage("PersistentTokenBasedRememberMeServices.cookieStolen",
                    "Invalid remember-me token (Series/token) mismatch. Implies previous cookie theft attack."));
        }

        if (token.getDate().getTime() + getTokenValiditySeconds() * 1000L < System.currentTimeMillis()) {
            throw new RememberMeAuthenticationException("Remember-me login has expired");
        }

        return token;
    }

    protected boolean rememberMeRequested(HttpServletRequest request, String parameter) {
        String value = request.getHeader(DEFAULT_PARAMETER);
        return value != null && Boolean.parseBoolean(value) ? Boolean.parseBoolean(value) : super.rememberMeRequested(request, parameter);
    }

    protected String extractRememberMeCookie(HttpServletRequest request) {
        return request.getHeader(SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
    }

    protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
        String cookieValue = encodeCookie(tokens);
        response.setHeader(SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY, cookieValue);
    }

    private void addCookie(PersistentRememberMeToken token, HttpServletRequest request, HttpServletResponse response) {
        setCookie(new String[]{token.getSeries(), token.getTokenValue()}, getTokenValiditySeconds(), request, response);
    }
}