package es.cifpcm.libreriaonline;

import java.io.Serializable;
/**
 * Cliente, con sus atributos y su miembro. Heh.
 * 
 * @author Marcos
 */
public class Cliente implements Serializable {
    private String nombre;
    private Boolean miembro;
    private String tipoMiembro;
    
   public Cliente(String nombre) {
       this.nombre = nombre;
   }
   
   public String getNombre() {
       return nombre;
   }
   
   public Boolean esMiembro() {
       return miembro;
   }
   
   public void setMiembro(Boolean miembro) {
       this.miembro = miembro;
   }
   
   public String getTipoMiembro() {
       return tipoMiembro;
   }
   
   public void setTipoMiembro(String tipoMiembro) {
       this.tipoMiembro = tipoMiembro;
   }
   
   @Override
   public String toString() {
       StringBuffer sb = new StringBuffer(); 
       sb.append("Nombre: ");
       sb.append(nombre);
       sb.append("\nMiembro: ");
       String member = (miembro) ? "Sí" : "No";
       sb.append(member);
       sb.append("\nTipo de miembro: ");
       sb.append(tipoMiembro);
       return sb.toString();
   }
}
