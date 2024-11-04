package profesorestest;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juanj
 */
public class Profesor {
    String nombre;
    ArrayList <Materia> materias;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMaterias(ArrayList<Materia> materias) {
        this.materias = materias;
    }

    public String getNombre() {
        return nombre;
    }

    public Profesor(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Profesor{" + "nombre=" + nombre + ", materias=" + materias + '}';
    }
    
    int calcularCarga(ArrayList <Materia> materias){
        int carga =0; 
        for (Materia materia : materias) {
            carga += materia.getCargaHoraria();
        }
        return carga;
    }
     int repartirGrupo(int alumnos, int profesores){
        return alumnos/profesores;
         
    }
 }
