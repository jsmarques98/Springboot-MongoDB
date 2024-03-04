package com.joaomarques.springbootmongo.resources;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joaomarques.springbootmongo.domain.Post;
import com.joaomarques.springbootmongo.domain.User;
import com.joaomarques.springbootmongo.dto.UserDTO;
import com.joaomarques.springbootmongo.services.UserService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<UserDTO> findById(@PathVariable String id){

        User obj = service.findById(id);
        
        return ResponseEntity.ok().body(new UserDTO(obj));

    }

    @RequestMapping(method=RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){

        User obj = service.fromDTO(objDto);  //convert dto to user
        obj = service.insert(obj);  //insert user in database
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(obj.getId()).toUri();  //return with header
        return ResponseEntity.created(uri).build();

    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable String id){

        service.delete(id);        
        return ResponseEntity.noContent().build();

    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id){

        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();

    }


    @RequestMapping(value="/{id}/posts", method=RequestMethod.GET)
    public ResponseEntity <List<Post>> findPosts(@PathVariable String id){

        User obj = service.findById(id);
        
        return ResponseEntity.ok().body(obj.getPosts());

    }

}
