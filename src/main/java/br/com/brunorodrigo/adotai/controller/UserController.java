package br.com.brunorodrigo.adotai.controller;

import br.com.brunorodrigo.adotai.dto.UserDTO;
import br.com.brunorodrigo.adotai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

import static br.com.brunorodrigo.adotai.security.SecurityConstants.USERS_URL;

@AllArgsConstructor
@RestController
@RequestMapping(USERS_URL)
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UUID> createUser(@Valid @RequestBody UserDTO userDTO) {

        UUID idNewUser = userService.saveUser(userDTO);

        return new ResponseEntity<>(idNewUser, HttpStatus.CREATED);
    }
}
