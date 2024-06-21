package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.model.Offerer;
import es.velazquez.proyectointegradoapi.repository.OffererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OffererServiceImpl implements OffererService {

    @Autowired
    private OffererRepository offererRepository;

    @Override
    public Offerer getOffererById(Long id) {
        return offererRepository.findById(id).orElse(null);
    }

    @Override
    public Offerer getOffererByUserEmail(String userEmail) {
        return offererRepository.findByUserEmail(userEmail);
    }

    @Override
    public List<Offerer> getAllOfferers() {
        return offererRepository.findAll();
    }

    @Override
    public Offerer saveOfferer(Offerer offerer) {
        return offererRepository.save(offerer);
    }

    @Override
    public void deleteOfferer(Long id) {
        offererRepository.deleteById(id);
    }
}
