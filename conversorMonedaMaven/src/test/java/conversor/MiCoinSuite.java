package conversor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author adam
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(
        {
            conversor.DolaritosTest.class,
            conversor.ConversorTest.class,
            conversor.CotizacionTest.class
        }
)

public class MiCoinSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
