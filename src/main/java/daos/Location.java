package daos;

import org.springframework.stereotype.Component;

import java.util.Objects;

public class Location {
    public long id;
    public String name;
    public double lat;
    public double lon;

    public Location(long id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    public Location(Location otherLocation){
        this.id = otherLocation.id;
        this.name = otherLocation.name;
        this.lat = otherLocation.lat;
        this.lon = otherLocation.lon;
    }

}
