package profesorestest;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(value=Parameterized.class)
public class ProfesorTest1 {
    static Profesor profe;
    static ArrayList<Materia> materias;
    static int expResult;
    
    public ProfesorTest1(String nombre, ArrayList<Materia> mater, int expected) {
        profe = new Profesor(nombre);
        System.out.println(profe.getNombre());
        materias=mater;
        expResult = expected;
    }
    
    @BeforeClass
    @SuppressWarnings("empty-statement")
    public static void setUpClass() {
        System.out.println("Profe: ");
    }
    
    @Parameterized.Parameters
    public static Iterable<Object[]> tomarDatos (){
        List<Object[]> datos= new ArrayList<>();
        
        ArrayList<Materia> materCris= new ArrayList<>();
        ArrayList<Materia> materPedro= new ArrayList<>();
        Materia m1 = new Materia ("Gestion de datos", 5);
        Materia m2 = new Materia ("Mumuki", 7);
        Materia m3 = new Materia ("Laboratorio", 8);
        Materia m4 = new Materia ("Base de datos", 6);
        materCris.add(m1);
        materCris.add(m2);
        materPedro.add(m3);
        materPedro.add(m4);
	datos.add(new Object[]{"Cristina", materCris, 12});
        datos.add(new Object[]{"Pedro", materPedro, 14});
        return datos;
    }   
    @AfterClass
    public static void tearDownClass() {
        System.out.println("PAREMETERIZED - Carga horaria...");
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     /**
     * Test of calcularCarga method, of class Profesor.
     */
    @Test
    public void testCalcularCarga() {
        //int expResult = 20;
        int result = profe.calcularCarga(materias);
        assertEquals(expResult, result);
        System.out.println(" "+ result);
    }
       
}
