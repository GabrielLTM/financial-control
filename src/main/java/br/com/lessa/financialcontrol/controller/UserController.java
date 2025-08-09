package br.com.lessa.financialcontrol.controller;

import br.com.lessa.financialcontrol.dto.UserRequest;
import br.com.lessa.financialcontrol.dto.UserResponse;
import br.com.lessa.financialcontrol.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest user){
        UserRequest userResponse = userService.createUser(user);

        var response = UserResponse.builder()
                .id(userResponse.id())
                .login(userResponse.login())
                .role(userResponse.role())
                .build();

        return ResponseEntity.ok(response);
    }

//    @GetMapping
//    public ResponseEntity<Page<UserDTO>> getAllUsers(
//            @RequestParam(value = "page", defaultValue = "0") Integer page,
//            @RequestParam(value = "size", defaultValue = "12") Integer size,
//            @RequestParam(value = "direction", defaultValue = "asc") String direction
//    ){
//        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "fullname"));
//        return ResponseEntity.ok(userService.getAllUsers(pageable));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable String id){
        var userRequest = userService.getUser(id);

        var response = UserResponse.builder()
                .id(userRequest.id())
                .login(userRequest.login())
                .role(userRequest.role())
                .build();
        return ResponseEntity.ok(response);
    }

}
