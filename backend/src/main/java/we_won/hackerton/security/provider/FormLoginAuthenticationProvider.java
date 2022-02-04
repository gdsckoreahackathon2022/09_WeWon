package we_won.hackerton.security.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import we_won.hackerton.security.token.PostAuthorizationToken;
import we_won.hackerton.security.token.PreAuthorizationToken;
import we_won.hackerton.service.UserServiceImpl;

import java.util.NoSuchElementException;


@Component
@RequiredArgsConstructor
public class FormLoginAuthenticationProvider implements AuthenticationProvider {
    private final UserServiceImpl userService;

    // 4.
    private final PasswordEncoder passwordEncoder;

    // 1.
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        // 2.
        PreAuthorizationToken token = (PreAuthorizationToken) authentication;

        String username = token.getUsername();
        String password = token.getUserPassword();
        System.out.println("username ================== " + username);
        // 3.
        UserDetails accountDB = userService.loadUserByUsername(username);

        // 4.
        System.out.println("accountDB ================== " + accountDB);
        if (isCorrectPassword(password, accountDB.getPassword())) {
            return PostAuthorizationToken
                    .getTokenFormUserDetails(accountDB);
        }

        // 이곳까지 통과하지 못하면 잘못된 요청으로 접근하지 못한것 그러므로 throw 해줘야 한다.
        throw new NoSuchElementException("인증 정보가 정확하지 않습니다.");
    }

    // 5.
    // Provider 를 연결 해주는 메소드 PreAuthorizationToken 사용한 filter 를 검색 후 연결
    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthorizationToken.class.isAssignableFrom(authentication);
    }

    // 4.
    private boolean isCorrectPassword(String password, String accountPassword) {
        return passwordEncoder.matches(password, accountPassword);
    }
}
