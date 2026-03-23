package com.ty.rentshield.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.rentshield.Entity.User;
import com.ty.rentshield.response.ResponseStructure;
import com.ty.rentshield.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
    @Autowired
    private UserService userService;
    
    ///////////////////////////////////////////  SAVE  /////////////////////////////////////////////////////////////////
    @PostMapping("/register")
    public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {

        User savedUser = userService.saveUser(user);

        ResponseStructure<User> response = new ResponseStructure<>();
        response.setStatusCode(HttpStatus.OK.value());
        response.setMessage("User Saved Successfully");
        response.setData(savedUser);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    //////////////////////////////// User by Id  ///////////////////////////////////////////////////////
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<User>> getUserById(@PathVariable Long id){

        User user = userService.getUserById(id);

        ResponseStructure<User> structure = new ResponseStructure<>();
        structure.setMessage("User Found Successfully.");
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setData(user);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    ///////////////////////////////////////////// Get all Users  ///////////////////////////////////////////////////
    @GetMapping
    public ResponseEntity<ResponseStructure<List<User>>> getAllUsers(){

        List<User> users = userService.getAllUsers();

        ResponseStructure<List<User>> structure = new ResponseStructure<>();
        structure.setMessage("All Users Fetched Successfully.");
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setData(users);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    //////////////////////////////////////////// Update User  ///////////////////////////////////////////////////////
    @PutMapping("/{id}")
    public ResponseEntity<ResponseStructure<User>> updateUser(@PathVariable Long id, @RequestBody User u){

        User updatedUser = userService.updateUser(id, u);

        ResponseStructure<User> structure = new ResponseStructure<>();
        structure.setMessage("User Updated Successfully.");
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setData(updatedUser);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    ///////////////////////////////////////////////// Delete User  /////////////////////////////////////////////////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseStructure<String>> deleteUser(@PathVariable Long id){

        String message = userService.deleteUser(id);

        ResponseStructure<String> structure = new ResponseStructure<>();
        structure.setMessage("User Deleted Successfully.");
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setData(message);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
    
    
}