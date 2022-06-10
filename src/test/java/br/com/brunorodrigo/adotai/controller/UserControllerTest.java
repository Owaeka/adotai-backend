package br.com.brunorodrigo.adotai.controller;

import br.com.brunorodrigo.adotai.dto.UserDTO;
import br.com.brunorodrigo.adotai.exceptions.UserException;
import br.com.brunorodrigo.adotai.service.UserService;
import br.com.brunorodrigo.adotai.utils.ConstantUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;
    private UserController underTest;
    UserDTO userDTO;
    @BeforeEach
    void setUp() {
        userDTO = new UserDTO("testuser", "Saf3P@asswrd", "Test User", "validemail@email.com");
        underTest = new UserController(userService);
    }

    @Test
    void shouldReturn201CreatingUser() {
        //given
        UUID uuid = UUID.randomUUID();
        //when
        when(userService.saveUser(userDTO)).thenReturn(uuid);
        //then

        ResponseEntity<UUID> response = underTest.createUser(userDTO);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertEquals(response.getBody(), uuid);
    }

    @Test
    void shouldThrowExceptionWhenCreatingAUserThatAlreadyExists() {
        //when
        when(userService.saveUser(userDTO)).thenThrow(new UserException(ConstantUtils.USER_ALREADY_REGISTRED));
        //then
        UserException exception = assertThrows(UserException.class, () -> underTest.createUser(userDTO), "Expected createUser() to throw, but it didn't");

        assertEquals(exception.getMessage(), ConstantUtils.USER_ALREADY_REGISTRED);

    }
}
