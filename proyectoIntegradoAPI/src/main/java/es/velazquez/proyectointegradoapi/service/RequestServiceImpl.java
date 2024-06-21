package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.dto.ActivityDTO;
import es.velazquez.proyectointegradoapi.dto.RequestDTO;
import es.velazquez.proyectointegradoapi.model.Activity;
import es.velazquez.proyectointegradoapi.model.Request;
import es.velazquez.proyectointegradoapi.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequestServiceImpl implements RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Override
    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    @Override
    public RequestDTO getRequestDTOById(Long id) {
        Request request = requestRepository.findById(id).orElse(null);

        if (request == null) {
            return null;
        }

        return new RequestDTO(
                request.getName(),
                request.getDescription(),
                request.getDate(),
                request.getLocation(),
                request.getCustomer().getId()
        );
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public void deleteRequest(Long id) {
        requestRepository.deleteById(id);
    }

    @Override
    public List<Request> getRequestsByCustomer(Long customerId) {
        return requestRepository.findByCustomer_Id(customerId);
    }
}
