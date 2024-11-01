package com.bkaracan.cv_generator_be.controller;

import com.bkaracan.cv_generator_be.dto.request.NewUserRecord;
import com.bkaracan.cv_generator_be.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody NewUserRecord newUserRecord) {
        userService.createUser(newUserRecord);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
