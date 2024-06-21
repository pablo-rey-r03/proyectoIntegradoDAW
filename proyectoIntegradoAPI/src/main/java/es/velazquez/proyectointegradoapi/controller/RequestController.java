package es.velazquez.proyectointegradoapi.controller;

import es.velazquez.proyectointegradoapi.dto.ActivityDTO;
import es.velazquez.proyectointegradoapi.dto.RequestDTO;
import es.velazquez.proyectointegradoapi.model.Activity;
import es.velazquez.proyectointegradoapi.model.Request;
import es.velazquez.proyectointegradoapi.model.Customer;
import es.velazquez.proyectointegradoapi.service.ActivityServiceImpl;
import es.velazquez.proyectointegradoapi.service.CustomerServiceImpl;
import es.velazquez.proyectointegradoapi.service.RequestServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author pablorey
 * @since 02/06/2024
 */
@RestController
@RequestMapping("/req")
@CrossOrigin
public class RequestController {
    @Autowired
    RequestServiceImpl requestService;

    @Autowired
    CustomerServiceImpl customerService;

    /**
     * Extremo de la API que devuelve una lista de todas las {@link Request}.
     * @return La lista con las solicitudes y {@link HttpStatus#OK}.
     * @see RequestServiceImpl#getAllRequests()
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    /**
     * Extremo de la API que devuelve sólo una {@link Request} a partir de su ID.
     * @param id El ID único de la solicitud.
     * @return La solicitud con el ID proporcionado; si no lo encuentra, un mensaje de error {@link HttpStatus#NOT_FOUND}.
     * @see RequestServiceImpl#getRequestById(Long)
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id) {
        Request request = requestService.getRequestById(id);

        if (request == null) {
            return new ResponseEntity<>("ERROR: no se han encontrado solicitudes con el ID proporcionado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(requestService.getRequestById(id), HttpStatus.OK);
        }
    }

    /**
     * Extremo de la API que devuelve todas las {@link Request} asociados a un {@link Customer}.
     * @param customerId El ID único del consumidor.
     * @return La lista de solicitudes del consumidor, o error si la lista está vacía o no hay consumidores con ese ID ({@link HttpStatus#NOT_FOUND}).
     * @see RequestServiceImpl#getRequestsByCustomer(Long)
     */
    @GetMapping("/cus/{id}")
    public ResponseEntity<?> listByCustomer(@PathVariable("id") Long customerId) {
        List<Request> reqByCus = requestService.getRequestsByCustomer(customerId);

        if (reqByCus.isEmpty()) {
            return new ResponseEntity<>("ERROR: no hay solicitudes asociadas al consumidor, o no se ha encontrado éste.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(reqByCus, HttpStatus.OK);
        }
    }

    /**
     * Extremo de la API que inserta una {@link Request} en el sistema a partir de los datos introducidos. El campo
     * {@link Request#getCustomer()} debe existir.
     * @param requestDTO Un objeto JSON con los valores para los campos de la nueva solicitud.
     * @return La solicitud creada con {@link HttpStatus#OK}, o mensajes de error si los datos no son adecuados ({@link HttpStatus#BAD_REQUEST}).
     * @see RequestServiceImpl#saveRequest(Request)
     * @see RequestDTO
     */
    @PostMapping("/create")
    public ResponseEntity<?> create (@Valid @RequestBody RequestDTO requestDTO) {
        Request request = new Request(
                requestDTO.getName(),
                requestDTO.getDescription(),
                requestDTO.getDate(),
                requestDTO.getLocation(),
                customerService.getCustomerById(requestDTO.getCustomerId())
        );

        if (customerService.getCustomerById(requestDTO.getCustomerId()) == null) {
            return new ResponseEntity<>("ERROR: consumidor no encontrado.", HttpStatus.BAD_REQUEST);
        } else {
            Request insertada = requestService.saveRequest(request);
            return new ResponseEntity<>(insertada, HttpStatus.CREATED);
        }
    }

    /**
     * Extremo de la API que actualiza una {@link Request} ya existente según su ID.
     * @param id El ID de la solicitud a actualizar.
     * @param requestDTO Un objeto con los nuevos valores de la solicitud.
     * @return La solicitud ya actualizada con {@link HttpStatus#OK}, o un error {@link HttpStatus#NOT_FOUND} si no
     * existe una solicitud con el ID suministrado.
     * @see RequestServiceImpl#saveRequest(Request)
     * @see RequestDTO
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody RequestDTO requestDTO) {
        Request request = requestService.getRequestById(id);

        if (request == null) {
            return new ResponseEntity<>("ERROR: no hay solicitudes con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }

        request.setName(requestDTO.getName());
        request.setDescription(requestDTO.getDescription());
        request.setDate(requestDTO.getDate());
        request.setLocation(requestDTO.getLocation());

        Request actualizado = requestService.saveRequest(request);

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    /**
     * Extremo de la API que elimina del sistema una {@link Request} ya existente.
     * @param id El ID de la solicitud a eliminar.
     * @return La nueva lista de solicitudes asociada al consumidor de la solicitud eliminada con código {@link HttpStatus#OK},
     * o un error {@link HttpStatus#NOT_FOUND} si no existe una solicitud con ese ID.
     * @see RequestServiceImpl#deleteRequest(Long)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Request request = requestService.getRequestById(id);

        if (request == null) {
            return new ResponseEntity<>("ERROR: no hay solicitudes con el ID proporcionado.", HttpStatus.NOT_FOUND);
        } else {
            requestService.deleteRequest(id);
            return new ResponseEntity<>(requestService.getRequestsByCustomer(request.getCustomer().getId()), HttpStatus.OK);
        }
    }
}
