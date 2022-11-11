package com.example.Warehouse.Management.System.service.ServiceImpl;

import com.example.Warehouse.Management.System.exceptions.ResourceNotFoundException;
import com.example.Warehouse.Management.System.model.User;
import com.example.Warehouse.Management.System.payload.dto.UserDto;
import com.example.Warehouse.Management.System.payload.request.SignUpRequest;
import com.example.Warehouse.Management.System.repository.UserRepository;
import com.example.Warehouse.Management.System.repository.UserRoleRepository;
import com.example.Warehouse.Management.System.service.IService.IUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService implements IUser {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleRepository roleRepository;

    @Override
    public Page<User> listAllUsers(Pageable pageable) {

        return userRepository.findAll(pageable);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("User by id " + id + " was not found"));
    }

    @Override
    public User saveNewUser(SignUpRequest signUpRequest) {
        log.info("Saving new user {} to the database", signUpRequest.getUsername());
        User newUser = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword());
        return userRepository.save(newUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User updateUserById(UserDto user, Long id) {
        User existingUser = userRepository.findById(id).orElseThrow(()->
                new ResourceNotFoundException("User not found"));
        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        existingUser.setRoles(user.getRole());

        userRepository.save(existingUser);

        return existingUser;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void changeUserPassword(final User user, final String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    public boolean checkIfValidOldPassword(final User user, final String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
