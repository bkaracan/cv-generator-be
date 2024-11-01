package com.bkaracan.cv_generator_be.service.impl;

import com.bkaracan.cv_generator_be.dto.request.NewUserRecord;
import com.bkaracan.cv_generator_be.service.UserService;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @__(@Autowired))
@Slf4j
public class UserServiceImpl implements UserService {

    @Value("${app.keycloak.realm}")
    private String realm;

    private final Keycloak keycloak;

    @Override
    public void createUser(NewUserRecord newUserRecord) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setEnabled(true);
        userRepresentation.setFirstName(newUserRecord.firstName());
        userRepresentation.setLastName(newUserRecord.lastName());
        userRepresentation.setUsername(newUserRecord.username());
        userRepresentation.setEmail(newUserRecord.username());
        userRepresentation.setEmailVerified(false);

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setValue(newUserRecord.password());
        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);

        userRepresentation.setCredentials(List.of(credentialRepresentation));

        UsersResource usersResource = getUsersResource();

        Response response = usersResource.create(userRepresentation);

        log.info("Status Code: " + response.getStatus());

        if(!Objects.equals(201, response.getStatus())) {
            throw new RuntimeException("Status code: " + response.getStatus());
        }
        log.info("New user has been created");
    }

    private UsersResource getUsersResource() {
        return keycloak.realm(realm).users();
    }
}
