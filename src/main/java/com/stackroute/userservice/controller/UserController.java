    package com.stackroute.userservice.controller;


import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExist;
import com.stackroute.userservice.exception.UserNotFound;
import com.stackroute.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1")
public class UserController {
    @Autowired
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "To save the track")
    @PostMapping("user")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.saveUser(user);
            responseEntity = new ResponseEntity<String>("successfully created", HttpStatus.CREATED);
        } catch (UserAlreadyExist ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @GetMapping("/getuser")
    public ResponseEntity<?> getALLUsers() {

        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<User>>(userService.getAllUser(), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @PutMapping("/update")
    @ApiOperation(value = "To update the track")
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        ResponseEntity responseEntity;
        try {
            userService.upadate(user);
            responseEntity = new ResponseEntity<String>("sucessfully updated", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "To delete the track")
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable int userId) {
        ResponseEntity responseEntity;
        try {
            userService.deleteUser(userId);
            responseEntity = new ResponseEntity<String>("sucessfully deleted  ", HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);
        }
        return responseEntity;
    }

    @ApiOperation(value = "To find the track by name")
    @GetMapping("/find/{name}")
    public ResponseEntity<?> findUserByTrackName(@PathVariable String name) {
        ResponseEntity responseEntity;
        try {
            responseEntity = new ResponseEntity<List<User>>(userService.findUserByTrackName(name), HttpStatus.OK);

        } catch (UserNotFound ex) {
            responseEntity = new ResponseEntity<String>(ex.getMessage(), HttpStatus.CONFLICT);

            return responseEntity;
        }
        return responseEntity;
    }
}
