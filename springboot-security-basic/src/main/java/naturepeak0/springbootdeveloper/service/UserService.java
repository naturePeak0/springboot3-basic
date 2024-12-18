package naturepeak0.springbootdeveloper.service;

import lombok.RequiredArgsConstructor;
import naturepeak0.springbootdeveloper.domain.User;
import naturepeak0.springbootdeveloper.dto.AddUserRequest;
import naturepeak0.springbootdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(
                User.builder()
                        .email(dto.getEmail())
                        .password(passwordEncoder.encode(dto.getPassword()))
                        .build())
                .getId();
    }
}
