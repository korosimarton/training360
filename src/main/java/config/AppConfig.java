package config;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import daos.Location;
import daos.LocationsRepository;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import services.CounterAspect;
import services.LocationsService;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackageClasses = {LocationsService.class, Location.class, CounterAspect.class,
        LocationsRepository.class})
@PropertySource({"classpath:/configuration.properties", "classpath:/application.properties"})
@EnableAspectJAutoProxy
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "daos")
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
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter hibernateJpaVendorAdapter =
                new HibernateJpaVendorAdapter();
        hibernateJpaVendorAdapter.setShowSql(true);
        return hibernateJpaVendorAdapter;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
                new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setPackagesToScan("daos");
        return entityManagerFactoryBean;
    }

    @Bean
    public DataSource dataSource(){
        MysqlDataSource mySqlDataSource = new MysqlDataSource();
        mySqlDataSource.setURL(env.getProperty("jdbc.connectionString"));
        mySqlDataSource.setUser(env.getProperty("jdbc.username"));
        mySqlDataSource.setPassword(env.getProperty("jdbc.password"));
        return mySqlDataSource;
    }

    @Bean
    public Flyway flyway(){
//        Flyway flyway = new Flyway();
//        flyway.setDataSource(dataSource());
        Flyway flyway = Flyway.configure().dataSource(dataSource()).load();
        flyway.migrate();
        return flyway;
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
