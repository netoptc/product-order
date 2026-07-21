package com.netoptc.productorder.controllers;


import com.netoptc.productorder.dtos.UserDto;
import com.netoptc.productorder.entities.User;
import com.netoptc.productorder.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> getMe() {
        UserDto dto = userService.getMe();
        return ResponseEntity.ok(dto);
    }

}
