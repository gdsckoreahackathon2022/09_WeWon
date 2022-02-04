package we_won.hackerton.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import we_won.hackerton.dto.UserDTO;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class PostAuthorizationToken extends UsernamePasswordAuthenticationToken {

    // 1.
    private PostAuthorizationToken(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities
    ) {
        super(principal, credentials, authorities);
    }

    public static PostAuthorizationToken getTokenFormUserDetails(UserDetails userDetails) {

        return new PostAuthorizationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );
    }

    public static PostAuthorizationToken getTokenFormUserDetails(UserDTO accountDTO) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(
                new SimpleGrantedAuthority(accountDTO.getRole())
        );

        return new PostAuthorizationToken(
                accountDTO,
                "null password",
                grantedAuthorities
        );
    }
    public UserDetails getUserDetails() {

        return (UserDetails) super.getPrincipal();
    }
}