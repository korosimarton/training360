package config;

import daos.ListLocationDao;
import daos.Location;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import services.LocationsService;

@Configuration
@ComponentScan(basePackageClasses = {ListLocationDao.class, LocationsService.class, Location.class})
public class AppConfig {
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
        return new Location(0l, "Choose", 47.50, 19.05);
    }
}
