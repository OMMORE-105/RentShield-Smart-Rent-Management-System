package com.ty.rentshield.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ty.rentshield.Entity.User;

@Service
public interface UserService {

	User saveUser(User user);

	User updateUser(Long id, User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    String deleteUser(Long id);    
	   
//    UserDTO getUserById(Long id);
}
