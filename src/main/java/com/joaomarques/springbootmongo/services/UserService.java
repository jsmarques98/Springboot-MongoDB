package com.joaomarques.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaomarques.springbootmongo.domain.User;
import com.joaomarques.springbootmongo.repository.UserRepository;
import com.joaomarques.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository repo;

    public List<User> findAll(){
        return repo.findAll();
    }

    public User findById(String id) {
        Optional<User> user = repo.findById(id);
        if(!user.isPresent()){
            throw new ObjectNotFoundException("Object not found");
        }
        return user.get();
    }
}
