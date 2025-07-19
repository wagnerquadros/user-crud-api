package com.wagnerquadros.user_crud.controller;

import com.wagnerquadros.user_crud.dto.UserResponseDto;
import com.wagnerquadros.user_crud.service.UserService;
import com.wagnerquadros.user_crud.dto.UserRequestDto;
import com.wagnerquadros.user_crud.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.saveUser(userRequestDto));
    }

    @GetMapping
    public ResponseEntity<User> getUserByEmail (@RequestParam String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUserByEmail(@RequestParam String email){
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<UserResponseDto> updateUserById(@RequestParam Long id, @RequestBody UserRequestDto userRequestDto){
        return ResponseEntity.ok(userService.updateUser(id, userRequestDto));
    }
}
