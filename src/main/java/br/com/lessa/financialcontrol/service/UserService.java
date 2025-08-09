package br.com.lessa.financialcontrol.service;

import br.com.lessa.financialcontrol.dto.UserRequest;
import br.com.lessa.financialcontrol.entity.User;
import br.com.lessa.financialcontrol.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public UserRequest createUser(UserRequest userDTO) {

        User userEntity = User.builder()
                .login(userDTO.login())
                .password(userDTO.password())
                .role(userDTO.role())
                .build();

        var userSaved = userRepository.save(userEntity);

        return mapToResponse(userSaved);
    }

    public UserRequest getUser(String id){
        var user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        return mapToResponse(user);
    }

    private UserRequest mapToResponse(User user){
        return UserRequest.builder()
                .id(user.getId())
                .login(user.getLogin())
                .role(user.getRole())
                .build();
    }
}

