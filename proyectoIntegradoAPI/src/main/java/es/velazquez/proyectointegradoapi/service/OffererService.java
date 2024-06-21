package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.model.Offerer;
import es.velazquez.proyectointegradoapi.model.User;

import java.util.List;

public interface OffererService {
    Offerer getOffererById(Long id);
    Offerer getOffererByUserEmail(String userEmail);
    List<Offerer> getAllOfferers();
    Offerer saveOfferer(Offerer offerer);
    void deleteOfferer(Long id);
}
