package com.joaomarques.springbootmongo.resources;

import org.springframework.web.bind.annotation.RestController;

import com.joaomarques.springbootmongo.domain.User;
import com.joaomarques.springbootmongo.dto.UserDTO;
import com.joaomarques.springbootmongo.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<UserDTO>> findAll(){
        
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        //stream -> coleção compatível com expressões lamba do java
        //map -> pega em cada objeto x da lista original e retorna um novo DTO com esse objeto
        return ResponseEntity.ok().body(listDto);

    }
}
