package daos;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("dummyLocationDao")
@Profile("dummy")
public class DummyLocationDao implements LocationDao {
    @Override
    public List<Location> findAll() {
        return null;
    }

    @Override
    public long save(String name, double lat, double lon) {
        return 0;
    }

    @Override
    public Location findById(long id) {
        return null;
    }

    @Override
    public void delete(long id) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void update(long id, String name, double lat, double lon) {

    }
}
