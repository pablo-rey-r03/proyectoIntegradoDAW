package es.velazquez.proyectointegradoapi.repository;

import es.velazquez.proyectointegradoapi.model.Offerer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffererRepository extends JpaRepository<Offerer, Long> {
    Offerer findByUserEmail(String userEmail);
}
