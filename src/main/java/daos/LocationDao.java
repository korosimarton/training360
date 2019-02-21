package daos;

import java.util.List;

public interface LocationDao {
    List<Location> findAll();

    long save(String name, double lat, double lon);

    Location findById(long id);

    void delete(long id);

    void deleteAll();
}
