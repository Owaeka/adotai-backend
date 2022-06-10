package br.com.brunorodrigo.adotai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import static br.com.brunorodrigo.adotai.utils.ConstantUtils.VALIDATION_USER_EMAIL_IS_INVALID;
import static br.com.brunorodrigo.adotai.utils.ConstantUtils.VALIDATION_USER_NAME_IS_REQUIRED;
import static br.com.brunorodrigo.adotai.utils.ConstantUtils.VALIDATION_USER_PASSWORD_INCORRECT;
import static br.com.brunorodrigo.adotai.utils.ConstantUtils.VALIDATION_USER_USERNAME_IS_REQUIRED;

@Data
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = VALIDATION_USER_USERNAME_IS_REQUIRED)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = VALIDATION_USER_PASSWORD_INCORRECT)
    private String password;

    @NotEmpty(message = VALIDATION_USER_NAME_IS_REQUIRED)
    private String name;

    @NotEmpty(message = VALIDATION_USER_EMAIL_IS_INVALID)
    @Email(message = VALIDATION_USER_EMAIL_IS_INVALID)
    private String email;
}
