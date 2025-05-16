package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pojos.Usuarios;
@Repository
public interface UserRepository extends JpaRepository <Usuarios ,Integer> {
	Usuarios findByEmail(String email);

	Usuarios findByNombre(String nombre);
}