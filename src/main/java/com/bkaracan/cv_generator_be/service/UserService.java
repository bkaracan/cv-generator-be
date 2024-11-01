package com.bkaracan.cv_generator_be.service;

import com.bkaracan.cv_generator_be.dto.request.NewUserRecord;

public interface UserService {

    void createUser(NewUserRecord newUserRecord);
}
