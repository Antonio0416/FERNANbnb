package Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {

    //Atributos
    private int id;
    private Models.Usuario usuario;
    private Vivienda vivienda;
    private int numNoches;
    private Date fechaEntrada;
    private Date fechaSalida;
    private int numViajeros;

    //Constructor
    public Reserva(int id, Usuario usuario, Vivienda vivienda, Date fechaInicio, Date fechaFin, int numViajeros) {
    }

    //G&S

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Models.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Models.Usuario usuario) {
        this.usuario = usuario;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
    }

    public int getNumNoches() {
        return numNoches;
    }

    public void setNumNoches(int numNoches) {
        this.numNoches = numNoches;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public int getNumViajeros() {
        return numViajeros;
    }

    public void setNumViajeros(int numViajeros) {
        this.numViajeros = numViajeros;
    }

    //ToString

    @Override
    public String toString() {
        SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yyyy");
        return "----Reserva: " + id + "----" +
                "\n Usuario = " + usuario +
                "\n Vivienda = " + vivienda +
                "\n Numero de noches = " + numNoches +
                "\n Fecha de entrada = " + Formato.format(fechaEntrada) +
                "\n Fecha de salida = " + Formato.format(fechaSalida) +
                "\n Numero de viajeros = " + numViajeros +
                "\n Precio noche= " + vivienda.getPrecioNoche() + "Euros" +
                "\n Precio total = " + (vivienda.getPrecioNoche()*numNoches) + "Euros";
    }
}
