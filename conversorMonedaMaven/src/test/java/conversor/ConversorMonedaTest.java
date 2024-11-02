/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package conversor;

import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author adam
 */
public class ConversorMonedaTest {
    
    public ConversorMonedaTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class ConversorMoneda.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConversorMoneda.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of aumentar method, of class ConversorMoneda.
     */
    @Test
    public void testAumentar() {
        System.out.println("aumentar");
        Number a = null;
        Number b = null;
        BigDecimal expResult = null;
        BigDecimal result = ConversorMoneda.aumentar(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of retirar method, of class ConversorMoneda.
     */
    @Test
    public void testRetirar() {
        System.out.println("retirar");
        Number a = null;
        Number b = null;
        BigDecimal expResult = null;
        BigDecimal result = ConversorMoneda.retirar(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cotizar method, of class ConversorMoneda.
     */
    @Test
    public void testCotizar() {
        System.out.println("cotizar");
        Number a = null;
        Number b = null;
        ConversorMoneda instance = new ConversorMoneda();
        BigDecimal expResult = null;
        BigDecimal result = instance.cotizar(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertir method, of class ConversorMoneda.
     */
    @Test
    public void testConvertir() {
        System.out.println("convertir");
        Number a = null;
        Number b = null;
        ConversorMoneda instance = new ConversorMoneda();
        BigDecimal expResult = null;
        BigDecimal result = instance.convertir(a, b);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
