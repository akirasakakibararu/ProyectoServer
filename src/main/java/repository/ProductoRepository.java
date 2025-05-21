package repository;

 
import pojos.Productos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;
@Repository
public interface ProductoRepository extends JpaRepository<Productos, Integer> {

    // Buscar productos por nombre
    List<Productos> findByNombreContainingIgnoreCase(String nombre);

    // Buscar productos por habilitado
    List<Productos> findByHabilitado(boolean habilitado);
    @Query("SELECT p FROM Productos p WHERE p.stockActual <= p.stockMinimo")
    List<Productos> findProductosConStockMenorOIgualAlMinimo();
    List<Productos> findByHabilitadoFalse();
}
