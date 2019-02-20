import config.AppConfig;
import daos.LocationDao;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import services.LocationsService;

public class Main {
    public static void main(String[] args) {
        try(AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class))
        {
            LocationDao locationDao = (LocationDao)applicationContext.getBean("locationDao");
            LocationsService locationServce = applicationContext.getBean(LocationsService.class);
            locationServce.createLocation("adsf",12,13);
            System.out.println(locationServce.listLocations());
        }
    }
}
