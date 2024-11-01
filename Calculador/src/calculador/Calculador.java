package calculador;

/**
 *
 * @author Grupo16
 */
import java.math.BigDecimal;
import java.math.BigInteger;

public class Calculador {
    public static void main(String[] args) {
        Calculador calc = new Calculador();
        BigDecimal result1 = calc.sumar(5, 10);
        BigDecimal result2 = calc.sumar(5.5, 4.5);
        BigDecimal result3 = calc.sumar(new BigDecimal("10.5"), new BigDecimal("5.5"));

        System.out.println("Sum (Integer): " + result1);
        System.out.println("Sum (Double): " + result2);
        System.out.println("Sum (BigDecimal): " + result3);

        BigDecimal result4 = calc.restar(20.0, 3);
        System.out.println("Difference: " + result4);
    }

    private BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        } else if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        } else {
            return BigDecimal.valueOf(number.doubleValue());
        }
    }

    public BigDecimal sumar(Number a, Number b) {
        return toBigDecimal(a).add(toBigDecimal(b));
    }

    public BigDecimal restar(Number a, Number b) {
        return toBigDecimal(a).subtract(toBigDecimal(b));
    }

    public BigDecimal dividir(Number a, Number b) {
        BigDecimal divisor = toBigDecimal(b);
        if (divisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return toBigDecimal(a).divide(divisor);
    }

    public BigDecimal multiplicar(Number a, Number b) {
        return toBigDecimal(a).multiply(toBigDecimal(b));
    }
}


