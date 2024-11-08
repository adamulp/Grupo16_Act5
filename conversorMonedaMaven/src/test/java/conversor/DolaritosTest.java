package conversor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(Parameterized.class)
public class DolaritosTest {

    private BigDecimal cotizarARS;
    private BigDecimal cotizarUSD;
    private String resultadoEsperado;

    public DolaritosTest(BigDecimal cotizarARS, BigDecimal cotizarUSD, String expectedResult) {
        this.cotizarARS = cotizarARS;
        this.cotizarUSD = cotizarUSD;
        this.resultadoEsperado = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> tomarDatos() {
        return Arrays.asList(new Object[][] {
            { new BigDecimal("180000"), new BigDecimal("1500"), "120" },
            { new BigDecimal("120000"), new BigDecimal("0"), "error" },
            { new BigDecimal("100000"), new BigDecimal("-900"), "111.11" }
        });
    }

    @Test
    public void testCotizarCero() {
        System.out.println("Testing cotizar with ARS: " + cotizarARS + " and USD: " + cotizarUSD);
        if (resultadoEsperado.equals("error")) {
            try {
                ConversorMoneda cotizador = new ConversorMoneda();
                cotizador.cotizar(cotizarARS, cotizarUSD);
                fail("Expected an ArithmeticException to be thrown.");
            } catch (ArithmeticException e) {
                System.out.println("Caught expected ArithmeticException: " + e.getMessage());
            }
        } else {
            ConversorMoneda cotizador = new ConversorMoneda();
            BigDecimal result = cotizador.cotizar(cotizarARS, cotizarUSD);
            assertEquals(new BigDecimal(resultadoEsperado), result);
        }
    }
}
