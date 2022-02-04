package we_won.hackerton.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import we_won.hackerton.Interface.UserRepository;
import we_won.hackerton.Interface.UserService;
import we_won.hackerton.entity.User_;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User_> account = this.userRepository.findByUsername(username);

        /**
         * Username 값이 DATA DB 에 존재하지 않는 경우
         * UsernameNotFoundException 에러 메소드를 사용합니다.
         * */
        if (account.isPresent()) {

            System.out.println("여기서 찍히냐!!!!!!!!!!!!");
            System.out.println(User.builder()
                    .username(account.get().getUsername())
                    .password(account.get().getPassword())
                    .roles(account.get().getRole().getKey())
                    .build());

            return User.builder()
                    .username(account.get().getUsername())
                    .password(account.get().getPassword())
                    .roles(account.get().getRole().getKey())
                    .build();
        } else {
            throw new UsernameNotFoundException(username + "정보를 찾을 수 없습니다.");
        }
    }


    @Override
    public User_ saveOrUpdateAccount(User_ user) {

        user.encodePassword(this.passwordEncoder);


        return this.userRepository.save(user);
    }
}
