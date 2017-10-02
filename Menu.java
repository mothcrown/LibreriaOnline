package es.cifpcm.libreriaonline;

import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Date;

/**
 * ¡Apenas tuve tiempo para trabajar en esto, pero la cosa no ha quedado nada
 * mal! (Bueno, tampoco exageremos, hay algún que otro método que es para
 * darle de collejas).
 * 
 *
 * @author Marcos
 */
public class Menu {
    
    private Visita visita;
    private Administracion admin;
    
    public Menu() {
        String usuario = "invitado";
        admin = new Administracion();
        visita = null;
        
        do {
            usuario = mostrarMenu(usuario);
        } while (!usuario.equals("SALIR"));
    }
    
    private String mostrarMenu(String usuario) {
        int modo = menuTexto(usuario);
        Scanner sc = new Scanner(System.in);
        System.out.printf("Introduzca opción: ");
        int op = validaOpcion(sc.nextLine(), false);
        String user = (op >= 0) ? opcionesMenu(op, modo) : usuario;
        return user;
    }
    
    private int menuTexto(String usuario) {
        int valor = -1;
        switch(usuario) {
            case "invitado":    menuInvitado();
                                valor = 0;
                                break;
            case "cliente":     menuCliente();
                                valor = 1;
                                break;
//            case "compra":      menuCompra();
//                                valor = 2;
//                                break;
            default:            break;
        }
        return valor;
    }
    
    public void menuInvitado(){
        System.out.print("\n******** LIBRERÍA ONLINE DAWRIO ********\n");
        System.out.print("-- La pasión por leer.\n");
        System.out.print("1.\tCatálogo de libros\n");
        System.out.print("2.\tCatálogo de servicios\n");
        System.out.print("0.\tSalir\n");
        System.out.print("--------------------------------\n");
        System.out.print("9.\tIngresar\n");
    }
    
    public void menuCliente(){
        System.out.print("\n******** LIBRERÍA ONLINE DAWRIO ********\n");
        System.out.print("-- ¡Bienvenido, " + visita.getCliente().getNombre() + "!\n");
        System.out.print("1.\tComprar libros\n");
        System.out.print("2.\tComprar servicios\n");
        System.out.print("0.\tLog out\n");
        System.out.print("--------------------------------\n");
        System.out.print("9.\tMi perfil\n");
        
    }
    
    public String opcionesMenu(int op, int modo) {
        String usuario = "invitado";
        switch(modo) {
            case 0:     usuario = opcionesInvitado(op);
                        break;
            case 1:     usuario = opcionesCliente(op);
                        break;
//            case 2:     usuario = opcionesCompra(op);
//                        break;
            default:    break;
        }
        return usuario;
    }
    
    public String opcionesInvitado(int op) {
        String usuario = "invitado";
        switch(op) {
            case 1:     mostrarLibros(usuario);
                        break;
            case 2:     mostrarServicios(usuario);
                        break;
            case 0:     usuario = "SALIR";
                        break;
            case 9:     usuario = ingresarUsuario();
                        break;
            default:    System.out.print("\n¡Elige una opción válida!\n");
                        break;
        }
        return usuario;
    }
    
    public String opcionesCliente(int op) {
        String usuario = "cliente";
        switch(op) {
            case 1:     comprarLibros(usuario);
                        break;
            case 2:     comprarServicios(usuario);
                        break;
            case 0:     usuario = "invitado";
                        admin.visitas.add(visita);
                        admin.grabaVisitas(Paths.get(System.getProperty("java.io.tmpdir"), "visitas.dat").toString(), admin.visitas);
                        admin.grabaClientes(Paths.get(System.getProperty("java.io.tmpdir"), "clientes.dat").toString(), admin.clientes);
                        break;
            case 9:     verPerfil();
                        break;
            default:    System.out.print("\n¡Elige una opción válida!\n");
                        break;
        }
        return usuario;
    }
    
    public void verPerfil() {
        for (int i = 0; i < admin.visitas.size(); i++) {
            if (visita.getCliente().getNombre().toUpperCase().equals(admin.visitas.get(i).getCliente().getNombre().toUpperCase())) {
               System.out.print(admin.visitas.get(i).toString() + "\n");
            }
        }
        System.out.print(visita.toString() + "\n");
        Scanner sc = new Scanner(System.in);
        System.out.print("\nInserte cualquier caracter para regresar: ");
        String salir = sc.nextLine();
    }
    
