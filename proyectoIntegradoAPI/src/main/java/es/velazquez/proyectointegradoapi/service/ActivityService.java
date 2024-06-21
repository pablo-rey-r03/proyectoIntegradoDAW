package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.dto.ActivityDTO;
import es.velazquez.proyectointegradoapi.model.Activity;

import java.util.List;

public interface ActivityService {
    ActivityDTO getActivityDTOById(Long id);
    Activity getActivityById(Long id);
    List<Activity> getAllActivities();
    Activity saveActivity(Activity activity);
    void deleteActivity(Long id);
    List<Activity> getActivitiesByOfferer(Long offererId);
}
