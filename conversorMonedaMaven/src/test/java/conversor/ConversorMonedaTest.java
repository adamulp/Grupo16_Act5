package conversor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Grupo16
 */
public class ConversorMonedaTest {

    private static ConversorMoneda conversor;

    public ConversorMonedaTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        conversor = new ConversorMoneda();
        System.out.println("Bienvenido al conversor de moneda");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("La operación ha finalizado.");
    }

    @Before
    public void setUp() {
        System.out.println(Thread.currentThread().getStackTrace()[1].getMethodName());
    }

    @After
    public void tearDown() {
        System.out.println("Prueba finalizada, campos en 0");
    }

    @Test
    public void testAumentar() {
        System.out.println("Test aumentar");
        BigDecimal a = new BigDecimal("10.00");
        BigDecimal b = new BigDecimal("5.00");
        BigDecimal expected = new BigDecimal("15.00");
        BigDecimal result = conversor.aumentar(a, b);
        assertEquals(expected, result.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testRetirar() {
        System.out.println("Test retirar");
        BigDecimal a = new BigDecimal("10.00");
        BigDecimal b = new BigDecimal("5.00");
        BigDecimal expected = new BigDecimal("5.00");
        BigDecimal result = conversor.retirar(a, b);
        assertEquals(expected, result.setScale(2, RoundingMode.HALF_UP));
    }
    
    @Test
    public void testCotizar() {
        System.out.println("Test cotizar");
        BigDecimal a = new BigDecimal("1200.00");
        BigDecimal b = new BigDecimal("1.00");
        BigDecimal expected = new BigDecimal("1200.00");
        BigDecimal result = conversor.cotizar(a, b);
        assertEquals(expected, result.setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    public void testConvertir() {
        System.out.println("Test convertir");
        BigDecimal a = new BigDecimal("1200.00");
        BigDecimal b = new BigDecimal("1.00"); // Example conversion rate
        BigDecimal expected = new BigDecimal("1200.00"); // Example value, adjust as needed
        BigDecimal result = conversor.convertir(a, b);
        assertEquals(expected, result.setScale(2, RoundingMode.HALF_UP));
    }
}
