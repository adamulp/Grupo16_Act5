/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package autojunit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author juanj
 */
@RunWith(value=Parameterized.class) 
public class AutoTest {
    static Auto instance;
    //public AutoTest() {   }
    static float expResult;  // esperado
        
    public AutoTest(String marca, float precioBase, float expResult) {
        instance = new Auto(marca, precioBase);
        this.expResult = expResult;
    }
   
    @Parameters
    public static Iterable<Object[]> tomarDatos (){
	/*List<Object[]> datos= new ArrayList<>();
	datos.add(new Object[]{"Audi R8", 5000000, 5400000});
        datos.add(new Object[]{"Fiat Mobi", 1200000, 1440000});
        datos.add(new Object[]{"Bentley", 5600000, 6048000}); //5040000 + 1008000
        return datos;*/
        return Arrays.asList(new Object[][] {{"Audi R8", 5000000, 5400000},{"Fiat Mobi", 1200000, 1440000},{"Bentley", 5600000, 6048000}});
}   
    @BeforeClass
    public static void setUpClass() {
        System.out.println("BeforeClass...");
        //instance = new Auto("Audi R8", 5600000);
        Auto.impuesto = 20f;
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("AfterClass...");
    }
    
    @Before
    public void setUp() {
        System.out.println("Before()");
    }
    
    @After
    public void tearDown() {
        System.out.println("After()");
    }

    /**
     * Test of precioFinal method, of class Auto.
     */
    @Test
    public void testPrecioFinal() throws Exception {
        System.out.println("precio Alta Gama");
        float descuento = 10.0F;
       // Auto instance = new Auto("Audi R8", 5600000);
       // Auto.impuesto = 20f;       
       // float expResult = 6048000;  // esperado
        float result = instance.precioFinal(descuento); // real
        assertEquals(expResult, result, 0.0002);
    }
    @Test
    public void testPrecioFinalBarato() throws Exception {
        System.out.println("precio Vehiculo convencional");
        float descuento = 10.0F;
      // Auto instance = new Auto("Fiat Mobi", 1200000);
      // Auto.impuesto = 20f;
      // float expResult = 1440000;  // esperado
        float result = instance.precioFinal(descuento); // real
        assertEquals(expResult, result, 0.0001);   // millonesima diferencia
    }
    @Test(expected = Exception.class)
    public void testExepcion() throws Exception  {
        System.out.println("Pueba de exeption en descuento");
        float descuento = 200.0F;
      // Auto instance = new Auto("Ford Fiesta", 1800000);
      // Auto.impuesto = 20f;
        instance.precioFinal(descuento);
    }
    
}
