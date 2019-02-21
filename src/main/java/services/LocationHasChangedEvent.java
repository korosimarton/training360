package services;

import daos.Location;
import org.springframework.context.ApplicationEvent;

public class LocationHasChangedEvent extends ApplicationEvent {
    private Location oldLocation;
    private Location newLocation;

    public LocationHasChangedEvent(Object source, Location oldLocation, Location newLocation) {
        super(source);
        this.newLocation = newLocation;
        this.oldLocation = oldLocation;
    }

    public Location getOldLocation() {
        return oldLocation;
    }

    public Location getNewLocation() {
        return newLocation;
    }
}
