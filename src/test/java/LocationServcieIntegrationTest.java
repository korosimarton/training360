import config.AppConfig;
import daos.Location;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.LocationsService;

import static org.junit.Assert.assertEquals;

public class LocationServcieIntegrationTest {

    private static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @AfterClass
    public static void tearDown(){
        applicationContext.close();
    }

    @Test
    public void testAllMethods(){
            LocationsService locationsService = applicationContext.getBean(LocationsService.class);
            Location location = new Location(0l,"name",123,456);
            locationsService.createLocation(location.name, location.lat, location.lon);
            assertEquals(location,locationsService.listLocations().get(0));
    }


}
