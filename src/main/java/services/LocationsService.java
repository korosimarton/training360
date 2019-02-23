package services;

import daos.AuditLog;
import daos.Location;
import daos.LocationsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class LocationsService {

    private ApplicationContext applicationContext;
    private LocationsRepository locationsRepository;
    private AuditLoggerService auditLoggerService;
    private ApplicationEventPublisher applicationEventPublisher;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public LocationsService(ApplicationContext applicationContext, LocationsRepository locationsRepository,
            AuditLoggerService auditLoggerService) {
        this.applicationContext = applicationContext;
        this.locationsRepository = locationsRepository;
        this.auditLoggerService = auditLoggerService;
    }

    public List<Location> listLocations() {
        return locationsRepository.findAll();
    }

    public long createLocation(String name, double lat, double lon) {
        Location location = locationsRepository.save(new Location(name, lat, lon));
        String logMessage = String.format("Location %s has been saved succefully.",name );
        auditLoggerService.saveAuditLog(new AuditLog(logMessage));
        return location.getId();
    }

    public Location getLocationById(long id) {
        return locationsRepository.findById(id).get();
    }

    public void deleteLocation(long id) {
        Location location = getLocationById(id);
        locationsRepository.delete(location);
    }

    public Location createLocationTemplate(){
        logger.debug("createLocationTemplate() was called");
        return applicationContext.getBean("templateLocation", Location.class);
    }

    //@Transactional --> ezt is rárakhatnánk, akkor nem kéne a save()-t hívogatunk, hanem automatikusan metódus végén lefutna a kommit
    public void updateLocation(long id, String name, double lat, double lon){
        Location location = locationsRepository.findById(id).get(); // opening persistence context
        Location oldLocation = new Location(location);
        location.setId(id);
        location.setName(name);
        location.setLat(lat);
        location.setLon(lon);
        locationsRepository.save(location); // closing down persistence context
        Location newLocation = new Location(location);
        if(applicationEventPublisher != null){
            LocationHasChangedEvent locationHasChangedEvent = new LocationHasChangedEvent(this,oldLocation, newLocation);
            applicationEventPublisher.publishEvent(locationHasChangedEvent);
        }
    }

    @Autowired(required = false)
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
