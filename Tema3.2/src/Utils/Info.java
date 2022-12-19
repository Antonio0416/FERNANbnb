package Utils;

import Models.*;

import java.util.Date;

public class Info {
    private static Administrador administrador = new Administrador("admin@gmail.com", "admin", "Administrador");
    private static Usuario user1;
    private static Usuario user2;
    private static Propietario propietario1;
    private static Propietario propietario2;
    private static Reserva reserva;
    public static int totalPropietarios = 0;
    private static int totalUsuarios = 0;
    private int contadorViviendas = 0;
    private static int contadorReservas = 0;
    private static String idResultadoAlojamiento;


    // Métodos
    public static Administrador getAdministrador() {
        return administrador;
    }

    public static void setAdministrador(Administrador administrador) {
        Info.administrador = administrador;
    }

    public static Usuario getUser1() {
        return user1;
    }

    public static void setUser1(Usuario user1) {
        Info.user1 = user1;
    }

    public static Usuario getUser2() {
        return user2;
    }

    public static void setUser2(Usuario user2) {
        Info.user2 = user2;
    }

    public static Propietario getPropietario1() {
        return propietario1;
    }

    public static void setPropietario1(Propietario propietario1) {
        Info.propietario1 = propietario1;
    }

    public static Propietario getPropietario2() {
        return propietario2;
    }

    public static void setPropietario2(Propietario propietario2) {
        Info.propietario2 = propietario2;
    }

    public static String getIdResultadoAlojamiento() {
        return idResultadoAlojamiento;
    }

    public static void setIdResultadoAlojamiento(String idResultadoAlojamiento) {
        Info.idResultadoAlojamiento = idResultadoAlojamiento;
    }

    public static int comprobarID(int opcion) {
        String opcionLetra = String.valueOf(opcion);
        for(int i = 0; i < idResultadoAlojamiento.length(); i ++){
            if(idResultadoAlojamiento.substring(i,i+1).equals(opcionLetra)){
                return opcion;
            }
        }
        return -1;
    }

    public static Vivienda buscarVivienda(int id){
        if(propietario1.getVivienda().getId() == id) return propietario1.getVivienda();
        return propietario2.getVivienda();
    }

    public static void crearReserva(Usuario usuario, int idVivienda, Date fechaInicio, Date fechaFin, int huespedesTeclado) {
        contadorReservas++;
        if(user1.getEmail().equals(usuario.getEmail())){
            user1.crearReserva(contadorReservas, usuario,buscarVivienda(idVivienda),fechaInicio,fechaFin, huespedesTeclado);
            Reserva reserva = user1.devulveUltimaReserva();
            if(propietario1.getVivienda() != null){
                if(buscarVivienda(idVivienda).getId() == propietario1.getVivienda().getId()) propietario1.getVivienda().crearReserva(reserva);
            }else{
                if(propietario2.getVivienda() != null){
                    if(buscarVivienda(idVivienda).getId() == propietario2.getVivienda().getId()) propietario2.getVivienda().crearReserva(reserva);
                }
            }
        }
        else {
            user2.crearReserva(contadorReservas, usuario,buscarVivienda(idVivienda),fechaInicio,fechaFin, huespedesTeclado);
            Reserva reserva = user1.devulveUltimaReserva();
            if(propietario1.getVivienda() != null){
                if(buscarVivienda(idVivienda).getId() == propietario1.getVivienda().getId()) propietario1.getVivienda().crearReserva(reserva);
            }else{
                if(propietario2.getVivienda() != null){
                    if(buscarVivienda(idVivienda).getId() == propietario2.getVivienda().getId()) propietario2.getVivienda().crearReserva(reserva);
                }
            }
        }
    }

    public static void borrarReserva(int id){
        if(propietario1 != null){
            if (propietario1.getVivienda() != null){
                if (propietario1.getVivienda().coincideIdReserva(id)) propietario1.getVivienda().borrarReserva(id);
            }
        }
        else {
            if (propietario2.getVivienda() != null){
                if (propietario2.getVivienda().coincideIdReserva(id)) propietario2.getVivienda().borrarReserva(id);
            }
        }
    }

    public int getTotalPropietarios() {
        return Propietario.getNumPropietarios();
    }

    public void setTotalPropietarios(int totalPropietarios) {
        this.totalPropietarios = totalPropietarios;
    }

    public int getTotalUsuarios() {
        return Usuario.getNumUsuarios();
    }

