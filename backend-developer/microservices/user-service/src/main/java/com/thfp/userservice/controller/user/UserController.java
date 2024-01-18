package com.thfp.userservice.controller.user;

import com.thfp.userservice.controller.user.dto.ResponseUserDTO;
import com.thfp.userservice.controller.user.dto.ResponseUserListDTO;
import com.thfp.userservice.controller.user.dto.SaveUserDTO;
import com.thfp.userservice.entity.user.User;
import com.thfp.userservice.entity.user.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> save(
            @RequestBody SaveUserDTO userBody
    ) {
        User user = new User(
                userBody.name(),
                userBody.email(),
                userBody.password()
        );

        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) throws Exception {
        User user = this.userService.findById(id);

        ResponseUserDTO response = new ResponseUserDTO(
                user.getName(),
                user.getEmail(),
                user.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<?> getAll(
            @RequestParam(defaultValue = "10") String pageSize,
            @RequestParam(defaultValue = "0") String pageNo,
            @RequestParam(defaultValue = "ASC") String order
    ) {
        // Page é uma classe que auxilia a criação de paginação de resultados
        // o que é paginação: imagina que vc tem uma lista de 1000 iitens

        int size = Integer.parseInt(pageSize);
        int page = Integer.parseInt(pageNo);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.fromString(order));

        Page<User> users = this.userService.findAll(pageable);
        List<ResponseUserDTO> dtos = users.getContent().stream().map(
                it -> new ResponseUserDTO(
                        it.getName(),
                        it.getEmail(),
                        it.getCreatedAt()
                )
        ).toList();

        ResponseUserListDTO response = new ResponseUserListDTO(
                dtos,
                users.getSize(),
                users.getNumber(),
                users.hasNext()
        );

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable String id,
            @RequestBody SaveUserDTO userDTO
    ) throws Exception {
        User user = new User(
                userDTO.name(),
                userDTO.email(),
                null
        );

        User updated = this.userService.update(id, user);

        ResponseUserDTO response = new ResponseUserDTO(
                updated.getName(),
                updated.getEmail(),
                updated.getCreatedAt()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) throws Exception {
        this.userService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
