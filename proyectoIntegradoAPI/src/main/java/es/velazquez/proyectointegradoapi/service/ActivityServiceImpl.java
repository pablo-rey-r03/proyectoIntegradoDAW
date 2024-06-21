package es.velazquez.proyectointegradoapi.service;

import es.velazquez.proyectointegradoapi.dto.ActivityDTO;
import es.velazquez.proyectointegradoapi.model.Activity;
import es.velazquez.proyectointegradoapi.model.Offerer;
import es.velazquez.proyectointegradoapi.repository.ActivityRepository;
import es.velazquez.proyectointegradoapi.repository.OffererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Override
    public Activity getActivityById(Long id) {
        return activityRepository.findById(id).orElse(null);
    }

    @Override
    public ActivityDTO getActivityDTOById(Long id) {
        Activity activity = activityRepository.findById(id).orElse(null);
        if (activity == null) {
            return null;
        }

        return new ActivityDTO(
                activity.getId(),
                activity.getName(),
                activity.getDescription(),
                activity.getDate(),
                activity.getLocation(),
                activity.getPrice(),
                activity.getDuration(),
                activity.getMaxQuota(),
                activity.getAvailableQuota(),
                activity.getRequiredMats(),
                activity.getProvidedMats(),
                activity.getRecommendedFormation(),
                activity.getOfferer().getId()
        );
    }

    @Override
    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    @Override
    public Activity saveActivity(Activity activity) {
        return activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
    }

    @Override
    public List<Activity> getActivitiesByOfferer(Long offererId) {
        return activityRepository.findByOffererId(offererId);
    }
}
