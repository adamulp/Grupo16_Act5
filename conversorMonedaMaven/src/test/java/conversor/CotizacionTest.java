package conversor;

import java.math.BigDecimal;
import org.junit.Test;

public class CotizacionTest {

    private Conversor conversor;

    public CotizacionTest() {
        conversor = new Conversor();
    }

    @Test(expected = ArithmeticException.class)
    public void testCotizarCero() {
        System.out.println("testCotizarCero");
        BigDecimal cotizarARS = new BigDecimal("1000.00");
        BigDecimal cotizarUSD = new BigDecimal("0.00");

        conversor.cotizar(cotizarARS, cotizarUSD);
    }
}
