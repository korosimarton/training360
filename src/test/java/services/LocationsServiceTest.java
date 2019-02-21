package services;

import daos.ListLocationDao;
import daos.LocationDao;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.times;

public class LocationsServiceTest {

    LocationDao locationDao = Mockito.mock(ListLocationDao.class);
    LocationsService locationsService = new LocationsService(null, locationDao);

    @Test
    public void listLocationsTest() {
        locationsService.listLocations();
        Mockito.verify(locationDao, times(1)).findAll();
    }
}