import me.atam.planes4sale.H2StubbedDatabase;
import me.atam.planes4sale.JDBIPlaneService;
import me.atam.planes4sale.Plane;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.h2.H2DatabasePlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static me.atam.planes4sale.H2StubbedDatabase.KNOWN_PLANE_SELLER_EMAIL_ADDRESS;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class JDBIPlaneServiceTest {

    JDBIPlaneService planeService ;

    @BeforeClass
    public static void setupDB(){
        H2StubbedDatabase.resetAndRecreatePlanesDatabase();
    }

    @Before
    public void setup(){
        H2DatabasePlugin h2DatabasePlugin = new H2DatabasePlugin();
        Jdbi jdbi = Jdbi.create(H2StubbedDatabase.DB_CONNECTION, H2StubbedDatabase.DB_USER, H2StubbedDatabase.DB_PASSWORD);
        jdbi.installPlugin(h2DatabasePlugin);
        jdbi.installPlugin(new SqlObjectPlugin());
        this.planeService = jdbi.onDemand(JDBIPlaneService.class);
    }

    @Test
    public void canRetrievePlanes() {
        List<Plane> list = planeService.findNameByManufacturer("Boeing");
        assertThat(list.size(), is(2));
        assertThat(list.get(0).getManufacturer(), is("Boeing"));
        assertThat(list.get(1).getManufacturer(), is("Boeing"));
    }

    @Test
    public void canRetrieveBoeingPlanesCaseInsensitive() {
        List<Plane> list = planeService.findNameByManufacturer("BoEinG");
        assertThat(list.size(), is(2));
        assertThat(list.get(0).getManufacturer(), is("Boeing"));
        assertThat(list.get(1).getManufacturer(), is("Boeing"));

    }

    @Test
    public void returnsZeroRowsWhenNone() {
        List<Plane> planes = planeService.findNameByManufacturer("unknown");
        assertThat(planes.size(), is(0));
    }

    @Test
    public void canRetrievePlaneSellersEmail() {
        String planeSellersEmail = planeService.getPlaneSellersEmail(H2StubbedDatabase.KNOWN_PLANE_1.getId());
        assertThat(planeSellersEmail, is(KNOWN_PLANE_SELLER_EMAIL_ADDRESS));
    }

}

