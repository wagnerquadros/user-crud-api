package com.wagnerquadros.user_crud.service;


import com.wagnerquadros.user_crud.dto.UserRequestDto;
import com.wagnerquadros.user_crud.dto.UserResponseDto;
import com.wagnerquadros.user_crud.entity.User;
import com.wagnerquadros.user_crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserResponseDto saveUser (UserRequestDto userRequestDto){
        User newUser = User.builder()
                .email(userRequestDto.email())
                .name(userRequestDto.name())
                .build();

        return new UserResponseDto(repository.saveAndFlush(newUser));
    }

    public User getUserByEmail(String email){
        return repository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email not found.")
        );
    }

    @Transactional
    public void deleteUserByEmail(String email){
        repository.deleteByEmail(email);
    }

    public UserResponseDto updateUser (Long id, UserRequestDto userRequestDto){
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));

        userEntity.setEmail(userRequestDto.email() != null ?
                userRequestDto.email() : userEntity.getEmail());
        userEntity.setName(userRequestDto.name() != null ?
                userRequestDto.name() : userEntity.getName());

        return new UserResponseDto(repository.saveAndFlush(userEntity));
    }
}
