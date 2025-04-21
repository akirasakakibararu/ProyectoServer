package com.example.server;

import org.springframework.data.jpa.repository.JpaRepository;

import pojos.Usuarios;

public interface UserRepository extends JpaRepository <Usuarios , Long> {
	Usuarios findByEmail(String email);

	Usuarios findByNombre(String nombre);
}
