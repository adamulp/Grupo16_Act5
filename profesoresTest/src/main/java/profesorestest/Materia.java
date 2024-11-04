package profesorestest;

/**
 *
 * @author juanj
 */
public class Materia {
    private String nombre;
    private int cargaHoraria;

    Materia(String nombre, int i) {
        this.nombre = nombre;
        this.cargaHoraria = i;
    }

    @Override
    public String toString() {
        return "Materia{" + "nombre=" + nombre + ", cargaHoraria=" + cargaHoraria + '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
    
}
