package com.example.Warehouse.Management.System.controller;

import com.example.Warehouse.Management.System.model.User;
import com.example.Warehouse.Management.System.payload.dto.UserDto;
import com.example.Warehouse.Management.System.payload.request.SignUpRequest;
import com.example.Warehouse.Management.System.service.ServiceImpl.UserService;

//import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<Page<User>> getUsers(Pageable pageable) {
        return ResponseEntity.ok().body(userService.listAllUsers(pageable));
    }

    @RolesAllowed("ROLE_ADMIN")
    @PostMapping("/save")
    public ResponseEntity<User> saveNewUser(@RequestBody SignUpRequest user) {
        return ResponseEntity.ok().body(userService.saveNewUser(user));
    }

    @RolesAllowed("ROLE_ADMIN")
    @GetMapping("/find/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    @RolesAllowed("ROLE_ADMIN")
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDto user, @PathVariable Long id) {
        User updateUser = userService.updateUserById(user, id);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }

    @RolesAllowed("ROLE_ADMIN")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @PostMapping("/role/save")
//    public ResponseEntity<UserRole> saveRole(@RequestBody UserRole userRole) {
//        return ResponseEntity.ok().body(userService.saveRole(userRole));
//    }

}
