package com.mackenzie.e_commerce.repository;

import com.mackenzie.e_commerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);

    UserDetails findUserDetailsByEmail(String email);
}
