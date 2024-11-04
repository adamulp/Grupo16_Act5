package entidades;

import java.util.ArrayList;
import java.util.List;
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
public class ColaMensajesTest {
 
  ColaMensajes colaLlena3;
     
  @Before
  public void setUp() throws Exception {
    colaLlena3 = new ColaMensajes(3);
    colaLlena3.insertarMensaje("Hola");
    colaLlena3.insertarMensaje("como");
    colaLlena3.insertarMensaje("estas?");
  }
  @Test
  public void testInsertarMensaje() {
    List<String> listaEsperada = new ArrayList<>();
    listaEsperada.add("como");
    listaEsperada.add("estas?");
    listaEsperada.add("bien?");
         
    colaLlena3.insertarMensaje("bien?");
    assertEquals(listaEsperada, colaLlena3.obtenerMensajes());
  }
 
  @Test
  public void testNumMensajes() {
    assertEquals(3, colaLlena3.numMensajes());
  }
 
  @Test
  public void testExtraerMensaje() {
    assertEquals("Hola", colaLlena3.extraerMensaje());
  }
}
