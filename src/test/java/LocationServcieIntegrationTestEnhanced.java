import daos.Location;
import daos.LocationsRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import config.AppConfig;
import services.LocationsService;
import services.NameChangeListener;
import services.CounterAspect;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("normal")
public class LocationServcieIntegrationTestEnhanced {

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private NameChangeListener nameChangeListener;

    @Autowired
    private CounterAspect counterAspect;

    @Autowired
    private LocationsRepository locationRepository;

    @Before
    public void init(){
        locationRepository.deleteAll();
    }

    @Test
    public void testCreateMethod(){
            locationsService.createLocation("name", 123, 456);
            assertEquals(locationsService.listLocations().size(),1 );

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

    @Test
    public void test5(){
        Location location = new Location(0l,"name",123,456);
        long id = locationsService.createLocation(location.name, location.lat, location.lon);

        String updatedName = "updated";
        double updatedLat = 11;
        double updatedLon = 22;
        locationsService.updateLocation(id,updatedName, updatedLat,updatedLon);
        Assert.assertEquals(updatedName, locationsService.getLocationById(id).name);
        Assert.assertEquals(updatedLat, locationsService.getLocationById(id).lat, 0.0001);
        Assert.assertEquals(updatedLon, locationsService.getLocationById(id).lon, 0.0001);
    }

    // Esemenykezeles feladat
    @Test
    public void test6(){
        nameChangeListener.deleteAll();
        Location location = new Location(0l,"initialName",123,456);
        long id = locationsService.createLocation(location.name, location.lat, location.lon);

        String updatedName = "updatedName";
        double updatedLat = 11;
        double updatedLon = 22;
        locationsService.updateLocation(id,updatedName, updatedLat,updatedLon);
        Assert.assertEquals("[initialName->updatedName]", nameChangeListener.getChanges().toString());
    }

    @Test
    public void testAOP(){
        nameChangeListener.deleteAll();
        System.out.println();
        System.out.println("mapsize before = " + counterAspect.getInitialCountMap().size());
        locationsService.createLocation("Budapest", 123,456);
        locationsService.createLocation("Paris", 123,456);
        locationsService.createLocation("Belgrad", 123,456);
        locationsService.createLocation("Bucharest", 123,456);
        locationsService.createLocation("Pecs", 123,456);
        locationsService.createLocation("Bekescsaba", 123,456);
        locationsService.createLocation("Arad", 123,456);
        System.out.println();
        System.out.println("mapsize after = " + counterAspect.getInitialCountMap().size());
        System.out.println(counterAspect.getInitialCountMap());
    }
}
