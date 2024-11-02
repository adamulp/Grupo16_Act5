package conversor;

/**
 *
 * @author Grupo16
 */
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class ConversorMoneda {
    private int precision;
    private static final int DEFAULT_PRECISION = 10;
    
    public ConversorMoneda(){
        this.precision = DEFAULT_PRECISION;
    }
    
    public static void main(String[] args) {
        ConversorMoneda calc = new ConversorMoneda();
        BigInteger saldo = BigInteger.valueOf(5);
        BigDecimal result1 = calc.aumentar(saldo, 10);
        System.out.println("saldo=" + saldo);
        BigDecimal result2 = calc.aumentar(5.5, 4.5);
        BigDecimal result3 = calc.aumentar(new BigDecimal("10.5"), new BigDecimal("5.5"));

        System.out.println("Sum (Integer): " + result1);
        System.out.println("Sum (Double): " + result2);
        System.out.println("Sum (BigDecimal): " + result3);

        BigDecimal result4 = calc.retirar(20.0, 3);
        System.out.println("Difference: " + result4);
    }

    private static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        } else {
            return BigDecimal.valueOf(number.doubleValue());
        }
    }

    public static BigDecimal aumentar(Number a, Number b) {
        return toBigDecimal(a).add(toBigDecimal(b));
    }

    public static BigDecimal retirar(Number a, Number b) {
        return toBigDecimal(a).subtract(toBigDecimal(b));
    }

    public static BigDecimal cotizar(Number a, Number b) {
        BigDecimal divisor = toBigDecimal(b);
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return toBigDecimal(a).divide(divisor, DEFAULT_PRECISION, RoundingMode.HALF_UP);
    }

    public static BigDecimal convertir(Number a, Number b) {
        return toBigDecimal(a).multiply(toBigDecimal(b));
    }
}


