package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.dto.RequestDTO;
import es.velazquez.proyectointegradoapi.model.Request;

import java.util.List;

public interface RequestService {
    RequestDTO getRequestDTOById(Long id);
    Request getRequestById(Long id);
    List<Request> getAllRequests();
    Request saveRequest(Request request);
    void deleteRequest(Long id);
    List<Request> getRequestsByCustomer(Long customerId);
}
