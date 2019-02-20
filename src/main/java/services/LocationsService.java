package services;

import config.AppConfig;
import daos.Location;
import daos.LocationDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class LocationsService {

    private ApplicationContext applicationContext;

    private LocationDao locationDao;

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
            return applicationContext.getBean("templateLocation", Location.class);
    }
}
