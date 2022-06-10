package br.com.brunorodrigo.adotai.service;

import br.com.brunorodrigo.adotai.dto.UserDTO;
import br.com.brunorodrigo.adotai.entity.Role;
import br.com.brunorodrigo.adotai.entity.User;
import br.com.brunorodrigo.adotai.exceptions.UserException;
import br.com.brunorodrigo.adotai.repository.RoleRepository;
import br.com.brunorodrigo.adotai.repository.UserRepository;
import br.com.brunorodrigo.adotai.utils.ConstantUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static br.com.brunorodrigo.adotai.security.SecurityConstants.USER_ROLE;


@Service
@AllArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UUID saveUser(UserDTO userDto) {
        User userByUsername = userRepository.findByUsername(userDto.getUsername());
        User userByEmail = userRepository.findByEmail(userDto.getEmail());

        if(userByUsername != null || userByEmail != null) {
            throw new UserException(ConstantUtils.USER_ALREADY_REGISTRED);
        }

        userDto.setPassword(passwordEncoder
                .encode(userDto.getPassword()));

        User newUser = new User();
        BeanUtils.copyProperties(userDto, newUser);

        Role role = this.getRoleByName(USER_ROLE);
        newUser.getRoles().add(role);

        return userRepository.save(newUser).getId();
    }

    public void saveRole(String nome) {
        roleRepository.save(new Role(null, nome));
    }

    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);

        user.getRoles().add(role);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Role getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}
