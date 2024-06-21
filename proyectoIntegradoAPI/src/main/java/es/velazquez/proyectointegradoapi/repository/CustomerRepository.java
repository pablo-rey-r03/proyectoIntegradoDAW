package es.velazquez.proyectointegradoapi.repository;

import es.velazquez.proyectointegradoapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserEmail(String userEmail);
}
