import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi3.JdbiFactory;
import me.atam.planes4sale.H2StubbedDatabase;
import me.atam.planes4sale.JDBIPlaneService;
import me.atam.planes4sale.Plane;
import me.atam.planes4sale.SQLPlaneService;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JDBIPlaneServiceTest {

    JDBIPlaneService planeService ;

    @BeforeClass
    public static void setupDB(){
        H2StubbedDatabase.createTableAndPopulateWithDummyData();
    }

    @Before
    public void setup(){
        H2DatabasePlugin h2DatabasePlugin = new H2DatabasePlugin();
        Jdbi jdbi = Jdbi.create(H2StubbedDatabase.DB_CONNECTION, "", "");
        jdbi.installPlugin(h2DatabasePlugin);
        jdbi.installPlugin(new SqlObjectPlugin());
        this.planeService = jdbi.onDemand(JDBIPlaneService.class);
    }


    @Test
    public void canRetrieveBoeingPlanes() {
        List<Plane> list = planeService.findNameByManufacturer("Boeing");
        assertThat(list.size(), is(1));
        Plane plane = list.get(0);
        assertThat(plane.getId(), is("123"));
        assertThat(plane.getManufacturer(), is("Boeing"));
        assertThat(plane.getManufactureDate(), is(LocalDate.now()));
        assertThat(plane.getImageId(), is("1234.jpg"));
    }

    @Test
    public void canRetrieveBoeingPlanesCaseInsensitive() {



        List<Plane> list = planeService.findNameByManufacturer("BoEinG");
        assertThat(list.size(), is(1));
        Plane plane = list.get(0);
        assertThat(plane.getId(), is("123"));
        assertThat(plane.getManufacturer(), is("Boeing"));
        assertThat(plane.getManufactureDate(), is(LocalDate.now()));
        assertThat(plane.getImageId(), is("1234.jpg"));
    }

    @Test
    public void returnseroRowsWhenNone() {
        List<Plane> planes = planeService.findNameByManufacturer("unknown");
        assertThat(planes.size(), is(0));
    }






}

