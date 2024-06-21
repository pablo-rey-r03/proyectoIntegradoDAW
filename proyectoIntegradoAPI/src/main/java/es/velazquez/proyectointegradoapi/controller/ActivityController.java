package es.velazquez.proyectointegradoapi.controller;

import es.velazquez.proyectointegradoapi.dto.ActivityDTO;
import es.velazquez.proyectointegradoapi.model.Activity;
import es.velazquez.proyectointegradoapi.model.Offerer;
import es.velazquez.proyectointegradoapi.service.ActivityServiceImpl;
import es.velazquez.proyectointegradoapi.service.OffererServiceImpl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/**
 * @author pablorey
 * @since 02/06/2024
 */
@RestController
@RequestMapping("/act")
@CrossOrigin
public class ActivityController {

    @Autowired
    ActivityServiceImpl activityService;

    @Autowired
    OffererServiceImpl offererService;

    /**
     * Extremo de la API que devuelve una lista de todas las {@link Activity}.
     * @return La lista con las actividades y {@link HttpStatus#OK}.
     * @see ActivityServiceImpl#getAllActivities()
     */
    @GetMapping("/list")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(activityService.getAllActivities(), HttpStatus.OK);
    }

    /**
     * Extremo de la API que devuelve sólo una {@link Activity} a partir de su ID.
     * @param activityId El ID único de la actividad.
     * @return La actividad con el ID proporcionado; si no lo encuentra, un mensaje de error {@link HttpStatus#NOT_FOUND}.
     * @apiNote Se devuelve un DTO con los campos que se necesitan en el <i>frontend</i>.
     * @see ActivityServiceImpl#getActivityDTOById(Long)
     * @see ActivityDTO
     */
    @GetMapping("/detail/{id}")
    public ResponseEntity<?> detail(@PathVariable("id") Long activityId) {
        ActivityDTO activity = activityService.getActivityDTOById(activityId);

        if (activity == null) {
            return new ResponseEntity<>("ERROR: no se han encontrado actividades con el ID proporcionado", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activityService.getActivityDTOById(activityId), HttpStatus.OK);
        }
    }

    /**
     * Extremo de la API que devuelve todas las {@link Activity} asociadas a un {@link Offerer}.
     * @param offererId El ID único del ofertante.
     * @return La lista de actividades del ofertante, o error si la lista está vacía o no hay ofertantes con ese ID ({@link HttpStatus#NOT_FOUND}).
     * @see ActivityServiceImpl#getActivitiesByOfferer(Long)
     */
    @GetMapping("/off/{id}")
    public ResponseEntity<?> listByOfferer(@PathVariable("id") Long offererId) {
        List<Activity> activitiesByOfferer = activityService.getActivitiesByOfferer(offererId);

        if (activitiesByOfferer.isEmpty()) {
            return new ResponseEntity<>("ERROR: no hay actividades asociadas al ofertante, o no se ha encontrado éste.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(activitiesByOfferer, HttpStatus.OK);
        }
    }

    /**
     * Extremo de la API que inserta una {@link Activity} en el sistema a partir de los datos introducidos. El campo
     * {@link Activity#getOfferer()} debe existir.
     * @param activityDTO Un objeto JSON con los valores para los campos de la nueva actividad.
     * @return La actividad creada con {@link HttpStatus#OK}, o mensajes de error si los datos no son adecuados ({@link HttpStatus#BAD_REQUEST}).
     * @see ActivityServiceImpl#saveActivity(Activity)
     * @see ActivityDTO
     */
    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody ActivityDTO activityDTO) {
        Activity activity = new Activity(
                activityDTO.getName(),
                activityDTO.getDescription(),
                activityDTO.getDate(),
                activityDTO.getLocation(),
                activityDTO.getPrice(),
                activityDTO.getDuration(),
                activityDTO.getMaxQuota(),
                activityDTO.getAvailableQuota(),
                activityDTO.getRequiredMats(),
                activityDTO.getProvidedMats(),
                activityDTO.getRecommendedFormation(),
                offererService.getOffererById(activityDTO.getOffererId())
        );

        if (offererService.getOffererById(activityDTO.getOffererId()) == null) {
            return new ResponseEntity<>("ERROR: ofertante no encontrado.", HttpStatus.BAD_REQUEST);
        } else {
            Activity nueva = activityService.saveActivity(activity);
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        }
    }

    /**
     * Extremo de la API que actualiza una {@link Activity} ya existente según su ID.
     * @param id El ID de la actividad a actualizar.
     * @param activityDTO Un objeto con los nuevos valores de la actividad.
     * @return La actividad ya actualizada con {@link HttpStatus#OK}, o un error {@link HttpStatus#NOT_FOUND} si no
     * existe una actividad con el ID suministrado.
     * @see ActivityServiceImpl#saveActivity(Activity)
     * @see ActivityDTO
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody ActivityDTO activityDTO) {
        Activity activity = activityService.getActivityById(id);

        if (activity == null) {
            return new ResponseEntity<>("ERROR: no hay actividades con el ID proporcionado.", HttpStatus.NOT_FOUND);
        }

        activity.setName(activityDTO.getName());
        activity.setDescription(activityDTO.getDescription());
        activity.setDate(activityDTO.getDate());
        activity.setLocation(activityDTO.getLocation());
        activity.setPrice(activityDTO.getPrice());
        activity.setDuration(activityDTO.getDuration());
        activity.setMaxQuota(activityDTO.getMaxQuota());
        activity.setAvailableQuota(activityDTO.getAvailableQuota());
        activity.setRequiredMats(activityDTO.getRequiredMats());
        activity.setProvidedMats(activityDTO.getProvidedMats());
        activity.setRecommendedFormation(activityDTO.getRecommendedFormation());

        Activity actualizado = activityService.saveActivity(activity);

        return new ResponseEntity<>(actualizado, HttpStatus.OK);
    }

    /**
     * Extremo de la API que elimina del sistema una {@link Activity} ya existente.
     * @param id El ID de la actividad a eliminar.
     * @return La nueva lista de actividades asociada al ofertante de la actividad eliminada con código {@link HttpStatus#OK},
     * o un error {@link HttpStatus#NOT_FOUND} si no existe una actividad con ese ID.
     * @see ActivityServiceImpl#deleteActivity(Long)
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        Activity activity = activityService.getActivityById(id);

        if (activity == null) {
            return new ResponseEntity<>("ERROR: no hay actividades con el ID proporcionado.", HttpStatus.NOT_FOUND);
        } else {
            activityService.deleteActivity(id);
            return new ResponseEntity<>(activityService.getActivitiesByOfferer(activity.getOfferer().getId()), HttpStatus.OK);
        }
    }
}
