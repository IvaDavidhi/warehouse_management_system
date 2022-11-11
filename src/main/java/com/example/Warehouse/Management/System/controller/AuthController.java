package com.example.Warehouse.Management.System.controller;


import com.example.Warehouse.Management.System.exceptions.InvalidOldPasswordException;
import com.example.Warehouse.Management.System.model.User;
import com.example.Warehouse.Management.System.payload.request.LoginRequest;
import com.example.Warehouse.Management.System.payload.dto.PasswordDto;
import com.example.Warehouse.Management.System.payload.request.SignUpRequest;
import com.example.Warehouse.Management.System.payload.response.JwtResponse;
import com.example.Warehouse.Management.System.service.ServiceImpl.AuthService;
import com.example.Warehouse.Management.System.service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@SecurityRequirement(name = "warehouse")
public class AuthController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    AuthService authService;

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> sigInUser(@RequestBody LoginRequest loginRequest) {

        return ResponseEntity.ok().body(authService.authenticateUser(loginRequest));
    }

    @PostMapping("/signup")
    public User registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {

        return  this.userService.saveNewUser(signUpRequest);

    }

    // Change user password
    @PostMapping("/user/updatePassword")
    public String changeUserPassword(@Valid PasswordDto passwordDto) {
        final User user = userService.findUserByEmail(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getEmail());
        if (!userService.checkIfValidOldPassword(user, passwordDto.getOldPassword())) {
            throw new InvalidOldPasswordException();
        }
        userService.changeUserPassword(user, passwordDto.getNewPassword());

        return "Password updated";
    }

/*    method to reset password with token email
    When a password reset is triggered –
     a token will be created and a special link containing this token will be emailed to the user.
    @PostMapping("/user/resetPassword")
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        User user = userService.findUserByEmail(userEmail);
        if (user == null) {
            throw new UserNotFoundException();
        }
        String token = UUID.randomUUID().toString();
        authService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                request.getLocale(), token, user));
        return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));
    } */

}
