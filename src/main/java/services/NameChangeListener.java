package services;

import daos.Location;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class NameChangeListener implements ApplicationListener<LocationHasChangedEvent> {
    private List<String> nameChanges = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void onApplicationEvent(LocationHasChangedEvent locationHasChangedEvent) {
        Location newLocation = locationHasChangedEvent.getNewLocation();
        Location oldLocation = locationHasChangedEvent.getOldLocation();
        if(!newLocation.name.equals(oldLocation.name)){
            nameChanges.add(oldLocation.name + "->" + newLocation.name);
        }
    }

    public void deleteAll(){
        nameChanges.clear();;
    }

    public List<String> getChanges(){
       return new ArrayList<>(nameChanges);
    }
}
