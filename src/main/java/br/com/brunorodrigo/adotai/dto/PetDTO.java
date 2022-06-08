package br.com.brunorodrigo.adotai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PetDTO {
    private String nome;
    private String idade;
    private String raca;
    private String profilePicture;
    private String user_id;
}
