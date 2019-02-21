package daos;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier("dummyLocationDao")
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
}
