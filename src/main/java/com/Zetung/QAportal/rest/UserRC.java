package com.Zetung.QAportal.rest;

import com.Zetung.QAportal.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserRC {
    private List<User> users = Stream.of(
            new User(1,"John","Doe","prot@gmail.com","+0756667788"),
            new User(2,"John1","Doe1","prot1@gmail.com","+1756667788"),
            new User(3,"John2","Doe2","prot2@gmail.com","+2756667788")
    ).collect(Collectors.toList());

    @GetMapping
    public List<User> getUsers(){
        return users;
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable int id){
        return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
    }
}
