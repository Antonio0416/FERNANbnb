package Models;

import java.util.Date;

public class Vivienda {

    //Atributos

    private static String localidad;
    private int id = 0;
    private String direccion;
    private int numViajeros;
    private String tipoVivienda;
    private double precioNoche;
    private Date fechaInicio;
    private Date fechaFin;
    private int numReservas = 0;
    private Reserva reserva1;
    private Reserva reserva2;

    //Constructor
    public Vivienda(String localidadTeclado, String direccionTeclado, int totalHuespedes, double precioNoche, int tipoTeclado) {
        this.direccion = direccionTeclado;
        this.numViajeros = totalHuespedes;
        this.precioNoche = (int) precioNoche;
        this.tipoVivienda = String.valueOf(tipoTeclado);
        this.localidad = localidadTeclado;
    }

    public Vivienda(String direccion, int numViajeros, String tipo, int precioNoche) {
        this.id++;
        this.direccion = direccion;
        this.localidad = localidad;
        this.numViajeros = numViajeros;
        this.tipoVivienda = tipoVivienda;
        this.precioNoche = precioNoche;
    }

    public Vivienda(String localidad, String direccion,int numViajeros , double precioNoche, String tipoVivienda, int id) {
        this.direccion = direccion;
        this.precioNoche = precioNoche;
        this.numViajeros = numViajeros;
        this.tipoVivienda = tipoVivienda;
        this.id = id++;
    }

    //G&S

    public static String getLocalidad() {
        return localidad;
    }

    public static void setLocalidad(String localidad) {
        Vivienda.localidad = localidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumViajeros() {
        return numViajeros;
    }

    public void setNumViajeros(int numViajeros) {
        this.numViajeros = numViajeros;
    }

    public String getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(String tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    public void setPrecioNoche(int precioNoche) {
        this.precioNoche = precioNoche;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public int getNumReservas() {
        return numReservas;
    }

    public void setNumReservas(int numReservas) {
        this.numReservas = numReservas;
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


    //Métodos
    public static String tipoViviendaString (int tipoVivienda){
        if (tipoVivienda == 1) return "Piso";
        if (tipoVivienda == 2) return "Chalet";
        if (tipoVivienda == 3) return "Apartamento";
        else return "Casa";
    }
    private  boolean cumplirRequisitoFechaNoDisponible(Date fechaInicio, Date fechaFin){
        if (this.fechaInicio == null) return true;
        else{
            if (cumplirRequisitoFechaInicio(fechaInicio, fechaFin, this.fechaInicio) || cumplirRequisitoFechaFin(fechaInicio,fechaFin, this.fechaFin)) return true;
            return false;
        }
    }

    private boolean cumplirRequisitoOtrasReservas(Date fechaInicio, Date fechaFin) {
        if(reserva1 != null){
            if (!cumplirRequisitoFechaInicio(fechaInicio, fechaFin, reserva1.getFechaEntrada()) && !cumplirRequisitoFechaFin(fechaInicio,fechaFin, reserva1.getFechaSalida())) return false;
        }
        if(reserva2 != null){
            if (!cumplirRequisitoFechaInicio(fechaInicio, fechaFin, reserva2.getFechaEntrada()) && !cumplirRequisitoFechaFin(fechaInicio,fechaFin, reserva2.getFechaSalida())) return false;
        }
        return true;
    }



    public boolean cumpliRequisitos(String ciudad, Date fechaInicio, Date fechaFin, int huespedes){
        if (!cumpliRequisitos(ciudad, fechaInicio, fechaFin, huespedes)) return false;
        if (!cumplirRequisitoFechaNoDisponible(this.fechaFin, this.fechaInicio)) return false;
        if (!cumplirRequisitoOtrasReservas(this.fechaFin, this.fechaInicio)) return false;
        if (!cumplirRequisitoViajeros(numViajeros));
        return true;
    }

    private boolean cumplirRequisitoFechaFin(Date fechaInicio, Date fechaFin, Date fechaFinCriterio){
        if(fechaInicio.after(fechaFinCriterio) && fechaFin.after(fechaFinCriterio)) return true;
        return false;
    }

    private boolean cumplirRequisitoFechaInicio(Date fechaInicio, Date fechaFin, Date fechaInicioCriterio){
        if(fechaInicio.before(fechaInicioCriterio) && fechaFin.before(fechaInicioCriterio)) return true;
        return false;
    }

    private boolean cumplirRequisitoViajeros(int viajeros){
        if (this.numViajeros >= viajeros) return true;
        return false;
    }

    private boolean cumplirRequisitoLocalidad(String ciudad){
        if (this.localidad.equalsIgnoreCase(ciudad)) return true;
        return false;
    }

    public void borrarFechaNoDisponibilidad() {
        this.fechaInicio = null;
        this.fechaFin = null;
    }

    public void crearReserva(Reserva reserva) {
        if (reserva1 == null) {
            reserva1 = reserva;
        } else if (reserva2 == null) {
            reserva2 = reserva;
        }
    }

    public boolean coincideIdReserva(int id){
        if (reserva1 != null) {
            if (reserva1.getId() == id) return true;
        } else if (reserva2 != null) {
            if (reserva2.getId() == id) return true;
        }
        return false;
    }

    public void borrarReserva(int id){
        if (reserva1 != null) {
            if (reserva1.getId() == id) reserva1 = null;
        } else if (reserva2 != null) {
            if (reserva2.getId() == id) reserva2 = null;
        }
    }
    //ToString


    @Override
    public String toString() {

        return "\n~~~~~~~~ Vivienda : " + id + " ~~~~~~~~" +
                "\n Localidad = " + localidad +
                "\n Dirección = " + direccion +
                "\n Numero de viajeros = " + numViajeros +
                "\n Precio por noche = " + precioNoche ;
    }

}

