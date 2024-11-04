package entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanj
 */
public class ColaMensajes {
    List<String> cola;
    ColaMensajes(int i) {
          cola = new ArrayList<>(i);
    }

    @Override
    public String toString() {
        return "ColaMensajes{" + "cola=" + cola + '}';
    }

    void insertarMensaje(String msj) {
        if(cola.size()==3)
            cola.remove(0);
        cola.add(msj);
    }

    List<String> obtenerMensajes() {
      return cola; 
    }

    int numMensajes() {
        return cola.size();
    }

    String extraerMensaje() {
        return cola.get(0);
    }
    
}
