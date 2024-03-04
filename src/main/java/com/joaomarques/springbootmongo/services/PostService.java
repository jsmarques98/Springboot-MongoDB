package com.joaomarques.springbootmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaomarques.springbootmongo.domain.Post;
import com.joaomarques.springbootmongo.repository.PostRepository;
import com.joaomarques.springbootmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {
    
    @Autowired
    private PostRepository repo;

    public List<Post> findAll(){
        return repo.findAll();
    }

    public Post findById(String id) {
        Optional<Post> Post = repo.findById(id);
        if(!Post.isPresent()){
            throw new ObjectNotFoundException("Object not found");
        }
        return Post.get();
    }
}
