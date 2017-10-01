/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cifpcm.libreriaonline;

import java.util.*;
import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Paths;
import java.nio.file.Path;


/**
 * Junto a la clase Menu, esto es el motor de la aplicación. Es desde aquí donde
 * guardamos datos en fichero y organizamos la información. La aplicación es
 * un poco Laissez-faire con los usuarios haciendo un poco lo que quieren;
 * una gente lo llama pereza, yo lo llamo web 2.0.
 * 
 * @author Marcos
 */
public class Administracion {
    public ArrayList<Cliente> clientes;
    public ArrayList<Visita> visitas;
    public ArrayList<Libro> libros;
    public Descuento descuentos;
    public Servicio servicios;
    
    /**
     * Creamos las listas de objetos.
     * 
     */
    public Administracion() {
        clientes = bdClientes();
        visitas = logVisitas();
        libros = inventarioLibros();
        descuentos = new Descuento();
        servicios = new Servicio();
    }
    
    /**
     * Crea fichero si no existe, lee si existe.
     * 
     * @return lista
     */
    private ArrayList<Cliente> bdClientes() {
        ArrayList<Cliente> bdclientes = null;
        String archivoClientes = Paths.get(System.getProperty("java.io.tmpdir"), "clientes.dat").toString();
        File archivo = new File(archivoClientes);
        if (archivo.exists()) {
            bdclientes = recuperaClientes(archivoClientes);
        } else {
            bdclientes = creaClientes(archivoClientes);
        }
        return bdclientes;
    }
    
    /**
     * Leemos del archivo y guardamos en un ArrayList<Cliente>
     * 
     * @param archivo
     * @return arraylist<Cliente>
     */
    private ArrayList<Cliente> recuperaClientes(String archivo){
        List<Cliente> clientes1 = null;
        FileInputStream finput = null;
        ObjectInputStream oinput = null;
        
        try {
            finput = new FileInputStream(archivo);
            oinput = new ObjectInputStream(finput);
            clientes1 = (List<Cliente>)oinput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (finput != null)
                    finput.close();
                if (oinput != null)
                    oinput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(clientes1);
    }
    
    /**
     * Me molesta un poco que la aplicación parezca a estrenar, así que he
     * usado a mis compis como conejillos de indias.
     * 
     * @param archivo
     * @return lista clientes
     */
    private ArrayList<Cliente> creaClientes(String archivo) {
        ArrayList<Cliente> clientes1 = new ArrayList<>();
        
        Cliente cliente = new Cliente("Ayoze");
        cliente.setMiembro(true);
        cliente.setTipoMiembro("Oro");
        clientes1.add(cliente);
        
        cliente = new Cliente("Carlos");
        cliente.setMiembro(true);
        cliente.setTipoMiembro("Premium");
        clientes1.add(cliente);
        
        cliente = new Cliente("Alejandro");
        cliente.setMiembro(false);
        clientes1.add(cliente);
        
        grabaClientes(archivo, clientes1);
        return clientes1;
    }
    /**
     * Esta clase se comporta de manera idéntica a las anterior. Y esto es así
     * porque me he limitado a copiar y a pegar. Ole yo. 10/10.
     * 
     * @return 
     */
    private ArrayList<Visita> logVisitas() {
        ArrayList<Visita> logvisitas = null;
        String archivoVisitas = Paths.get(System.getProperty("java.io.tmpdir"), "visitas.dat").toString();
        File archivo = new File(archivoVisitas);
        if (archivo.exists()) {
            logvisitas = recuperaVisitas(archivoVisitas);
        } else {
            logvisitas = creaVisitas(archivoVisitas);
        }
        return logvisitas;
    }
    
    private ArrayList<Visita> recuperaVisitas(String archivo){
        List<Visita> visitas1 = new ArrayList<>();
        FileInputStream finput = null;
        ObjectInputStream oinput = null;
        
        try {
            finput = new FileInputStream(archivo);
            oinput = new ObjectInputStream(finput);
            visitas1 = (List<Visita>)oinput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (finput != null)
                    finput.close();
                if (oinput != null)
                    oinput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(visitas1);
    }
    
    private ArrayList<Visita> creaVisitas(String archivo) {
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        ArrayList<Visita> libroVisitas = new ArrayList<>();
        
        Visita visita = new Visita(clientes.get(0), new Date());
        libroVisitas.add(visita);
        
        grabaVisitas(archivo, libroVisitas);
        return libroVisitas;
    }
    
    private ArrayList<Libro> inventarioLibros() {
        ArrayList<Libro> libreria = null;
        String archivoLibros = Paths.get(System.getProperty("java.io.tmpdir"), "libros.dat").toString();
        File archivo = new File(archivoLibros);
        if (archivo.exists()) {
            libreria = consultaLibreria(archivoLibros);
        } else {
            libreria = creaLibreria(archivoLibros);
        }
        return libreria;
    }
    
    private ArrayList<Libro> consultaLibreria(String archivo){
        List<Libro> libreria = null;
        FileInputStream finput = null;
        ObjectInputStream oinput = null;
        
        try {
            finput = new FileInputStream(archivo);
            oinput = new ObjectInputStream(finput);
            libreria = (List<Libro>)oinput.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (finput != null)
                    finput.close();
                if (oinput != null)
                    oinput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>(libreria);
    }
    
    /**
     * Seamos honestos, solo he metido a Borges para dármelas de interesante.
     * 
     * @param archivo
     * @return 
     */
    private ArrayList<Libro> creaLibreria(String archivo) {
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        ArrayList<Libro> libreria = new ArrayList<>();
        
        Libro libro = new Libro("Drácula", "Bram Stoker", 15);
        libreria.add(libro);
        libro = new Libro("El Aleph", "Jorge Luis Borges", 25);
        libreria.add(libro);
        libro = new Libro("La Estación de la Calle Perdido", "China Mieville", 20);
        libreria.add(libro);
        
        grabaLibros(archivo, libreria);
        return libreria;
    }
    
    public void grabaClientes(String archivo, ArrayList<Cliente> clientes1) {
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        
        try {
            foutput = new FileOutputStream(archivo);
            ooutput = new ObjectOutputStream(foutput);
            ooutput.writeObject(clientes1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (foutput != null)
                    foutput.close();
                if (ooutput != null)
                    ooutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void grabaVisitas(String archivo, ArrayList<Visita> visitas1) {
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        
        try {
            foutput = new FileOutputStream(archivo);
            ooutput = new ObjectOutputStream(foutput);
            ooutput.writeObject(visitas1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (foutput != null)
                    foutput.close();
                if (ooutput != null)
                    ooutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public void grabaLibros(String archivo, ArrayList<Libro> libreria) {
        FileOutputStream foutput = null;
        ObjectOutputStream ooutput = null;
        
        try {
            foutput = new FileOutputStream(archivo);
            ooutput = new ObjectOutputStream(foutput);
            ooutput.writeObject(libreria);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (foutput != null)
                    foutput.close();
                if (ooutput != null)
                    ooutput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    
}
