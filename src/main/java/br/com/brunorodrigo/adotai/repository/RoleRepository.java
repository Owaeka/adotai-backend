package br.com.brunorodrigo.adotai.repository;

import br.com.brunorodrigo.adotai.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
