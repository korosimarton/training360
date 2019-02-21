import config.AppConfig;
import daos.Location;
import daos.LocationDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import services.LocationsService;
import services.NameChangeListener;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@ActiveProfiles("dummy")
public class LocationServcieIntegrationTestDummyProfile {

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private Environment env;

    @Test
    public void testActiveProfile(){
            assertEquals("dummy", env.getActiveProfiles()[0]);
    }

}
