package repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pojos.Movimientos;
import pojos.Productos;
@Repository
public interface MovimientosRepository extends JpaRepository<Movimientos, Integer>{
	 // Buscar productos por nombre
    List<Movimientos> findByUsuario(int usuario);
}