package services;

import daos.LocationDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class LocationsServiceTest2 {
    @Mock
    private LocationDao locationDao;

    @InjectMocks
    private LocationsService locationsService;

    @Test
    public void listLocationsTest() {
        locationsService.listLocations();
        Mockito.verify(locationDao, times(1)).findAll();
    }
}