package Utils;

import Models.*;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Menus {
    private static Scanner s = new Scanner(System.in);
    private static int opcion;
    private static int totalHuespedes;
    private static double precioNoche;
    private static Models.Vivienda plantillaVivienda;
    private static boolean cerrarSesion;
    private static Info info = new Info();
    public static Date fechaIniTeclado;


    // Menú inicio
    public static void menuInicio() {
        System.out.println();
        System.out.println("""
                |---------------------------------------------------------------------------------|
                |        ______ ___________ _   _   ___   _   _ _           _                     |\s
                |        |  ___|  ___| ___ \\ \\ | | / _ \\ | \\ | | |         | |                    |\s
                |        | |_  | |__ | |_/ /  \\| |/ /_\\ \\|  \\| | |__  _ __ | |__                  |\s
                |        |  _| |  __||    /| . ` ||  _  || . ` | '_ \\| '_ \\| '_ \\                 |\s
                |        | |   | |___| |\\ \\| |\\  || | | || |\\  | |_) | | | | |_) |                |
                |        \\_|   \\____/\\_| \\_\\_| \\_/\\_| |_/\\_| \\_/_.__/|_| |_|_.__/                 |\s
                |---------------------------------------------------------------------------------|

                    """);
        System.out.println("""
                ============== MENU ============== \s
                |   1. Iniciar Sesión            | \s
                |   2. Registro                  | \s
                |   3. Salir                     | \s
                ============ FERNANbnb =========== \s
                """);
    }


    // Menú registro
    public static void menuRegistro() {
        System.out.println("¿Qué tipo de perfil quiere registrar?");
        System.out.println("""
                =================== REGISTRO =====================\s
                |      ¿Qué tipo de perfil quiere registrar?     |\s
                |          1. Usuario                            |\s
                |          2. Propietario                        |\s
                |          3. Salir                              |\s
                =================== FERNANbnb ====================\s
                """);
    }


    // Menú administrador
    public static Administrador menuAdminstrador(Administrador administrador) {
        cerrarSesion = false;
        while (!cerrarSesion){
            System.out.println();
            System.out.println(
                            "==================== PERFIL DE ADMINISTRADOR ====================\n" +
                            "\n" +
                            " Bienvenio/a " + administrador.getNombre() + ", perfil de administración\n" +

                            "|========================= MENÚ PRINCIPAL ========================|\n" +
                            "|\n" +
                            "|  1.- Ver todas las viviendas en alquiler.\n" +
                            "|  2.- Ver todos los usuarios del sistema.\n" +
                            "|  3.- Ver todas las reservas de viviendas.\n" +
                            "|  4.- Ver mi perfil.\n" +
                            "|  5.- Modificar mi perfil.\n" +
                            "|  6.- Cerrar sesión.\n" +
                            "|\n" +
                            "=================================================================\n");

            System.out.print("-> Seleccione una opcion: ");
            opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1: // Ver todas las viviendas en alquiler
                    System.out.println(info.mostrarViviendas());
                    break;
                case 2: // Ver todos los usuarios del sistema
                    System.out.println("Mostrando todos los usuarios");
                    if (info.getTotalUsuarios() == 0 && info.getTotalPropietarios() == 0) System.out.println("No hay usuarios creados");
                    if(info.getTotalPropietarios() != 0 && info.getTotalUsuarios() == 0){
                        System.out.println("--------- PROPIETARIOS ---------");
                        System.out.println(info.mostrarPropietarios());
                    }
                    if(info.getTotalUsuarios() != 0 && info.getTotalPropietarios() == 0){
                        System.out.println("--------- USUARIOS ---------");
                        System.out.println(info.mostrarUsuarios());
                    }
                    if(info.getTotalUsuarios() != 0 && info.getTotalPropietarios() != 0){
                        System.out.println("--------- PROPIETARIOS ---------");
                        System.out.println(info.mostrarPropietarios());
                        System.out.println("\n--------- USUARIOS ---------");
                        System.out.println(info.mostrarUsuarios());
                    }
                    break;
                case 3: // Ver todas las reservas de viviendas
                    System.out.println(Info.mostrarTodasReservas(administrador));
                    break;
                case 4: // Ver mi perfil
                    System.out.println(administrador.toString());
                    break;
                case 5: // Modificar mi perfil
                    do {
                        System.out.println("¿Qué apartado desea modificar?");
                        System.out.println("""
                            =========== MODIFICAR PERFIL ===========
                            |                                      |
                            |  1.- Nombre                          |
                            |  2.- Contraseña                      |
                            |  3.- Salir                           |
                            |                                      |
                            ========================================
                            """);
                        System.out.print("-> Seleccione una opcion: ");
                        opcion = Integer.parseInt(s.nextLine());
                        switch (opcion) {
                            case 1 -> { // Modificar nombre
                                System.out.print("Introduce el nuevo nombre: ");
                                administrador.setNombre(s.nextLine());
                                System.out.println("Guardando cambios");
                            }
                            case 2 -> { // Modificar contraseña
                                System.out.print("Introduce la contraseña antigua: ");
                                if (s.nextLine().equals(administrador.getPass())) {
                                    System.out.print("Introduce la contraseña nueva: ");
                                    administrador.setPass(s.nextLine());
                                    System.out.println("Guardando cambios");
                                } else System.out.println("Contraseña incorrecta");
                            }
                            case 3 -> { // Salir
                                System.out.println("Volviendo al menú administrador");
                            }
                        }
                    } while (opcion != 3);
                    break;
                case 6: // Cerrar sesión
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
            }
            System.out.println("Pulse enter para continuar...");
            s.nextLine();
        }
        System.out.println("Volviendo al menú principal");
        return administrador;
    }


    // Menú usuario
    public static Models.Usuario menuUsuario(Models.Usuario usuario) {
        cerrarSesion = false;
        System.out.println("Cargando menú usuario");
        while (!cerrarSesion){
            System.out.println();
            System.out.println(
                            "============================== PERFIL DE USUARIO ==============================\n" +
                            "\n" +
                            " Bienvenio/a " + usuario.getNombre() +", busque un alojamiento para sus próximas vacaciones\n" +
                            "\n" +
                            "================================ MENÚ PRINCIPAL ===============================\n" +
                            "\n" +
                            "    1.- Búsqueda de alojamientos.\n" +
                            "    2.- Ver mis reservas.\n" +
                            "    3.- Borrar mis reservas.\n" +
                            "    4.- Ver mi perfil.\n" +
                            "    5.- Modificar mi perfil.\n" +
                            "    6.- Cerrar sesión.\n" +
                            "\n" +
                            "===============================================================================\n");
            System.out.print("-> Seleccione una opcion: ");
            opcion = Integer.parseInt(s.nextLine());

            switch (opcion) {
                case 1: // Búsqueda de alojamientos
                    System.out.print("Introduzca una ciudad para buscar: ");
                    String ciudadTeclado = s.nextLine();
                    Date fechaInicio = pedirFechaInicio();
                    Date fechaFin = pedirFechaFin();
                    System.out.print("Introduzca número de huéspedes: ");
                    int huespedesTeclado = Integer.parseInt(s.nextLine());
                    System.out.println("Buscando alojamientos");
                    String resultadoAlojamoientos = Info.buscarAlojamiento(ciudadTeclado, fechaInicio, fechaFin, huespedesTeclado);
                    System.out.println(resultadoAlojamoientos);
                    if(!resultadoAlojamoientos.equals("No hay viviendas en alquiler.") && !resultadoAlojamoientos.equals("No se han encontrado viviendas con esos requisistos")){
                        System.out.print("¿Deseas hacer una reserva (S/N)?: ");
                        if (!s.nextLine().toUpperCase().equals("S")) System.out.println("Pulse enter para continuar...");
                        else {
                            boolean reservar = false;
                            do{
                                System.out.print("¿Qué vivienda deseas reservar? (Seleccione el ID de la vivienda deseada): ");
                                opcion = Integer.parseInt(s.nextLine());
                                switch (opcion){
                                    case 1,2 ->{
                                        if(Info.comprobarID(opcion) == -1) System.out.println("Opción incorrecta."); //Como el total de id son 1 o 2 si se introduce el id equivocado que se muestre como en defauldt
                                        else{
                                            if (usuario.getNumReservas() >= 2) System.out.println("Límite de reversas alcanzado.");
                                            else{
                                                Info.crearReserva(usuario, Info.comprobarID(opcion), fechaInicio, fechaFin, huespedesTeclado);
                                                System.out.println("Generando reserva");
                                                System.out.println(usuario.mostrarUltimaReserva());
                                            }
                                            reservar = true;
                                        }
                                    }
                                    default ->{
                                        System.out.println("Error al elegir la opción.");
                                        System.out.println("Pulse enter para continuar...");
                                        s.nextLine();
                                    }
                                }
                            }while (!reservar);
                        }
                    }else System.out.println("Pulse enter para continuar...");
                    s.nextLine();

                    break;
                case 2: // Ver mis reservas
                    if(usuario.mostrarReservas().equals("")) System.out.println("No hay reservas creadas.");
                    else {
                        System.out.println("Recopilando reservas");
                        System.out.println(usuario.mostrarReservas());
                    }
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
                    break;
                case 3: // Borrar mis reservas
                    if(usuario.mostrarReservas().equals("")) System.out.println("No hay reservas creadas.");
                    else {
                        System.out.println(usuario.mostrarReservas());
                        Boolean salir = false;
                        while (!salir){
                            do {
                                System.out.print("Seleccione la reserva que desea borrar o pulse 0 (cero) para salir: ");
                                opcion = Integer.parseInt(s.nextLine());
                                if(opcion == 0) salir = true;
                                else {
                                    if(usuario.comprobacionReserva(opcion)){
                                        System.out.print("¿Deseas borrarla?: ");
                                        String opcionBorrar = s.nextLine().toUpperCase();
                                        if(opcionBorrar.equals("S")){
                                            usuario.borrarReserva(opcion);
                                            Info.borrarReserva(opcion);
                                            System.out.println("Borrando reserva");
                                        }
                                        salir = true;
                                    } else System.out.println("Opción incorrecta");
                                }

                            }while (!salir);
                        }
                    }
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
                    break;
                case 4: // Ver mi perfil
                    System.out.println(usuario.toString());
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
                    break;
                case 5: // Modificar mi perfil
                    do {
                        menuModificarPerfil();
                        System.out.print("-> Seleccione una opcion: ");
                        opcion = Integer.parseInt(s.nextLine());
                        switch (opcion) {
                            case 1 -> { // Modificar nombre
                                System.out.print("Introduce el nuevo nombre: ");
                                usuario.setNombre(s.nextLine());
                                System.out.println("Guardando cambios");
                            }

                            case 2 -> { // Modificar contraseña pidiendo la antigua
                                System.out.print("Introduce la contraseña antigua: ");
                                if (s.nextLine().equals(usuario.getPass())) {
                                    System.out.print("Introduce la contraseña nueva: ");
                                    usuario.setPass(s.nextLine());
                                    System.out.println("Guardando cambios");
                                } else System.out.println("Contraseña incorrecta.");
                            }
                            case 3 -> { // Modificar número de teléfono pidiendo la contraseña
                                System.out.print("Introduce la contraseña: ");
                                if (s.nextLine().equals(usuario.getPass())) {
                                    System.out.print("Introduce el nuevo teléfono: ");
                                    usuario.setTelefono(String.valueOf(Integer.parseInt(s.nextLine())));
                                } else System.out.println("Contraseña incorrecta.");
                            }
                            case 4 -> { // Salir
                                System.out.println("Volviendo al menú usuario");
                            }
                        }
                    } while (opcion != 4);
                    break;
                case 6: // Cerrar sesión
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
            }
        }
        System.out.println("Volviendo al menú principal");
        return usuario;
    }


    // Menú propietario
    public static Propietario menuPropietario(Propietario propietario, String localidadTeclado, String direccionTeclado, int tipoTeclado) {
        cerrarSesion = false;
        System.out.println("Cargando menú propietario");
        while (!cerrarSesion){
            System.out.println();
            System.out.println(
                    "============================ PERFIL DE PROPIETARIO ============================\n" +
                            "\n" +
                            "  Bienvenio/a " + propietario.getNombre() + ", gestione sus viviendas en alquiler.\n" +
                            "\n" +
                            "|=============================== MENÚ PRINCIPAL ================================\n" +
                            "|\n" +
                            "|   1.- Ver mis viviendas en alquiler.\n" +
                            "|   2.- Editar mis viviendas.\n" +
                            "|   3.- Ver las reservas de mis viviendas.\n" +
                            "|   4.- Cambiar periodo de disponibilidad para una vivienda.\n" +
                            "|   5.- Ver mi perfil.\n" +
                            "|   6.- Modificar mi perfil.\n" +
                            "|   7.- Cerrar sesión.\n" +
                            "|\n" +
                            "|===============================================================================\n");
            System.out.print("-> Seleccione una opcion: ");
            opcion = Integer.parseInt(s.nextLine());
            switch (opcion) {
                case 1: // Ver mis viviendas
                    System.out.println("Recopilando propiedades");
                    if (propietario.existenciaVivienda())  System.out.println(propietario.mostrarVivienda());
                    else System.out.println("No se han registrado viviendas");
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
                    break;
                case 2: // Editar mis viviendas
                    Boolean salirModificacion = false;
                    do {
                        System.out.println("¿Qué apartado desea modificar?");
                        System.out.println("""
                            ========= EDITAR MIS VIVIENDAS =========
                            |                                      |
                            |  1.- Número de huéspedes             |
                            |  2.- Precio por noche                |
                            |  3.- Borrar Vivienda                 |
                            |  4.- Añadir vivienda                 |
                            |  5.- Salir                           |
                            |                                      |
                            ================FERNANbnb===============
                            """);
                        System.out.print("-> Seleccione una opcion: ");
                        opcion = Integer.parseInt(s.nextLine());
                        switch (opcion) {
                            case 1: // Editar el número de huéspedes
                                System.out.print("Introduce el número de huéspedes: ");
                                totalHuespedes = Integer.parseInt(s.nextLine());
                                propietario.cambiarHuespedes(totalHuespedes);
                                System.out.println("Guardando cambios");
                                break;
                            case 2: // Editar el precio por noche
                                System.out.print("Introduce el precio por noche: ");
                                precioNoche = Double.parseDouble(s.nextLine());
                                propietario.cambiarPrecioNoche(precioNoche);
                                System.out.println("Guardando cambios");
                                break;
                            case 3: // Borrar vivienda
                                System.out.println("Borrando vivienda");
                                propietario.borrarVivienda();
                                break;
                            case 4: // Añadir vivienda
                                if (propietario.getTotalViviendas() != 0) System.out.println("Se ha alcanzado el total de viviendas creadas.");
                                else {
                                    // Pedir datos constructor vivienda
                                    // Crearla y después añadirla al contructor de Propietario
                                    plantillaVivienda = new Vivienda(localidadTeclado, direccionTeclado, totalHuespedes, precioNoche, tipoTeclado);
                                    System.out.print("Introduce la localidad donde se encuentra: ");
                                    plantillaVivienda.setLocalidad(s.nextLine());

                                    System.out.print("Introduce la dirección donde se encuentra: ");
                                    plantillaVivienda.setDireccion(s.nextLine());

                                    System.out.print("Introduce el número máximo de huéspedes: ");
                                    plantillaVivienda.setNumViajeros(Integer.parseInt(s.nextLine()));

                                    System.out.print("Introduce el precio por noche: ");
                                    plantillaVivienda.setPrecioNoche((int) Double.parseDouble(s.nextLine()));
                                    do {
                                        menuTiposViviendas();
                                        System.out.print("-> Seleccione una opcion: ");
                                        opcion = Integer.parseInt(s.nextLine());
                                        switch (opcion) {
                                            case 1: // Piso
                                                plantillaVivienda.setTipoVivienda(String.valueOf(1));
                                                break;
                                            case 2: // Chatle
                                                plantillaVivienda.setTipoVivienda(String.valueOf(2));
                                                break;
                                            case 3: // Apartamento
                                                plantillaVivienda.setTipoVivienda(String.valueOf(3));
                                                break;
                                            case 4: // Casa
                                                plantillaVivienda.setTipoVivienda(String.valueOf(4));
                                                break;
                                            default:
                                                System.out.println("Tipo de vivienda incorrecto.");
                                                System.out.println("Pulse enter para continuar...");
                                                s.nextLine();
                                        }
                                    }while (opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4);
                                    propietario.crearVivienda(plantillaVivienda);
                                    System.out.println("Guardando cambios");
                                }
                                break;
                            case 5: //Salir
                                salirModificacion = true;
                                System.out.println("Volviendo al menú propietario");
                                break;
                            default:
                                System.out.println("Error al elegir la opción.");
                                System.out.println("Pulse enter para continuar...");
                                s.nextLine();
                        }
                    } while (!salirModificacion);
                    break;
                case 3: // Ver las reservas de mis viviendas
                    System.out.println(propietario.verReservas());
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
                    break;
                case 4: // Cambiar periodo de no disponibilidad para una vivienda
                    // Simular que en las fechas introducidas no esté disponible la vivienda
                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                    String op;
                    if(propietario.getVivienda().getFechaInicio() != null) {
                        do {
                            System.out.print("Actualmente tienes un periodo de no disponibilidad en su vivienda en las siguientes fechas: " +
                                    "\nInicio: " + formato.format(propietario.getVivienda().getFechaInicio()) +
                                    "\nFin: " + formato.format(propietario.getVivienda().getFechaFin()) +
                                    "\n¿Deseas borrarla o modificarla (B/M)?: ") ;
                            op = s.nextLine().toUpperCase();
                            switch (op){
                                case "B" -> {
                                    propietario.getVivienda().borrarFechaNoDisponibilidad();
                                    System.out.println("Borrando fecha de no disponibilidad");
                                }
                                case "M" -> {
                                    crearPeriodoNoDisponible(propietario);
                                    System.out.println("Guardando cambios");
                                }
                                default -> {
                                    System.out.println("Error al elegir la opción.");
                                    System.out.println("Pulse enter para continuar...");
                                    s.nextLine();
                                }
                            }
                        } while (!op.equals("B") && !op.equals("M"));

                    }else {
                        crearPeriodoNoDisponible(propietario);
                        System.out.println("Guardando cambios");
                    }
                    break;
                case 5: // Ver mi perfil
                    System.out.println(propietario.toString());
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
                    break;
                case 6: // Modificar mi perfil
                    boolean salirMenuOpcion = false;
                    do {
                        menuModificarPerfil();
                        System.out.print("-> Seleccione una opcion: ");
                        opcion = Integer.parseInt(s.nextLine());
                        switch (opcion) {
                            case 1 -> { // Modificar nombre
                                System.out.print("Introduce el nuevo nombre: ");
                                propietario.setNombre(s.nextLine());
                                System.out.println("Guardando cambios");
                            }
                            case 2 -> { // Modificar contraseña pidiendo la antigua
                                System.out.print("Introduce la contraseña antigua: ");
                                if (s.nextLine().equals(propietario.getPass())) {
                                    System.out.print("Introduce la contraseña nueva: ");
                                    propietario.setPass(s.nextLine());
                                    System.out.println("Guardando cambios");
                                } else System.out.println("Contraseña incorrecta");
                            }
                            case 3 -> { // Modificar número de teléfono pidiendo la contraseña
                                System.out.print("Introduce la contraseña: ");
                                if (s.nextLine().equals(propietario.getPass())) {
                                    System.out.print("Introduce el nuevo teléfono: ");
                                    propietario.setTelefono(Integer.parseInt(s.nextLine()));
                                } else System.out.println("Contraseña incorrecta");
                            }
                            case 4 -> { // Salir
                                salirMenuOpcion = true;
                                System.out.println("Volviendo al menú propietario");
                            }
                        }
                    } while (!salirMenuOpcion);
                    break;
                case 7: // Cerrar sesión
                    cerrarSesion = true;
                    break;
                default:
                    System.out.println("Error al elegir la opción.");
                    System.out.println("Pulse enter para continuar...");
                    s.nextLine();
            }
        }
        System.out.println("Volviendo al menu principal");
        return propietario;
    }

    // Método para validar fecha de inicio
    public static Date pedirFechaInicio(){
        Date fechaInicioTeclado = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Introduzca una fecha de inicio (dd/MM/yyyy): ");
        try {
            fechaInicioTeclado = formato.parse(s.nextLine());
            fechaIniTeclado = fechaInicioTeclado;
        } catch (ParseException e) {
            System.out.println("Error al introducir la fecha.");
            pedirFechaInicio();
        }
        return fechaInicioTeclado;
    }

    // Método para validar fecha de fin
    public static Date pedirFechaFin(){
        Date fechaFinTeclado = null;
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Introduzca una fecha de fin (dd/mm/yyyy): ");
        try {
            fechaFinTeclado = formato.parse(s.nextLine());
            if (fechaFinTeclado.before(fechaIniTeclado)) {
                System.out.println("¡Error! Has introducido una fecha de fin anterior a la fecha de inicio.");
                pedirFechaFin();
            }
        } catch (ParseException e) {
            System.out.println("Error al introducir la fecha.");
            pedirFechaFin();
        }
        return fechaFinTeclado;
    }

    public static void crearPeriodoNoDisponible(Propietario propietario) {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaI = pedirFechaInicio();
        Date fechaF = pedirFechaFin();
        propietario.cambiarEstadoVivienda(fechaI, fechaF);
        System.out.println("Tu vivienda no estará disponible en estas fechas: Fecha Inicio: " + formato.format(fechaI) + "    Fecha Fin: " + formato.format(fechaF));
    }

    public static void menuTiposViviendas() {

        System.out.println("""
                            ======== TIPO DE VIVIENDA ========
                            |                                |
                            |  1.- Piso                      |
                            |  2.- Chalet                    |
                            |  3.- Apartamento               |
                            |  4.- Casa                      |
                            |                                |
                            =============FERNANbnb============
                            """);
    }

    public static void menuModificarPerfil() {
        System.out.println("¿Qué apartado desea modificar?");
        System.out.println("""
                            =========== MODIFICAR PERFIL ===========
                            |                                      |
                            |  1.- Nombre                          |
                            |  2.- Contraseña                      |
                            |  3.- Teléfono                        |
                            |  4.- Salir                           |
                            |                                      |
                            ================FERNANbnb===============
                            """);
    }
}