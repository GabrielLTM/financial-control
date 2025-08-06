package br.com.lessa.financialcontrol.service;

import br.com.lessa.financialcontrol.dto.UserDTO;
import br.com.lessa.financialcontrol.entity.User;
import br.com.lessa.financialcontrol.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserDTO createUser(UserDTO userDTO){

        User userEntity = User.builder().fullname(userDTO.fullname())
                .username(userDTO.username())
                .roles(userDTO.roles())
                .build();

        User savedUser = userRepository.save(userEntity);

        return mapToDto(savedUser);
    }

    public Page<UserDTO> getAllUsers(Pageable pageable){
        Page<User> usersPage = userRepository.findAll(pageable);

        return usersPage.map(this::mapToDto);
    }

    public UserDTO getUser(Long id){
        return mapToDto(userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }

    private UserDTO mapToDto(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullname(user.getFullname())
                .roles(user.getRoles())
                .build();
    }
}
