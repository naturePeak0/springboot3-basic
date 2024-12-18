package naturepeak0.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import naturepeak0.springbootdeveloper.domain.User;
import naturepeak0.springbootdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException(email));

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .accountExpired(false) // 계정 만료 여부
                .accountLocked(false) // 계정 잠김 여부
                .credentialsExpired(false) // 비밀번호 만료 여부
                .disabled(false) // 계정 활성화 여부
                .build();
    }
}
