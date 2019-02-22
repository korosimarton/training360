package daos;

import com.mysql.jdbc.Statement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Profile({"normal"})
public class JdbcLocationsDao implements LocationDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcLocationsDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Location> findAll() {
        return jdbcTemplate.query("select * from locations",new LocationRowMapper());
    }

    @Override
    public long save(String name, double lat, double lon) {
        //jdbcTemplate.update("insert into locations(name, lat, lon) values (?, ?, ?)", name, lat, lon);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into locations(name, lat, lon) values (?, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, name);
                preparedStatement.setDouble(2, lat);
                preparedStatement.setDouble(3, lon);
                return preparedStatement;
            }
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    @Override
    public Location findById(long id) {
        return (Location) jdbcTemplate.queryForObject("select * from locations where id = ?", new LocationRowMapper(), id);
    }

    @Override
    public void delete(long id) {
        jdbcTemplate.update("delete from locations where id=?", id);
    }

    @Override
    public void deleteAll() {
        jdbcTemplate.update("delete from locations");
    }

    @Override
    public void update(long id, String name, double lat, double lon) {
        jdbcTemplate.update("update locations set name=?, lat=?, lon=? where id=?", name, lat, lon, id);
    }
}
