package com.bkaracan.cv_generator_be.dto.request;

public record NewUserRecord(String username, String password,
                            String firstName, String lastName) {
}
