import daos.Location;
import daos.LocationDao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import config.AppConfig;
import services.LocationsService;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class LocationServcieIntegrationTestEnhanced {

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private LocationDao locationDao;

    @Before
    public void init(){
        locationDao.deleteAll();
    }

    @Test
    public void testCreateMethod(){
            Location location = new Location(0l,"name",123,456);
            locationsService.createLocation(location.name, location.lat, location.lon);
            assertEquals(location,locationsService.listLocations().get(0));

    }

    @Test
    public void test2(){
        Location location = new Location(0l,"name",123,456);
        long id = locationsService.createLocation(location.name, location.lat, location.lon);
        locationsService.deleteLocation(id);
        assertEquals(0,locationsService.listLocations().size());
    }

    @Test
    public void test3(){
        Location location = new Location(0l,"name",123,456);
        locationsService.createLocation(location.name, location.lat, location.lon);
        Location locationReturned = locationsService.getLocationById(location.id );
        Assert.assertEquals(location, locationReturned);
    }

}
