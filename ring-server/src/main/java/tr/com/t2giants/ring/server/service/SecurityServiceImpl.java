package tr.com.t2giants.ring.server.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;
import tr.com.t2giants.ring.core.domain.User;
import tr.com.t2giants.ring.server.dao.UserDao;


/**
 * User: mertcaliskan
 * Date: 6/14/12
 */
@Service(value = "securityService")
public class SecurityServiceImpl implements SecurityService {


    @Qualifier("tokenRepository")
    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Autowired
    private UserDao userDao;

    private final Log logger = LogFactory.getLog(SecurityServiceImpl.class);

    private void logDebugMessage(String logMessage) {
        if (logger.isDebugEnabled()) {
            logger.debug(logMessage);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUser(username);
        if (user == null) {
            logDebugMessage("Supporter cannot be found");
            throw new UsernameNotFoundException("Username not found");
        }
        return user;
    }

    public void logout(UserDetails userDetails) {
        tokenRepository.removeUserTokens(userDetails.getUsername());
    }
}