    public void setTotalUsuarios(int totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public int getContadorViviendas() {
        return contadorViviendas;
    }

    public void setContadorViviendas(int contadorViviendas) {
        this.contadorViviendas = contadorViviendas;
    }

    // Creamos una copia del propietario sin vivienda
    public void registrarPropietarioSinVivienda(Propietario nuevoPropietario, String apellidosTeclado, String localidadTeclado, String direccionTeclado, int tipoTeclado){
        if(totalPropietarios == 0){
            propietario1 = new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(), apellidosTeclado, nuevoPropietario.getTelefono());
            irMenuPropietario(propietario1, localidadTeclado, direccionTeclado, tipoTeclado);
        }else{
            propietario2 = new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(), apellidosTeclado, nuevoPropietario.getTelefono());
            irMenuPropietario(propietario2, localidadTeclado, direccionTeclado, tipoTeclado);
        }
        totalPropietarios++;
    }

    // Creamos una copia del propietario con vivienda
    public void registrarPropietarioConVivienda(Propietario nuevoPropietario, Vivienda nuevaVivienda, String localidadTeclado, String direccionTeclado, int tipoTeclado){
        contadorViviendas++;
        if(totalPropietarios == 0){
            propietario1 = new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(), new Vivienda(nuevaVivienda.getLocalidad(),nuevaVivienda.getDireccion(),nuevaVivienda.getNumViajeros(), (int) nuevaVivienda.getPrecioNoche(),nuevaVivienda.getTipoVivienda(),contadorViviendas),
                    nuevoPropietario.getTelefono());
            irMenuPropietario(propietario1, localidadTeclado, direccionTeclado, tipoTeclado);
        }else{
            propietario2 = new Propietario(nuevoPropietario.getEmail(),nuevoPropietario.getPass(),nuevoPropietario.getNombre(), new Vivienda(nuevaVivienda.getLocalidad(),nuevaVivienda.getDireccion(),nuevaVivienda.getNumViajeros(), (int) nuevaVivienda.getPrecioNoche(),nuevaVivienda.getTipoVivienda(), contadorViviendas),
                    nuevoPropietario.getTelefono());
            irMenuPropietario(propietario2, localidadTeclado, direccionTeclado, tipoTeclado);
        }
        totalPropietarios++;
    }

    public boolean maximoUsuariosPermitidos(){
        if( totalUsuarios < 2) return false;
        return true;
    }
    public boolean maximoPropietariosPermitidos(){
        if( totalPropietarios < 2) return false;
        return true;
    }

    public void registrarUsuario(Usuario nuevoUsuario){
        if(totalUsuarios == 0){
            user1 = new Usuario(nuevoUsuario.getEmail(),nuevoUsuario.getPass(),nuevoUsuario.getNombre(),nuevoUsuario.getApellidos(), nuevoUsuario.getTelefono());
            irMenuUsuario(user1);
        }else {
            user2 = new Usuario(nuevoUsuario.getEmail(),nuevoUsuario.getPass(),nuevoUsuario.getNombre(),nuevoUsuario.getApellidos(), nuevoUsuario.getTelefono());
            irMenuUsuario(user2);
        }
        totalUsuarios++;
    }

    public boolean validarEmail(String email){
        if (email.equals(administrador.getEmail())) return false;
        if(user1 != null){
            if (email.equals(user1.getEmail())) return false;
        }
        if(user2 != null){
            if (email.equals(user2.getEmail())) return false;
        }
        if(propietario1 != null){
            if (email.equals(propietario1.getEmail())) return false;
        }
        if(propietario2 != null){
            if (email.equals(propietario2.getEmail())) return false;
        }
        return true;
    }

    public boolean comprobarEspacio(String email){
        if(email == "") return true;
        return false;
    }

    public String mostrarUsuarios(){
        String listaUsuarios = "";
        if(user1 != null) listaUsuarios += (user1.getNombre()+" "+user1.getApellidos());
        if(user2 != null) listaUsuarios += (user2.getNombre()+" "+user2.getApellidos());
        return listaUsuarios;
    }

    public String mostrarPropietarios(){
        String listaPropietarios = "";
        if(propietario1 != null) listaPropietarios += propietario1.getNombre() ;
        if(propietario2 != null) listaPropietarios += propietario2.getNombre() ;
        return listaPropietarios;
    }

    public String mostrarViviendas(){
        String lista = "";
        //Comprobamos la existencia de los propietarios y si existen comprobamos si tienen viviendas
        if(propietario1 != null){
            if(propietario1.existenciaVivienda()) lista += "\n" + propietario1.existenciaVivienda();
        }
        if(propietario2 != null){
            if(propietario2.existenciaVivienda()) lista += "\n" + propietario2.existenciaVivienda();
        }
        if (lista.equals("")) return "No hay viviendas creadas.";
        return lista;
    }


    // Mostramos todas las reservas de todos los propietarios
    public static String mostrarTodasReservas(Administrador administrador) {
        String listaReservasTotales = "RESERVAS ENCONTRADAS: ";
        // Comprobamos que los propietarios no estén a null
        if (propietario1 != null) {
            if (!propietario1.verReservas().equals("No se han realizado reservas") && !propietario1.verReservas().equals("No hay viviendas en alquiler")){
                // Mostrar las reservas del propietario1
                listaReservasTotales += "\n" + propietario1.verReservas();
            }
        }
        if (propietario2 != null) {
            if (!propietario2.verReservas().equals("No se han realizado reservas") && !propietario2.verReservas().equals("No hay viviendas en alquiler")){
                // Mostrar las reservas del propietario2
                listaReservasTotales += "\n" + propietario2.verReservas();
            }
        }
        if (listaReservasTotales.equals("RESERVAS ENCONTRADAS: ")) return "No se han encontrado revervas";
        else return listaReservasTotales;
    }

    //Método que comprueba el email y contradeña con los datos del admin, usuario y propietario
    public boolean usuarioExistente(String email, String pass) {
        if(administrador != null){
            if(administrador.getEmail().equals(email) && administrador.getPass().equals(pass)) return true;
        }
        if(user1 != null){
            if(user1.getEmail().equals(email) && user1.getPass().equals(pass)) return true;
        }
        if(user2 != null){
            if(user2.getEmail().equals(email) && user2.getPass().equals(pass)) return true;
        }
        if (propietario1 != null) {
            if(propietario1.getEmail().equals(email) && propietario1.getPass().equals(pass)) return true;
        }
        if (propietario2 != null) {
            if(propietario2.getEmail().equals(email) && propietario2.getPass().equals(pass)) return true;
        }
        return false;
    }

    public void iniciarSesion(String email, String pass, String localidadTeclado, String direccionTeclado, int tipoTeclado){
        if(administrador != null){
            if(administrador.getEmail().equals(email) && administrador.getPass().equals(pass)) irMenuAdmin(administrador);
        }
        if(user1 != null){
            if(user1.getEmail().equals(email) && user1.getPass().equals(pass)) irMenuUsuario(user1);
        }
        if(user2 != null){
            if(user2.getEmail().equals(email) && user2.getPass().equals(pass)) irMenuUsuario(user2);
        }
        if (propietario1 != null) {
            if(propietario1.getEmail().equals(email) && propietario1.getPass().equals(pass)) irMenuPropietario(propietario1, localidadTeclado, direccionTeclado, tipoTeclado);
        }
        if (propietario2 != null) {
            if(propietario2.getEmail().equals(email) && propietario2.getPass().equals(pass)) irMenuPropietario(propietario2, localidadTeclado, direccionTeclado, tipoTeclado);
        }
    }


    public static String buscarAlojamiento(String ciudad, Date fechaInicio, Date fechaFin, int huespedes) {
        String listaReservas = " ";
        idResultadoAlojamiento = " ";
        if (totalPropietarios == 0) return "No hay viviendas en alquiler.";
        else {
            if (propietario1 != null){
                if(propietario1.getVivienda() != null){
                    if (propietario1.getVivienda().cumpliRequisitos(ciudad, fechaInicio,fechaFin,huespedes)){
                        idResultadoAlojamiento += propietario1.getVivienda().getId()+" ";
                        listaReservas += propietario1.getVivienda().toString() + "\n";
                    }
                }
            }
            if (propietario2 != null){
                if(propietario2.getVivienda() != null){
                    if (propietario2.getVivienda().cumpliRequisitos(ciudad, fechaInicio, fechaFin, huespedes)) {
                        idResultadoAlojamiento += propietario2.getVivienda().getId()+" ";
                        listaReservas += propietario2.getVivienda().toString() + "\n";
                    }
                }
            }
        }
        if (listaReservas.equals(" ")) return "No se han encontrado viviendas con esos requisistos";
        return listaReservas;
    }

    public static void irMenuAdmin(Administrador admin){
        Menus.menuAdminstrador(admin);
    }

    private void irMenuUsuario(Usuario user){
        Menus.menuUsuario(user);
    }

    private void irMenuPropietario(Propietario propietario, String localidadTeclado, String direccionTeclado, int tipoTeclado){
        Menus.menuPropietario(propietario, localidadTeclado, direccionTeclado, tipoTeclado);
    }

}
