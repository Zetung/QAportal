package com.Zetung.QAportal.rest;

import com.Zetung.QAportal.model.Role;
import com.Zetung.QAportal.model.UserCustomer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
public class UserCustomerRC {
    private List<UserCustomer> userCustomers = Stream.of(
            new UserCustomer(1,"John","Doe","prot@gmail.com","asdas","+0756667788", Role.USER),
            new UserCustomer(2,"John1","Doe1","prot1@gmail.com","+1756667788","asdas1", Role.USER),
            new UserCustomer(3,"John2","Doe2","prot2@gmail.com","+2756667788","asdas2", Role.USER)
    ).collect(Collectors.toList());

    @GetMapping
    public List<UserCustomer> getUsers(){
        return userCustomers;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user:read')")
    public UserCustomer getById(@PathVariable int id){
        return userCustomers.stream().filter(userCustomer -> userCustomer.getId() == id).findFirst().orElse(null);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('user:write')")
    public UserCustomer create(@RequestBody UserCustomer userCustomer){
        this.userCustomers.add(userCustomer);
        return userCustomer;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('user:write')")
    public void deleteById(@PathVariable int id){
        this.userCustomers.removeIf(userCustomer -> userCustomer.getId() == id);
    }

}
