import config.AppConfig;
import daos.Location;
import org.junit.AfterClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import services.LocationsService;

import static org.junit.Assert.assertEquals;

public class LocationServcieIntegrationTest {

    private static AnnotationConfigApplicationContext applicationContext;

    static{
        applicationContext =  new AnnotationConfigApplicationContext();
        applicationContext.getEnvironment().setActiveProfiles("normal");
        applicationContext.register( AppConfig.class);
        applicationContext.refresh();
    }

    @AfterClass
    public static void tearDown(){
        applicationContext.close();
    }

    @Test
    public void testAllMethods(){
            LocationsService locationsService = applicationContext.getBean(LocationsService.class);
            System.out.println(applicationContext.getEnvironment().getActiveProfiles());
            Location location = new Location(0l,"name",123,456);
            locationsService.createLocation(location.name, location.lat, location.lon);
            assertEquals(location.id,locationsService.listLocations().get(0).id);
    }


}
