package daos;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationRowMapper implements RowMapper
{

    public Location mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        double lat = resultSet.getDouble("lat");
        double lon = resultSet.getDouble("lon");
        return new Location(id, name, lat, lon);
    }

}
