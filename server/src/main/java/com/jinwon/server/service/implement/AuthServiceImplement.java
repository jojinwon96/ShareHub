package com.jinwon.server.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jinwon.server.dto.request.auth.SignInRequestDto;
import com.jinwon.server.dto.request.auth.SignUpRequestDto;
import com.jinwon.server.dto.response.ResponseDto;
import com.jinwon.server.dto.response.auth.SignInResponseDto;
import com.jinwon.server.dto.response.auth.SignUpResponseDto;
import com.jinwon.server.entity.UserEntity;
import com.jinwon.server.provider.JwtProvider;
import com.jinwon.server.repository.UserRepository;
import com.jinwon.server.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    // @Autowired
    // private UserRepository userRepository;

    // @Autowired
    // public AuthServiceImplement(UserRepository userRepository){
    // this.userRepository = userRepository;
    // }

    // @Autowired
    // public void setUserRepository(UserRepository userRepository){
    // this.userRepository = userRepository;
    // }

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        try {
            String email = dto.getEmail();
            boolean existedEmail = userRepository.existsByEmail(email);
            if (existedEmail)
                return SignUpResponseDto.duplicateEmail();

            String nickname = dto.getNickname();
            boolean existedNickname = userRepository.existsByNickname(nickname);
            if (existedNickname)
                return SignUpResponseDto.duplicateNickname();

            String password = dto.getPassword();
            String encodePassword = passwordEncoder.encode(password);

            UserEntity userEntity = UserEntity.builder()
                    .email(email)
                    .nickname(nickname)
                    .password(encodePassword)
                    .build();

            userRepository.save(userEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignUpResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {

        String token = null;

        try {
            String email = dto.getEmail();
            UserEntity userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return SignInResponseDto.signInFailed();

            String password = dto.getPassword();
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return SignInResponseDto.signInFailed();

            token = jwtProvider.create(email);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }

}
