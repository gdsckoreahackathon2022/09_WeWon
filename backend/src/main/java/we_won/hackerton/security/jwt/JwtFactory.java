package we_won.hackerton.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.entity.User_;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;


@Component
public class JwtFactory {

    private static final Logger log = LoggerFactory.getLogger(JwtFactory.class);

    private final UserRepository userRepository;

    public JwtFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1.
    public String generateToken(UserDetails userDetails) {
        System.out.println("generateToken~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String token = null;
        User_ user = userRepository.getByUsername(userDetails.getUsername());

        try {
            Set<String> roles = userDetails.getAuthorities().stream()
                    .map(r -> r.getAuthority()).collect(Collectors.toSet());
            String role = roles.iterator().next();

            token = JWT.create()
                    .withIssuer("hackerton")
                    .withClaim("USERNAME", userDetails.getUsername())
                    .withClaim("USERID",user.getId())
                    .withClaim("USER_ROLE", role)
                    .withClaim("EXP", new Date(System.currentTimeMillis() + 864000000))
                    .sign(generateAlgorithm());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return token;
    }

    // 2.
    private Algorithm generateAlgorithm() throws UnsupportedEncodingException {
        String signingKey = "jwttest";
        return Algorithm.HMAC256(signingKey);
    }
}
