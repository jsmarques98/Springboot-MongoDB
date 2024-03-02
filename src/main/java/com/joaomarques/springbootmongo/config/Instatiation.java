package com.joaomarques.springbootmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joaomarques.springbootmongo.domain.User;
import com.joaomarques.springbootmongo.repository.UserRepository;

@Configuration
public class Instatiation implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("null")
    @Override
    public void run(String... arg0) throws Exception {

        userRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria, alex, bob));
    }
    
}
