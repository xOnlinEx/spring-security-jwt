package com.xonlinex.springsecurityjwt.controller;

import com.xonlinex.springsecurityjwt.controller.request.CreateUserDTO;
import com.xonlinex.springsecurityjwt.model.ERole;
import com.xonlinex.springsecurityjwt.model.RoleEntity;
import com.xonlinex.springsecurityjwt.model.UserEntity;
import com.xonlinex.springsecurityjwt.repository.UserRepository;
import jakarta.persistence.EnumType;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/hello")
    public String hello(){
        return "hello world not secured";
    }
    @GetMapping("/helloSecured")
    public String helloSecured(){
        return "hello world ni secured";
    }

    @PostMapping("/createUser")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){

        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .email(createUserDTO.getEmail())
                .password(createUserDTO.getPassword())
                .roles(createUserDTO.getRoles().stream()
                        .map(role -> RoleEntity.builder()
                                .name(ERole.valueOf(role))
                                .build()
                        ).collect(Collectors.toSet()))
                .build();

        userRepository.save(userEntity);
        return ResponseEntity.ok(userEntity);
    }
    @DeleteMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId){
        userRepository.deleteById(userId);
        return "user with id : " + userId + "deleted";
    }

}
