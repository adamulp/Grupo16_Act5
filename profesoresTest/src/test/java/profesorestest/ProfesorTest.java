package profesorestest;

import java.util.ArrayList;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author juanj
 */
public class ProfesorTest {
    static Profesor profe;
    static ArrayList<Materia> materias;
    public ProfesorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        profe = new Profesor("JuanJo");
        materias =  new ArrayList<>();
        Materia m1 = new Materia ("Ingenieria", 5);
        Materia m2 = new Materia ("Mumuki", 7);
        Materia m3 = new Materia ("Laboratorio", 8);
        materias.add(m1);
        materias.add(m2);
        materias.add(m3);
        profe.setMaterias(materias);
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
     * Test of calcularCarga method, of class Profesor.
     */
    @Test
    public void testCalcularCarga() {
     
        int expResult = 20;
        int result = profe.calcularCarga(materias);
        assertEquals(expResult, result);
        System.out.println("calcular Carga del profesor Juanjo..."+ result );
    }

    /**
     * Test of repartirGrupo method, of class Profesor.
     */
    @Test
    public void testRepartirGrupo() {
        System.out.println("repartirGrupo");
        int alumnos = 38;
        int profesores = 4;
        int expResult = 9;
        int result = profe.repartirGrupo(alumnos, profesores);
        assertEquals(expResult, result);
    
            }
    @Test(expected = ArithmeticException.class)
    public void testNoHayQuienCorrija() {
        System.out.println("NoHayQuienCorrija divide por 0");
        int alumnos = 38;
        int profesores = 0;
        int result = profe.repartirGrupo(alumnos, profesores);
    }
    
}
