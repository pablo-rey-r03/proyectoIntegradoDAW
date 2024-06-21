package es.velazquez.proyectointegradoapi.repository;

import es.velazquez.proyectointegradoapi.model.Activity;
import es.velazquez.proyectointegradoapi.model.Offerer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    List<Activity> findByOffererId(Long offererId);
}
