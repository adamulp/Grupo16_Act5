package autojunit;

public class AutoJUnit {
    public static void main(String[] args) throws Exception {
           Auto a = new Auto("Bentley", 5000000);
           Auto.impuesto = 20f;
           System.out.println("Bentley Precio Final : "+ a.precioFinal(110f));  // con 10% de desc
           Auto a2 = new Auto("Fiat Mobi", 1200000);
           System.out.println("Auto Fiat baratito " + a2.precioFinal(10f));
           
    }
    
}
