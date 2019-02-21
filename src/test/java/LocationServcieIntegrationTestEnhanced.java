import daos.Location;
import daos.LocationDao;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    @Qualifier("listLocationDao")
    private LocationDao locationDao;

    @Before
    public void init(){
        locationDao.deleteAll();
    }

    @Test
    public void testCreateMethod(){
            Location location = new Location(0l,"name",123,456);
            locationsService.createLocation(location.name, location.lat, location.lon);
            assertEquals(location.id,locationsService.listLocations().get(0).id);

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
        long id = locationsService.createLocation("name", 123,456);
        Location locationReturned = locationsService.getLocationById(id );
        Assert.assertEquals(id, locationReturned.id);
    }

    @Test
    public void test4(){
        Location location1 = locationsService.createLocationTemplate();
        Location location2 = locationsService.createLocationTemplate();
        System.out.println(location1);
        System.out.println(location2);
        Assert.assertNotEquals(location1,location2);
    }


}
