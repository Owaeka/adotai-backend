package br.com.brunorodrigo.adotai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
public class UserDTO {

    @NotEmpty(message = "Usuário é obrigatório.")
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Senha inválida. A Senha deve conter pelo menos 8 caracteres, " +
            "1 letra maiscula, 1 letra minuscula e 1 caracter especial")
    private String password;

    @NotEmpty(message = "Nome do usuário é obrigatório.")
    private String name;

    @NotEmpty(message = "O endereço de e-mail é inválido")
    @Email(message = "O endereço de e-mail é inválido")
    private String email;
}
