package config;

import daos.Location;
import daos.LocationDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import services.LocationsService;

@Configuration
public class AppConfig {
    @Bean
    public LocationDao locationDao(){
        System.out.println("debug");
        return new LocationDao();
    }

    @Bean
    public LocationsService locationsService(ApplicationContext applicationContext){
        return new LocationsService(applicationContext, locationDao());
    }

    @Bean("templateLocation")
    @Scope("prototype")
    public Location location(){
        return new Location(0l, "Choose", 47.50, 19.05);
    }
}
