package Models;

import java.util.Date;

public class Propietario {

    //Atributos
    private String email;

    private String nombre;
    private String pass;
    private Models.Vivienda vivienda;
    private int telefono;
    private int totalViviendas = 0;

    //Atributos estáticos
    private static int numPropietarios = 0;

    //Constructor
    public Propietario(String email, String pass, String nombre, String apellidosTeclado, int telefono) {
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Propietario(String nuevoEmail, String nuevoNombre, String nuevaPass, Vivienda nuevaVivienda, int telefono) {
        this.email = nuevoEmail;
        this.nombre = nuevoNombre;
        this.pass = nuevaPass;
        this.vivienda = new Vivienda(nuevaVivienda.getLocalidad(), nuevaVivienda.getDireccion(),nuevaVivienda.getNumViajeros() , nuevaVivienda.getPrecioNoche(), nuevaVivienda.getTipoVivienda(), nuevaVivienda.getId());
        this.telefono = telefono;
        totalViviendas++;
        numPropietarios++;
    }

    public Propietario(String email, String nombre, String pass, Vivienda vivienda, int telefono, int totalViviendas) {
        this.email = email;
        this.nombre = nombre;
        this.pass = pass;
        this.vivienda = vivienda;
        this.telefono = telefono;
        this.totalViviendas = totalViviendas;
    }

    //G&S

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public int getTotalViviendas() {
        return totalViviendas;
    }

    public void setTotalViviendas(int totalViviendas) {
        this.totalViviendas = totalViviendas;
    }

    public static int getNumPropietarios() {
        return numPropietarios;
    }
    public void cambiarHuespedes(int nuevoHuespedes){ vivienda.setNumViajeros(nuevoHuespedes); }

    public static void setNumPropietarios(int numPropietarios) {
        Propietario.numPropietarios = numPropietarios;
    }
    public String mostrarVivienda() { return vivienda.toString(); }
    public void cambiarPrecioNoche(double nuevoPrecio){ vivienda.setPrecioNoche((int) nuevoPrecio); }

    //Métodos
    public boolean login(String email, String pass) {
        if (this.email.equals(email) && this.pass.equals(pass)) return true;
        return false;
    }

    //Mostrar reservas del propietario
    public String verReservas() {
        String listReservas = "";
        if (getVivienda() != null) {
            if (getVivienda().getReserva1() != null) listReservas += getVivienda().getReserva1().toString() + "\n";
            if (getVivienda().getReserva2() != null) listReservas += getVivienda().getReserva2().toString() + "\n";
            if (listReservas.equals("")) return "Todavia no se han añadido reservas.";
            else return listReservas;
        }
        return "No hay viviendas alquiladas.";
    }

    //Disponibilidad de la vivienda en las fechas introducidas
    public void cambiarEstadoVivienda(Date fechaInicio, Date fechaFin) {
        vivienda.setFechaInicio(fechaInicio);
        vivienda.setFechaFin(fechaFin);
    }

    public boolean existenciaVivienda() {
        if (vivienda == null) return false;
        return true;
    }

    public void crearVivienda(Vivienda vivienda) {
        this.vivienda = new Vivienda(vivienda.getLocalidad(), vivienda.getDireccion(), vivienda.getNumViajeros(), vivienda.getPrecioNoche(), vivienda.getId());
        totalViviendas++;
    }

    public void borrarVivienda() {
        if (totalViviendas == 0) System.out.println("No hay viviendas para poder borrar");
        else {
            vivienda = null;
            totalViviendas--;
        }
    }

    //ToString

    @Override
    public String toString() {
        return "\n~~~~~~~~ Propietario ~~~~~~~~" +
                "\n Email = " + email +
                "\n Nombre = " + nombre +
                "\n Pass = " + pass +
                "\n Telefono = " + telefono +
                 vivienda ;
    }
}
