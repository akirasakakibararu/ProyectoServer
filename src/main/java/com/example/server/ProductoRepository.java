package com.example.server;

 
import pojos.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProductoRepository extends JpaRepository<Productos, Integer> {

    // Buscar productos por nombre
    List<Productos> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos por habilitado
    List<Productos> findByHabilitado(boolean habilitado);
}
