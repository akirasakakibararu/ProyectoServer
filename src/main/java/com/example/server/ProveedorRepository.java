package com.example.server;

import org.springframework.data.jpa.repository.JpaRepository;
import pojos.Proveedores;

public interface ProveedorRepository extends JpaRepository<Proveedores, Integer> {
}