/*
 JUnit version: 5.6.0 (Shift+F6 desde syscheck/JunitVersionCheck)
 */
package conversor;

import conversor.ConversorMoneda;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Grupo16
 */
public class ConversorTest {
    
    public ConversorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of main method, of class Calculador.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConversorMoneda.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
