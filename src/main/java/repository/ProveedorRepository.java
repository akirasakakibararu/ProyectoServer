package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pojos.Proveedores;
import org.springframework.stereotype.Repository;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedores, Integer> {
}