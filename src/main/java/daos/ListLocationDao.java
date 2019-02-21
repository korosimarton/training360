package daos;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
@Qualifier("listLocationDao")
public class ListLocationDao implements LocationDao {
    private List<Location> locations = Collections.synchronizedList(new ArrayList<Location>());

    private long cntr;

    @Override
    public List<Location> findAll(){
        return new ArrayList<Location>(locations);
    }

    @Override
    public long save(String name, double lat, double lon){
        locations.add(new Location(cntr, name, lat, lon));
        return cntr++;
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
        System.out.println("deleteAll() called");
        locations.clear();
    }
}
