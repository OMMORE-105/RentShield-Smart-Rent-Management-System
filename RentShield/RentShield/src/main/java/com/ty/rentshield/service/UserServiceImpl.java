package com.ty.rentshield.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.rentshield.Entity.User;
import com.ty.rentshield.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserRepository repository;

    @Override
    public User saveUser(User user) {
        return repository.save(user);
    }
	
    
    @Override
    public User updateUser(Long id, User user) {
        Optional<User> optional = repository.findById(id);

        if (optional.isPresent()) {
            User existingUser = optional.get();
            existingUser.setUserName(user.getUserName());
            existingUser.setEmail(user.getEmail());
            existingUser.setUserPassword(user.getUserPassword());
            existingUser.setRole(user.getRole());

            return repository.save(existingUser);
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public String deleteUser(Long id) {
        repository.deleteById(id);
        return "User Deleted Successfully";
    }
    
    
    
    
    
    
}
