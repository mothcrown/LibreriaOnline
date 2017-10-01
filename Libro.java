package es.cifpcm.libreriaonline;

import java.io.Serializable;

/**
 *  Nada especial que ver. Por cierto, no he puesto ningún método para añadir
 *  más libros. Debería de haberlo hecho. Hmmm.
 *
 * @author Marcos
 */
public class Libro implements Serializable{
    private String titulo;
    private String autor;
    private double precio;
    
    public Libro(String titulo, String autor, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }
    
    public String getTitulo() {
       return titulo;
   }
   
   public void setTitulo(String titulo) {
       this.titulo = titulo;
   }
   
   public String getAutor() {
       return autor;
   }
   
   public void setAutor(String autor) {
       this.autor = autor;
   }
   
   public double getPrecio() {
        return precio;
    }
    
    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
