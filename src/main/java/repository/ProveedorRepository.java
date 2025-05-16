package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pojos.Proveedores;
@Repository
public interface ProveedorRepository extends JpaRepository<Proveedores, Integer> {
}