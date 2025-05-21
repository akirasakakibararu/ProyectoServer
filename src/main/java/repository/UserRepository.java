package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pojos.Usuarios;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository <Usuarios ,Integer> {
	Usuarios findByEmail(String email);

	Usuarios findByNombre(String nombre);
}