    public void comprarLibros(String usuario) {
        mostrarLibros(usuario);
        double compra;
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Qué libro desea comprar? (¡Pulse 0 para salir sin comprar!): ");
        int op = validaOpcion(sc.nextLine(), false);
        if (op > 0) {
            compra = admin.libros.get(op - 1).getPrecio() - (admin.libros.get(op - 1).getPrecio() * admin.descuentos.getDescuentoProductos(visita.getCliente().getTipoMiembro()));
            visita.setGastoProductos(visita.getGastoProductos() + compra);
            System.out.print("\n¡Gracias por su compra!\n");
        }
    }
    
    
    /**
     * Ok, necesito más tiempo para hacer esto. Esto es terrible. Pero terrible,
     * y no terrible en plan divertido como mi anterior práctica. Terrible y
     * punto. Esto necesita más submenús y tonterías varias. A la lista de cosas
     * pendientes.
     * 
     * @param usuario 
     */
    public void comprarServicios(String usuario) {
        mostrarServicios(usuario);
        double compra;
        Scanner sc = new Scanner(System.in);
        System.out.print("¿Qué servicio desea comprar? (¡Pulse 0 para salir sin comprar!)");
        int op = validaOpcion(sc.nextLine(), false);
        if (op > 0) {
            switch(op){
                case 1: compra = admin.servicios.getPreciosServicio("mensual") - (admin.servicios.getPreciosServicio("mensual") * admin.descuentos.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        break;
                case 2: compra = admin.servicios.getPreciosServicio("trimestral") - (admin.servicios.getPreciosServicio("trimestral") * admin.descuentos.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        break;
                case 3: compra = admin.servicios.getPreciosServicio("anual")- (admin.servicios.getPreciosServicio("anual") * admin.descuentos.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        break;
                case 4: compra = admin.servicios.getPreciosMembresia("Plata") - (admin.servicios.getPreciosMembresia("Plata") * admin.descuentos.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        visita.getCliente().setTipoMiembro("Plata");
                        break;
                case 5: compra = admin.servicios.getPreciosMembresia("Oro") - (admin.servicios.getPreciosMembresia("Oro") * admin.descuentos.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        visita.getCliente().setTipoMiembro("Oro");
                        break;
                case 6: compra = admin.servicios.getPreciosMembresia("Premium") - (admin.servicios.getPreciosMembresia("Premium") * admin.descuentos.getDescuentoServicios(visita.getCliente().getTipoMiembro()));
                        visita.setGastoServicios(visita.getGastoServicios() + compra);
                        visita.getCliente().setTipoMiembro("Premium");
            }
            System.out.print("\n¡Gracias por su compra!\n");
        }
    }
    
    
    public String ingresarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nIntroduzca su nombre de usuario (¡O cree uno nuevo si es la primera vez que nos visita!): ");
        String user = sc.nextLine();
        for (int i = 0; i < admin.clientes.size(); i++) {
            if (user.toUpperCase().equals(admin.clientes.get(i).getNombre().toUpperCase()))
                visita = new Visita(admin.clientes.get(i), new Date());
        }
        if (visita == null) {
            Cliente nuevoCliente = new Cliente("user");
            admin.clientes.add(nuevoCliente);
            visita = new Visita(nuevoCliente, new Date());
        }
        
        return "cliente";
    }
    
    public void mostrarLibros(String usuario) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        sb.append("\nCatálogo de Libros");
        sb.append("\n---------------------");
        for (int i = 0; i < admin.libros.size(); i++) {
            sb.append("\n".concat(Integer.toString(i + 1)));
            sb.append(". \tTítulo: ".concat(admin.libros.get(i).getTitulo()));
            sb.append("\n\tAutor: ".concat(admin.libros.get(i).getAutor()));
            sb.append("\n\tPrecio: ".concat(admin.libros.get(i).getPrecio() + " euros\n"));
        }
        sb.append("\n");
        System.out.print(sb.toString());
        if (usuario.equals("invitado")) {
            System.out.print("\nInserte cualquier caracter para regresar: ");
            String salir = sc.nextLine();
        }
    }
    
    public void mostrarServicios(String usuario) {
        Scanner sc = new Scanner(System.in);
        StringBuffer sb = new StringBuffer();
        sb.append("\nCatálogo de Servicios");
        sb.append("\n---------------------\n");
        sb.append("1.\tSuscripción mensual: ");
        sb.append("\t".concat(admin.servicios.getPreciosServicio("mensual") + " euros\n"));
        sb.append("2.\tSuscripción trimestral: ");
        sb.append("\t".concat(admin.servicios.getPreciosServicio("trimestral") + " euros\n"));
        sb.append("3.\tSuscripción anual: ");
        sb.append("\t".concat(admin.servicios.getPreciosServicio("anual") + " euros\n"));
        sb.append("4.\tMembresía Plata: ");
        sb.append("\t".concat(admin.servicios.getPreciosMembresia("Plata") + " euros\n"));
        sb.append("5.\tMembresía Oro: ");
        sb.append("\t".concat(admin.servicios.getPreciosMembresia("Oro") + " euros\n"));
        sb.append("6.\tMembresía Premium: ");
        sb.append("\t".concat(admin.servicios.getPreciosMembresia("Premium") + " euros\n"));
        System.out.print(sb.toString());
        if (usuario.equals("invitado")) {
            System.out.print("\nInserte cualquier caracter para regresar: ");
            String salir = sc.nextLine();
        }
    }
    
    public int validaOpcion(String valor, Boolean modo) {
        int num = -1;
        try {
            num = Integer.parseInt(valor);
            if (modo && num < 0)
                System.out.print("\n¡No has introducido un número válido!\n");
        } catch(NumberFormatException e) {
            System.out.print("\n¡No has introducido un número entero!\n");
        }
        return num;
    }
}
