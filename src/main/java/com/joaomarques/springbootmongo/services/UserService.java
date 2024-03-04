package com.joaomarques.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaomarques.springbootmongo.domain.User;
import com.joaomarques.springbootmongo.dto.UserDTO;
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

    public User insert(User obj){
        return repo.insert(obj);
    }

    public void delete(String id){
        User user = findById(id);
        repo.delete(user);
    }

    public User update(User obj){
        Optional<User> optionalUser = repo.findById(obj.getId());
        if(!optionalUser.isPresent()) {
            throw new ObjectNotFoundException("Object not found");
        }
        User newObj = optionalUser.get();
        updateData(newObj, obj);
        return repo.save(newObj);
    }

    public void updateData(User newObj, User obj){
        newObj.setName(obj.getName());
        newObj.setEmail(obj.getEmail());
    }


    public User fromDTO(UserDTO objDto){
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }
}
