package com.alura.challenge.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alura.challenge.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail (String email);

}
