package com.Zetung.QAportal.repository;

import com.Zetung.QAportal.model.UserCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserCustomer,Integer> {
    Optional<UserCustomer> findByEmail(String email);
}
