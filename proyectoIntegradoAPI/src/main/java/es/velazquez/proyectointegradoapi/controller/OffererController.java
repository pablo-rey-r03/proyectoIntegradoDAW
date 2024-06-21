package es.velazquez.proyectointegradoapi.controller;

import es.velazquez.proyectointegradoapi.dto.OffererDTO;
import es.velazquez.proyectointegradoapi.dto.UserDTO;
import es.velazquez.proyectointegradoapi.dto.UserEmail;
import es.velazquez.proyectointegradoapi.model.*;
import es.velazquez.proyectointegradoapi.security.jwt.JwtTokenProvider;
import es.velazquez.proyectointegradoapi.service.ActivityServiceImpl;
import es.velazquez.proyectointegradoapi.service.OffererServiceImpl;
import es.velazquez.proyectointegradoapi.service.UserServiceImpl;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author pablorey
 * @since 30-05-2024
 */
@RestController
@RequestMapping("/off")
@CrossOrigin
public class OffererController {
    private static final Logger log = LoggerFactory.getLogger(OffererController.class);

    @Autowired
    private OffererServiceImpl offererService;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private ActivityServiceImpl activityService;

    /**
     * Extremo de la API que devuelve una lista de todos los {@link Offerer}.
     * @return La lista con los ofertantes y {@link HttpStatus#OK}.
     * @see OffererServiceImpl#getAllOfferers()
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(offererService.getAllOfferers(), HttpStatus.OK);
    }

    /**
     * Extremo de la API que devuelve un {@link Offerer} según el email de su usuario asociado. Se utiliza para gestionar las
     * sesiones en el <i>frontend</i>, ya que el JWT almacena el email del usuario ({@link JwtTokenProvider#generateToken(Authentication)}); así sabremos los datos personales
     * del usuario en sesión.
     * @param userEmail Un objeto con el email del usuario que se quiere buscar.
     * @return El ofertante asociado al usuario con el email proporcionado y {@link HttpStatus#OK}. Devuelve un error si
     * no hay usuarios con ese email {@link HttpStatus#NOT_FOUND} o si el usuario no es un ofertante
     * {@link HttpStatus#BAD_REQUEST}.
     * @apiNote Se utliza una clase simple {@link UserEmail} que sólo almacena un {@link String} debido a que la API
     * envía y recibe datos en formato JSON.
     * @see OffererServiceImpl#getOffererByUserEmail(String)
     * @see UserEmail
     */
    @PostMapping("/detailByUser")
    public ResponseEntity<?> detailByUser(@RequestBody UserEmail userEmail) {
        log.info("userEmail: " + userEmail.toString());
        if (userService.getUserByEmail(userEmail.getEmail()) == null) {
            return new ResponseEntity<>("ERROR: no hay usuarios con el email proporcionado.", HttpStatus.NOT_FOUND);
        } else if (userService.getUserByEmail(userEmail.getEmail()).getRole() != Role.ROLE_OFF) {
            return new ResponseEntity<>("ERROR: el usuario no es un ofertante.", HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(offererService.getOffererByUserEmail(userEmail.getEmail()), HttpStatus.OK);
        }
    }

    /**
     * Extremo de la API que devuelve sólo un {@link Offerer} a partir de su ID.
     * @param id El ID único del ofertante.
     * @return El ofertante con el ID proporcionado; si no lo encuentra, un mensaje de error {@link HttpStatus#NOT_FOUND}.
     * @see OffererServiceImpl#getOffererById(Long)
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long id) {
        Offerer offerer = offererService.getOffererById(id);

        if (offerer == null) {
            return new ResponseEntity<>("ERROR: no hay ofertantes con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(offerer, HttpStatus.OK);
    }

    /**
     * Extremo de la API que inserta un {@link Offerer} en el sistema a partir de los datos introducidos. El campo
     * {@link Offerer#getUser()} debe existir, y su {@link User#getRole()} debe ser {@link Role#ROLE_OFF}.
     * @param offererDTO Un objeto JSON con los valores para los campos del nuevo ofertante.
     * @return El ofertante creado con {@link HttpStatus#OK}, o mensajes de error si los datos no son adecuados ({@link HttpStatus#BAD_REQUEST}).
     * @see OffererServiceImpl#saveOfferer(Offerer)
     * @see OffererDTO
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody OffererDTO offererDTO) {
        Offerer offerer = new Offerer(
                offererDTO.getName(),
                offererDTO.getSurname(),
                offererDTO.getAddress(),
                offererDTO.getPhoneNumber(),
                offererDTO.getBirthDate(),
                offererDTO.getFormation(),
                userService.getUserByEmail(offererDTO.getUser()),
                new HashSet<>()
        );

        if (userService.getUserByEmail(offererDTO.getUser()) == null) {
            return new ResponseEntity<>("ERROR: usuario no encontrado.", HttpStatus.BAD_REQUEST);
        } else if (offerer.getUser().getRole() != Role.ROLE_OFF) {
            return new ResponseEntity<>("ERROR: se ha asociado un usuario con un rol distinto a OFERTANTE.", HttpStatus.BAD_REQUEST);
        } else {
            offererService.saveOfferer(offerer);
            return new ResponseEntity<>(offerer, HttpStatus.CREATED);
        }
    }

    /**
     * Extremo de la API que actualiza un {@link Offerer} ya existente según su ID.
     * @param id El ID del ofertante a actualizar.
     * @param offererDTO Un objeto con los nuevos valores del ofertante.
     * @return El ofertante ya actualizado con {@link HttpStatus#OK}, o un error {@link HttpStatus#NOT_FOUND} si no
     * existe un ofertante con el ID suministrado.
     * @see OffererServiceImpl#saveOfferer(Offerer)
     * @see OffererDTO
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody OffererDTO offererDTO) {
        log.info(offererDTO.toString());
        Offerer offerer = offererService.getOffererById(id);

        if (offerer == null) {
            return new ResponseEntity<>("ERROR: no hay ofertantes con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }

        offerer.setName(offererDTO.getName());
        offerer.setSurname(offererDTO.getSurname());
        offerer.setAddress(offererDTO.getAddress());
        offerer.setPhoneNumber(offererDTO.getPhoneNumber());
        offerer.setBirthDate(offererDTO.getBirthDate());
        offerer.setFormation(offererDTO.getFormation());

        Offerer actualizado = offererService.saveOfferer(offerer);
        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    /**
     * Extremo de la API que elimina del sistema un {@link Offerer} ya existente.
     * @param id El ID del ofertante a eliminar.
     * @return La nueva lista actualizada de ofertantes con {@link HttpStatus#OK}, o un error
     * {@link HttpStatus#NOT_FOUND} si no existe un ofertante con ese ID.
     * @apiNote Para evitar error por restricción de clave foránea, eliminamos antes todas las actividades asociadas al
     * ofertante ({@link ActivityServiceImpl#getActivitiesByOfferer(Long)}).
     * @see OffererServiceImpl#deleteOfferer(Long)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Offerer offerer = offererService.getOffererById(id);

        if (offerer == null) {
            return new ResponseEntity<>("ERROR: no hay ofertantes con el ID proporcionado.", HttpStatus.NOT_FOUND);
        } else {
            for (Activity a : activityService.getActivitiesByOfferer(id)) {
                activityService.deleteActivity(a.getId());
            }
            offererService.deleteOfferer(id);
            return new ResponseEntity<>(offererService.getAllOfferers(), HttpStatus.OK);
        }
    }
}
