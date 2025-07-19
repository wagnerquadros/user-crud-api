package com.wagnerquadros.user_crud.business;


import com.wagnerquadros.user_crud.infrastructure.dto.UserDto;
import com.wagnerquadros.user_crud.infrastructure.entity.User;
import com.wagnerquadros.user_crud.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User saveUser (UserDto userDto){
        User newUser = User.builder()
                .email(userDto.email())
                .name(userDto.name())
                .build();

        return repository.saveAndFlush(newUser);
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

    public User updateUser (Long id, UserDto userDto){
        User userEntity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
        userEntity.setEmail(userDto.email() != null ?
                userDto.email() : userEntity.getEmail());
        userEntity.setName(userDto.name() != null ?
                userDto.name() : userEntity.getName());

        return repository.saveAndFlush(userEntity);
    }
}
