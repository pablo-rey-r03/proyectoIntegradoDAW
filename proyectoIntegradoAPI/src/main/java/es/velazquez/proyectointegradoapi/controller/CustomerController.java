package es.velazquez.proyectointegradoapi.controller;

import es.velazquez.proyectointegradoapi.dto.CustomerDTO;
import es.velazquez.proyectointegradoapi.dto.UserEmail;
import es.velazquez.proyectointegradoapi.model.*;
import es.velazquez.proyectointegradoapi.security.jwt.JwtTokenProvider;
import es.velazquez.proyectointegradoapi.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;

/**
 * @author pablorey
 * @since 31-05-2024
 */
@RestController
@RequestMapping("/cus")
@CrossOrigin
public class CustomerController {
    @Autowired
    private CustomerServiceImpl customerService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RequestServiceImpl requestService;

    /**
     * Extremo de la API que devuelve una lista con todos los {@link Customer}.
     * @return La lista de consumidores con {@link HttpStatus#OK}.
     * @see CustomerServiceImpl#getAllCustomers()
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    /**
     * Extremo de la API que devuelve un {@link Customer} según el email de su usuario asociado. Se utiliza para gestionar las
     * sesiones en el <i>frontend</i>, ya que el JWT almacena el email del usuario ({@link JwtTokenProvider#generateToken(Authentication)}); así sabremos los datos personales
     * del usuario en sesión.
     * @param userEmail Un objeto con el email del usuario que se quiere buscar.
     * @return El consumidor asociado al usuario con el email proporcionado y {@link HttpStatus#OK}. Devuelve un error si
     * no hay usuarios con ese email ({@link HttpStatus#NOT_FOUND}) o si el usuario no es un consumidor
     * ({@link HttpStatus#BAD_REQUEST}).
     * @apiNote Se utliza una clase simple {@link UserEmail} que sólo almacena un {@link String} debido a que la API
     * envía y recibe datos en formato JSON.
     * @see CustomerServiceImpl#getCustomerByUserEmail(String)
     * @see UserEmail
     */
    @PostMapping("/detailByUser")
    public ResponseEntity<?> detailByUser(@RequestBody UserEmail userEmail) {
        if (userService.getUserByEmail(userEmail.getEmail()) == null) {
            return new ResponseEntity<>("ERROR: no hay usuarios con el email proporcionado.", HttpStatus.NOT_FOUND);
        } else if (userService.getUserByEmail(userEmail.getEmail()).getRole() != Role.ROLE_CUS) {
            return new ResponseEntity<>("ERROR: el usuario no es un consumidor.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(customerService.getCustomerByUserEmail(userEmail.getEmail()), HttpStatus.OK);
        }
    }

    /**
     * Extremo de la API que devuelve sólo un {@link Customer} a partir de su ID.
     * @param id El ID único del consumidor.
     * @return El consumidor con el ID proporcionado; si no lo encuentra, un mensaje de error {@link HttpStatus#NOT_FOUND}.
     * @see CustomerServiceImpl#getCustomerById(Long)
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            return new ResponseEntity<>("ERROR: no hay consumidores con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    /**
     * Extremo de la API que inserta un {@link Customer} en el sistema a partir de los datos introducidos. El campo
     * {@link Customer#getUser()} debe existir, y su {@link User#getRole()} debe ser {@link Role#ROLE_CUS}.
     * @param customerDTO Un objeto JSON con los valores para los campos del nuevo consumidor.
     * @return El consumidor creado con {@link HttpStatus#OK}, o mensajes de error si los datos no son adecuados ({@link HttpStatus#BAD_REQUEST}).
     * @see CustomerServiceImpl#saveCustomer(Customer)
     * @see CustomerDTO
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = new Customer(
                customerDTO.getName(),
                customerDTO.getSurname(),
                customerDTO.getAddress(),
                customerDTO.getPhoneNumber(),
                customerDTO.getBirthDate(),
                userService.getUserByEmail(customerDTO.getUserEmail()),
                new HashSet<>()
        );

        if (userService.getUserByEmail(customerDTO.getUserEmail()) == null) {
            return new ResponseEntity<>("ERROR: usuario no encontrado.", HttpStatus.BAD_REQUEST);
        } else if (customer.getUser().getRole() != Role.ROLE_CUS) {
            return new ResponseEntity<>("ERROR: se ha asociado un usuario con un rol distinto a CONSUMIDOR.", HttpStatus.BAD_REQUEST);
        } else {
            Customer nuevo = customerService.saveCustomer(customer);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        }
    }

    /**
     * Extremo de la API que actualiza un {@link Customer} ya existente según su ID.
     * @param id El ID del consumidor a actualizar.
     * @param customerDTO Un objeto con los nuevos valores del consumidor.
     * @return El consumidor ya actualizado con {@link HttpStatus#OK}, o un error {@link HttpStatus#NOT_FOUND} si no
     * existe un consumidor con el ID suministrado.
     * @see CustomerServiceImpl#saveCustomer(Customer)
     * @see CustomerDTO
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody CustomerDTO customerDTO) {
        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            return new ResponseEntity<>("ERROR: no hay consumidores con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }

        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setAddress(customerDTO.getAddress());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        customer.setBirthDate(customerDTO.getBirthDate());

        Customer actualizado = customerService.saveCustomer(customer);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    /**
     * Extremo de la API que elimina del sistema un {@link Customer} ya existente.
     * @param id El ID del consumidor a eliminar.
     * @return La nueva lista de consumidores con {@link HttpStatus#OK}, o un error
     * {@link HttpStatus#NOT_FOUND} si no existe un consumidor con ese ID.
     * @apiNote Para evitar error por restricción de clave foránea, eliminamos antes todas las solicitudes asociadas al
     * consumidor ({@link RequestServiceImpl#getRequestsByCustomer(Long)}).
     * @see CustomerServiceImpl#deleteCustomer(Long)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Customer customer = customerService.getCustomerById(id);

        if (customer == null) {
            return new ResponseEntity<>("ERROR: no hay consumidores con el ID proporcionado.", HttpStatus.NOT_FOUND);
        } else {
            for (Request r : requestService.getRequestsByCustomer(id)) {
                requestService.deleteRequest(r.getId());
            }
            customerService.deleteCustomer(id);
            return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
        }
    }
}
