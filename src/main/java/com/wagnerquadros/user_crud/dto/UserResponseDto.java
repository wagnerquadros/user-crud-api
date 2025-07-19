package com.wagnerquadros.user_crud.dto;

import com.wagnerquadros.user_crud.entity.User;

public record UserResponseDto(Long id, String email, String nome) {
    public UserResponseDto(User user) {
        this(user.getId(), user.getEmail(), user.getName());
    }
}
