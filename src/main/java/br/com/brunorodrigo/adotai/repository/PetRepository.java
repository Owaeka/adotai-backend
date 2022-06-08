package br.com.brunorodrigo.adotai.repository;

import br.com.brunorodrigo.adotai.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PetRepository extends JpaRepository<Pet, UUID> {
}
