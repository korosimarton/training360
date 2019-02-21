package config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import daos.ListLocationDao;
import daos.Location;
import daos.MySqlLocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import services.CounterAspect;
import services.LocationsService;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {ListLocationDao.class, LocationsService.class, Location.class, CounterAspect.class, MySqlLocationDao.class})
@PropertySource({"classpath:/configuration.properties", "classpath:/application.properties"})
@EnableAspectJAutoProxy
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

    @Bean
    public DataSource dataSource(){
        MysqlDataSource mySqlDataSource = new MysqlDataSource();
        mySqlDataSource.setURL(env.getProperty("jdbc.connectionString"));
        mySqlDataSource.setUser(env.getProperty("jdbc.username"));
        mySqlDataSource.setPassword(env.getProperty("jdbc.password"));
        return mySqlDataSource;
    }

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
