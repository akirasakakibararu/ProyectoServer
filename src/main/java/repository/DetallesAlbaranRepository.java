package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import pojos.DetallesAlbaran;

@Repository
public interface DetallesAlbaranRepository extends JpaRepository<DetallesAlbaran, Integer> {
    List<DetallesAlbaran> findByAlbaran(int albaranId);

}