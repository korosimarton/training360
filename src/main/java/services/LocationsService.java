package services;

import daos.Location;
import daos.LocationDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LocationsService {

    private ApplicationContext applicationContext;

    private LocationDao locationDao;

    private ApplicationEventPublisher applicationEventPublisher;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public LocationsService(ApplicationContext applicationContext, LocationDao locationDao) {
        this.applicationContext = applicationContext;
        this.locationDao = locationDao;
    }

    public List<Location> listLocations() {
        return locationDao.findAll();
    }

    public long createLocation(String name, double lat, double lon) {
        return locationDao.save(name, lat, lon);
    }

    public Location getLocationById(long id) {
        return locationDao.findById(id);
    }

    public void deleteLocation(long id) {
        locationDao.delete(id);
    }

    public Location createLocationTemplate(){
        logger.debug("createLocationTemplate() was called");
        return applicationContext.getBean("templateLocation", Location.class);
    }

    public void updateLocation(long id, String name, double lat, double lon){
        Location oldLocation = new Location(locationDao.findById(id));
        locationDao.update(id, name, lat, lon);
        Location newLocation = new Location(id, name, lat, lon);
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
