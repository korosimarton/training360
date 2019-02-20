package services;

import daos.LocationDao;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;

public class LocationsServiceTest {

    LocationDao locationDao = Mockito.mock(LocationDao.class);
    LocationsService locationsService = new LocationsService(locationDao);

    @Test
    public void listLocationsTest() {
        locationsService.listLocations();
        Mockito.verify(locationDao, times(1)).findAll();
    }
}