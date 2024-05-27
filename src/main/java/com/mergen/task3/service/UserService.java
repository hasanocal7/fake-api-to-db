package com.mergen.task3.service;

import com.mergen.task3.entity.User;
import com.mergen.task3.helper.UserHelper;
import com.mergen.task3.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserHelper userHelper;

    public void saveAllUsers() throws IOException {
        List<User> users = userHelper.dtoToEntityConverter();
        userRepository.saveAll(users);
    }

    public List<User> getUsers(){
        return (List<User>) userRepository.findAll();
    }

    public User getUserById(Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new RuntimeException("User Not Found");
        return optionalUser.get();
    }
}
