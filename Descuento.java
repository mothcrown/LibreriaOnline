package es.cifpcm.libreriaonline;

/**
 * No sé si me he pasado aquí con lo de los switch, pero para mí tenía sentido!
 * 
 * @author Marcos
 */
public class Descuento {
    static double descuentoServiciosPremium = 0.2;
    static double descuentoServiciosOro = 0.15;
    static double descuentoServiciosPlata = 0.1;
    static double descuentoProductosPremium = 0.1;
    static double descuentoProductosOro = 0.1;
    static double descuentoProductosPlata = 0.1;
    
    public double getDescuentoServicios(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "Premium": descuento = descuentoServiciosPremium;
                            break;
            case "Oro":     descuento = descuentoServiciosOro;
                            break;
            case "Plata":   descuento = descuentoServiciosPlata;
                            break;
        }      
        return descuento;
    }
    
    public double getDescuentoProductos(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "Premium": descuento = descuentoProductosPremium;
                            break;
            case "Oro":     descuento = descuentoProductosOro;
                            break;
            case "Plata":   descuento = descuentoProductosPlata;
                            break;
        }      
        return descuento;
    }
}
