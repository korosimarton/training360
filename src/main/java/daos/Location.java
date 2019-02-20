package daos;

import java.util.Objects;

public class Location {
    public Long id;
    public String name;
    public double lat;
    public double lon;

    public Location(Long id, String name, double lat, double lon) {
        this.id = id;
        this.name = name;
        this.lat = lat;
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.lat, lat) == 0 &&
                Double.compare(location.lon, lon) == 0 &&
                Objects.equals(id, location.id) &&
                Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lat, lon);
    }
}
