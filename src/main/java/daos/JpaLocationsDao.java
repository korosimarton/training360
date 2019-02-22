package daos;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Profile({"normal"})
public class JpaLocationsDao implements LocationDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    @Transactional
    public List<Location> findAll() {
        TypedQuery<Location> query =
                entityManager.createQuery("select l from locations l order by l.name", Location.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public long save(String name, double lat, double lon) {
        Location location = new Location(name, lat, lon);
        entityManager.persist(location);
        entityManager.flush();
        return location.getId();
    }

    @Override
    @Transactional
    public Location findById(long id) {
        return entityManager.find(Location.class, id);
    }

    @Override
    @Transactional
    public void delete(long id) {
        entityManager.createQuery("delete from locations l where l.id = :id").setParameter("id",id).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteAll() {
        entityManager.createQuery("delete from locations").executeUpdate();
    }

    @Override
    @Transactional
    public void update(long id, String name, double lat, double lon) {
        entityManager.createQuery("update locations set id = :id, name = :name, lat = :lat, lon = :lon")
                .setParameter("id",id).setParameter("name", name).setParameter("lat", lat)
                    .setParameter("lon",lon).executeUpdate();
    }
}
