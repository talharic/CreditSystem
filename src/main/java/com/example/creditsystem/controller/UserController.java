package com.example.creditsystem.controller;

import com.example.creditsystem.dto.UserRequestDto;
import com.example.creditsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{nationalIdNumber}")
    public ResponseEntity<Object> getByNationalIdNumber(@PathVariable String nationalIdNumber) {
        return ResponseEntity.ok(userService.findByNationalIdNumber(nationalIdNumber));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }


    @PutMapping("/{nationalIdNumber}")
    public ResponseEntity<Object> update(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable String nationalIdNumber) {
        return ResponseEntity.ok(userService.update(userRequestDto, nationalIdNumber));
    }

    @DeleteMapping("/{nationalIdNumber}")
    public String deleteByNationalIdNumber(@PathVariable String nationalIdNumber) {
        userService.deleteByNationalIdNumber(nationalIdNumber);
        return "User deleted National Id Number is : " + nationalIdNumber;
    }

}
