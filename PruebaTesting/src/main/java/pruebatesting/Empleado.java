package pruebatesting;
public class Empleado{
     //Constantes
    public final static double SUELDO=600;
    // Indica que la edad de un empleado es mayor que otro
    public final static int MAYOR=1;
    // Indica que las edades de los empleados son iguales
    public final static int IGUAL=0;
    // Indica que la edad de un empleado es menor que otro
    public final static int MENOR=-1;
 
    //Atributos
    private String nombre;
    private String apellido;
    private int edad;
    private double salario;
    public String getNombre() { 
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public int getEdad() {
        return edad;
    }
    public double getSalario() {
        return salario;
    }
    /**
     * Suma un plus si el empleado tiene mas de 40 años
     * @param sueldoPlus
     * @return true si se realiza y false si no se realiza
     */
    public boolean plus (double sueldoPlus){
        boolean aumento=false;
        if (edad>=40){
            salario+=sueldoPlus;
            aumento=true;
        }
        return aumento;
    }
    /**
     * Indica si dos empleados son iguales segun su nombre y apellido
     * @param a empleado a comparar
     * @return true si son iguales y false si no lo son
     */
    public boolean equals (Empleado a){
     if(a.getNombre().equals(nombre) && a.getApellido().equals(apellido)){
            return true;
        }else{
            return false;
        }
    }
    /**
     * Indica si un empleado es mayor, igual o menor que otro segun la edad
     * @param a empleado a comparar
     * @return 1: mayor, 0: iguales y -1 menor que el empleado comparado
     */
    public int compareTo(Empleado a){
            int estado=MENOR;
            if(this.edad==a.getEdad()){
                estado=IGUAL; //Los objetos son iguales
            }else if(this.edad > a.getEdad()){
                estado=MAYOR; //El objeto 1 es mayor que la pasada por parametro
            }
            return estado;
    }
    /**
     * Devuelve el estado del empleado
     * @return estado del empleado
     */
    public String toString (){
        String mensaje="El empleado se llama "+nombre+" "+apellido+" con "+edad+" años " +
                "y un salario de "+salario;
        return mensaje;
    }
    //Constructores
    public Empleado(){
        this ("", "", 0, SUELDO);
    }
    public Empleado(String nombre, String apellido){
        this (nombre, apellido, 0, SUELDO);
    }
    public Empleado (String nombre, String apellido, int edad){
        this (nombre, apellido, edad, SUELDO);
    }
      public Empleado(String nombre, String apellido, int edad, double salario){
        this.nombre=nombre;
        this.apellido=apellido;
        this.edad=edad;
        this.salario=salario;
    }
}

