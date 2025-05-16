package repository;

 
import pojos.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {

    // Buscar productos por nombre
    List<Productos> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos por habilitado
    List<Productos> findByHabilitado(boolean habilitado);
}
