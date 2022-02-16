package com.example.creditsystem.controller;

import com.example.creditsystem.dto.UserRequestDto;
import com.example.creditsystem.dto.UserResponseDto;
import com.example.creditsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable Long id) {

        UserResponseDto userDto = userService.findById(id);

        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/id-number/{nationalIdNumber}")
    public ResponseEntity<Object> getByNationalIdNumber(@PathVariable String nationalIdNumber) {

        UserResponseDto userResponseDto = userService.findByNationalIdNumber(nationalIdNumber);

        return ResponseEntity.ok(userResponseDto);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllUsers() {
        List<UserResponseDto> all = userService.findAll();
        return ResponseEntity.ok(all);
    }

    @PostMapping
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.create(userRequestDto));
    }

    @PutMapping("/{nationalIdNumber}")
    public ResponseEntity<Object> create(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable String nationalIdNumber) {
        return ResponseEntity.ok(userService.update(userRequestDto, nationalIdNumber));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }

}
