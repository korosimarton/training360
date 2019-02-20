package config;

import daos.LocationDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import services.LocationsService;

@Configuration
public class AppConfig {
    @Bean
    public LocationDao locationDao(){
        System.out.println("debug");
        return new LocationDao();
    }

    @Bean
    public LocationsService locationsService(){
        return new LocationsService(locationDao());
//        return new LocationsService(new LocationDao());       // ?
    }
}
