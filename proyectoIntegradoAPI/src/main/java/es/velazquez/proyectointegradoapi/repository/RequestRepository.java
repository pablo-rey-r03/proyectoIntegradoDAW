package es.velazquez.proyectointegradoapi.repository;

import es.velazquez.proyectointegradoapi.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByCustomer_Id(Long customerId);
}
