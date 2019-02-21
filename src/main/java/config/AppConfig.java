package config;

import daos.ListLocationDao;
import daos.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import services.LocationsService;

@Configuration
@ComponentScan(basePackageClasses = {ListLocationDao.class, LocationsService.class, Location.class})
@PropertySource("classpath:/configuration.properties")
public class AppConfig {

    @Autowired
    private Environment env;

//    @Bean
//    public LocationDao locationDao(){
//        System.out.println("debug");
//        return new LocationDao();
//    }
//
//    @Bean
//    public LocationsService locationsService(ApplicationContext applicationContext){
//        return new LocationsService(applicationContext, locationDao());
//    }
//

    /*
        Ezt csak Java-configból tudjuk megoldani (annotation based configból nem tudnánk megcsinálni! :) )
     */
    @Bean("templateLocation")
    @Scope("prototype")
    public Location location(){
        String name = env.getProperty("template.location.name");
        double lat = Double.parseDouble(env.getProperty("template.location.lat"));
        double lon = Double.parseDouble(env.getProperty("template.location.lon"));
        return new Location(0l, name, lat, lon);
    }
}
