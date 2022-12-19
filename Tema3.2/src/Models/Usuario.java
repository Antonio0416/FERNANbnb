package Models;

import java.util.Date;

public class Usuario {

    //Atributos
    private int id;
    private String nombre;
    private String apellidos;
    private String pass;
    private Reserva reserva1;
    private Reserva reserva2;
    private String telefono;
    private String email;
    private Reserva ultimaReserva;
    private String ultimaReservaCreada = "";
    private int numReservas;



    //Atributos estáticos
    private static int numUsuarios = 0;

    //Constructor
    public Usuario (String email, String pass, String nombre, String apellidos, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.pass = pass;
        this.email = email;
        this.telefono = String.valueOf(telefono);
        numUsuarios++;
        id = numUsuarios * 10;
    }

    public Usuario(String emailTeclado, String passTeclado, String nombreTeclado, String apellidosTeclado, int telefonoTeclado) {
        this.email = emailTeclado;
        this.pass = passTeclado;
        this.nombre = nombreTeclado;
        this.apellidos = apellidosTeclado;
        this.telefono = String.valueOf(telefonoTeclado);
    }

    //G&S


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Reserva getReserva1() {
        return reserva1;
    }

    public void setReserva1(Reserva reserva1) {
        this.reserva1 = reserva1;
    }

    public Reserva getReserva2() {
        return reserva2;
    }

    public void setReserva2(Reserva reserva2) {
        this.reserva2 = reserva2;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Reserva getUltimaReserva() {
        return ultimaReserva;
    }

    public void setUltimaReserva(Reserva ultimaReserva) {
        this.ultimaReserva = ultimaReserva;
    }

    public String getUltimaReservaCreada() {
        return ultimaReservaCreada;
    }

    public void setUltimaReservaCreada(String ultimaReservaCreada) {
        this.ultimaReservaCreada = ultimaReservaCreada;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public void setNumReservas(int numReservas) {
        this.numReservas = numReservas;
    }

    public static int getNumUsuarios() {
        return numUsuarios;
    }

    public static void setNumUsuarios(int numUsuarios) {
        Usuario.numUsuarios = numUsuarios;
    }

    //Métodos
    public boolean buscarVivienda (String localidad) { //Comprueba que hay viviendas en la localidad introducida
        return localidad.equals(Vivienda.getLocalidad());
    }

    public void crearReserva(int id, Usuario usuario, Vivienda vivienda, Date fechaInicio, Date fechaFin, int numViajeros){
        if (reserva1 == null){
            reserva1 = new Reserva(id, usuario, vivienda, fechaInicio, fechaFin, numViajeros);
            guardarUltimaReserva(reserva1.toString());
            recogerUltimaReserva(reserva1);
        }
        else {
            reserva2 = new Reserva(id, usuario, vivienda, fechaInicio, fechaFin, numViajeros);
            guardarUltimaReserva(reserva2.toString());
            recogerUltimaReserva(reserva2);
        }
        numReservas++;
    }

    private void guardarUltimaReserva(String reservaCreada){
        ultimaReservaCreada = reservaCreada;
    }

    public void recogerUltimaReserva(Reserva reserva){
        ultimaReserva = reserva;
    }

    public Reserva devolverUltimaReserva(){
        return ultimaReserva;
    }

    public String mostrarReservas(){
        String listReservas = "";
        if (reserva1!=null)listReservas += reserva1.toString() + "\n";
        if (reserva2!=null)listReservas += reserva2.toString() + "\n";
        return listReservas;
    }

    public boolean comprobacionReserva(int op){
        if (reserva1 != null) {
            if (reserva1.getId() == op) return true;
        }
        if (reserva2 != null) {
            if (reserva2.getId() == op) return true;
        }
        return false;
    }

    public void borrarReserva(int op){
        if (reserva1 != null){
            if (reserva1.getId() == op){
                reserva1 = null;
                numReservas--;
            }
        }
        if (reserva2 != null){
            if (reserva2.getId() == op){
                reserva2 = null;
                numReservas--;
            }
        }
    }

    public String mostrarUltimaReserva(){
        return ultimaReservaCreada;
    }

    public Reserva devulveUltimaReserva() {
        return ultimaReserva;
    }



    //ToString
    @Override
    public String toString() {
        return "~~~~~~~~ USUARIO ~~~~~~~~" +
                "\nNombre = " + nombre +
                "\nApellidos = " + apellidos +
                "\nPass = " + pass +
                "\nTelefono = " + telefono +
                "\nCorreo = " + email ;
    }


}
