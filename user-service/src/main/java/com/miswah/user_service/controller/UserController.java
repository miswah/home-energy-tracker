package com.miswah.user_service.controller;


import com.miswah.user_service.dto.UserDto;
import com.miswah.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto dto){
        UserDto userDto = this.userService.createUser(dto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<UserDto> getUser(@PathVariable Long id){
        UserDto userDto = this.userService.getUser(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> updatedUser(@PathVariable Long id, @RequestBody UserDto dto){
       try{
           this.userService.updateUser(id, dto);
           return ResponseEntity.ok("User Updated");
       } catch (IllegalArgumentException ex){
           return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
       }

    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@PathVariable Long id){
        try{
            this.userService.deleteUser(id);
            return ResponseEntity.ok("User Deleted");
        } catch (IllegalArgumentException ex){
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

}
