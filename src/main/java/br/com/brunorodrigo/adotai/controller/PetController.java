package br.com.brunorodrigo.adotai.controller;

import br.com.brunorodrigo.adotai.entity.Pet;
import br.com.brunorodrigo.adotai.service.PetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static br.com.brunorodrigo.adotai.security.SecurityConstants.PETS_URL;

@RestController
@RequestMapping(PETS_URL)
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping(params = {"page", "size"})
    public ResponseEntity<Page<Pet>> listAllPetsPaginated(@RequestParam(value = "page") Integer page,
                                                          @RequestParam(value = "size", defaultValue = "25", required = false) Integer size) {

        return new ResponseEntity<>(petService.getPetsWithPagination(page, size), HttpStatus.OK);
    }

}
