import config.AppConfig;
import daos.Location;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.LocationsService;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "normal");

        try(AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class))
        {
            LocationsService locationServce = applicationContext.getBean(LocationsService.class);
            locationServce.createLocation("adsf",12,13);
            System.out.println(locationServce.listLocations());

            Location templateLocation = (Location) applicationContext.getBean("templateLocation");
        }
    }
}
