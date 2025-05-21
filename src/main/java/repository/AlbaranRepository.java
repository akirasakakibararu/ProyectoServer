package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import pojos.Albaranes;

import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Repository;




import pojos.Albaranes;
@Repository
public interface AlbaranRepository extends JpaRepository<Albaranes, Integer> {
    @Transactional

    List<Albaranes> findByFechaAlbaranBetween(Timestamp inicio, Timestamp fin);
    
}
