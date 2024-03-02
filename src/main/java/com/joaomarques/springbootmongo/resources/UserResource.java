package com.joaomarques.springbootmongo.resources;

import org.springframework.web.bind.annotation.RestController;

import com.joaomarques.springbootmongo.domain.User;
import com.joaomarques.springbootmongo.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;




@RestController
@RequestMapping(value="/users")
public class UserResource {
    
    @Autowired
    private UserService service;

    @RequestMapping(method=RequestMethod.GET)
    public ResponseEntity<List<User>> findAll(){
        
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }
}
