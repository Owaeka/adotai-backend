package br.com.brunorodrigo.adotai.service;

import br.com.brunorodrigo.adotai.entity.Pet;
import br.com.brunorodrigo.adotai.repository.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;

    public Page<Pet> getPetsWithPagination(int page, int size) {
        return petRepository.findAll(PageRequest.of(page, size));
    }

}
