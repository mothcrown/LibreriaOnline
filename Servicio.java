package es.cifpcm.libreriaonline;

/**
 * Los clientes pueden comprar/renovar membres�a. Quer�a hacer una comparaci�n 
 * entre la membres�a vieja del cliente y la nueva para que se quedase tan con
 * la mejor entre ambas. Sin embargo puede darse el caso de alguien que
 * tiene membres�a de Oro y quiere pasarse a Plata.
 * 
 * Es igualmente probable que est� pensando demasiado c�mo funcionar�a esta
 * hipot�tica librer�a online. Meh.
 *
 * @author Marcos
 */
public class Servicio {
    static double precioSuscripcionMensual = 30;
    static double precioSuscripcionTrimestral = 75;
    static double precioSuscripcionAnual = 180;
    static double miembroPremium = 50;
    static double miembroOro = 20;
    static double miembroPlata = 10;
    
    public double getPreciosServicio(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "mensual":     descuento = precioSuscripcionMensual;
                                break;
            case "trimestral":  descuento = precioSuscripcionTrimestral;
                                break;
            case "anual":       descuento = precioSuscripcionAnual;
                                break;
        }      
        return descuento;
    }
    
    public double getPreciosMembresia(String tipo) {
        double descuento = 0;
        switch(tipo) {
            case "Premium": descuento = miembroPremium;
                            break;
            case "Oro":     descuento = miembroOro;
                            break;
            case "Plata":   descuento = miembroPlata;
                            break;
        }      
        return descuento;
    }
}
