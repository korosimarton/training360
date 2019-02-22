package daos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("listLocationDao")
@Profile({"sql"})
@Primary
public class MySqlLocationDao implements LocationDao {
    private List<Location> locations = Collections.synchronizedList(new ArrayList<Location>());

    private DataSource dataSource;

    public MySqlLocationDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private long cntr;

    @Override
    public List<Location> findAll(){
        return new ArrayList<Location>(locations);
    }

    @Override
    public long save(String name, double lat, double lon){
        try {
            Connection connection = dataSource.getConnection();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot get connection", e);
        }

        return -1;
    }

    @Override
    public Location findById(long id){
        return locations.stream().filter(l -> l.id == id).findFirst().get();
    }

    @Override
    public void delete(long id){
        boolean isElementDeleted = locations.removeIf(l -> l.id == id);
        System.out.println("delete() called with id: " + id);
    }

    @Override
    public void deleteAll(){
        locations.clear();
    }

    @Override
    public void update(long id, String name, double lat, double lon) {
        Location location = locations.stream().filter(l -> l.id == id).findFirst().get();
        location.name = name;
        location.lat = lat;
        location.lon = lon;
    }
}
