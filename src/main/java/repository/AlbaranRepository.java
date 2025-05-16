package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pojos.Albaranes;
@Repository
public interface AlbaranRepository extends JpaRepository<Albaranes, Integer> {
}