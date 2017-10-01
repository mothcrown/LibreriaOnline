package es.cifpcm.libreriaonline;


import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.Serializable;
/**
 * No lo he comentado hasta ahora, pero eso de serializar objetos es la vida. 
 * La vida. 
 *
 * @author Marcos
 */

public class Visita implements Serializable{
    private Cliente cliente;
    private Date fecha;
    private double gastoServicios;
    private double gastoProductos;
    
    public Visita(Cliente cliente, Date fecha) {
        this.cliente = cliente;
        this.fecha = fecha;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public double getGastoServicios() {
        return gastoServicios;
    }
    
    public void setGastoServicios(double gastoServicios) {
        this.gastoServicios = gastoServicios;
    }
    
    public double getGastoProductos() {
        return gastoProductos;
    }
    
    public void setGastoProductos(double gastoProductos) {
        this.gastoProductos = gastoProductos;
    }
    
    public double getGastoTotal() {
        double gastoTotal = gastoServicios + gastoProductos;
        return gastoTotal;
    }
    
    @Override
    /**
     * Esto es una chulada, la verdad.
     */
    public String toString() {
        String patronFecha = "dd/MM/yyyy HH:mm:SS";
        SimpleDateFormat formato = new SimpleDateFormat(patronFecha);
        StringBuffer sb = new StringBuffer(); 
        sb.append("\nCliente: ");
        sb.append(cliente);
        sb.append("\nFecha: ");
        sb.append(formato.format(fecha));
        sb.append("\nGasto en servicios: ");
        sb.append(Double.toString(gastoServicios));
        sb.append("\nGasto en productos: ");
        sb.append(Double.toString(gastoProductos));
        sb.append("\nGasto total: ");
        sb.append(Double.toString(getGastoTotal()));
        return sb.toString();
    }
    
}
