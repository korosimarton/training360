package services;

import daos.Location;
import daos.LocationDao;

import java.util.ArrayList;
import java.util.List;

public class LocationsService {
    private LocationDao locationDao;

    public LocationsService(LocationDao locationDao) {
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
}
