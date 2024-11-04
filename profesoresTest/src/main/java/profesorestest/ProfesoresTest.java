package profesorestest;

import java.util.ArrayList;

/**
 *
 * @author juanj
 */
public class ProfesoresTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Profesor profe = new Profesor("JuanJo");
        ArrayList<Materia> asignaturas =  new ArrayList<>();
        Materia m1 = new Materia ("Ingenienieria", 5);
        Materia m2 = new Materia ("Mumuki", 7);
        Materia m3 = new Materia ("Laboratorio", 8);
        asignaturas.add(m1);
        asignaturas.add(m2);
        asignaturas.add(m3);
        profe.setMaterias(asignaturas);
        System.out.println("Profesor" + profe);
        System.out.println("Carga horaria: "+ profe.calcularCarga(asignaturas));
        System.out.println("Cantidad a corregir: " + profe.repartirGrupo(38, 4));
    }
    
}
