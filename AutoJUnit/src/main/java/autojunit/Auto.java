package autojunit;

public class Auto {
    String modelo;
    float precioBase;
    static float impuesto;  // porcentaje

    public Auto(String modelo, float precioBase) {
        this.modelo = modelo;
        this.precioBase = precioBase;
    }

    @Override
    public String toString() {
        return "Auto{" + "modelo=" + modelo + ", precioBase=" + precioBase + '}';
    }
    
    public float precioFinal(float descuento) throws Exception{
        float precioConDescuento = this.precioBase;
        if(descuento<0 || descuento>100){
            throw new RuntimeException("Descuento erroneo");
        }
        if (precioBase >1500000)
            precioConDescuento = this.precioBase - (this.precioBase*descuento)/100f; //0.1
        
        return precioConDescuento+precioConDescuento*this.impuesto/100f; //0.2
    }               //4.500.000 + 900.000     1.200.000 + 240.000

    public void setPrecioBase(float precioBase) {
        this.precioBase = precioBase;
    }
}
