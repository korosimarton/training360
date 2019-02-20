package daos;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class LocationDao {
    private List<Location> locations = Collections.synchronizedList(new ArrayList<Location>());

    private long cntr;

    public List<Location> findAll(){
        return new ArrayList<Location>(locations);
    }

    public long save(String name, double lat, double lon){
        locations.add(new Location(cntr, name, lat, lon));
        return cntr++;
    }

    public Location findById(long id){
        return locations.stream().filter(l -> l.id == id).findFirst().get();
    }

    public void delete(long id){
        boolean isElementDeleted = locations.removeIf(l -> l.id == id);
        System.out.println("delete() called with id: " + id);
    }

    public void deleteAll(){
        System.out.println("deleteAll() called");
        locations.clear();
    }
}
