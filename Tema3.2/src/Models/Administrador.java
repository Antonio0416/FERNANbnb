package Models;

public class Administrador {
    //Atributos
    private String email;
    private String pass;
    private String nombre;

    //Constructor

    public Administrador(String email, String pass, String nombre) {
        this.email = email;
        this.pass = pass;
        this.nombre = nombre;
    }


    //G&S

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Metodos
    public boolean login (String email, String pass){
        if (this.email.equals(email) && this.pass.equals(pass))return true;
        return false;
    }

    //ToString

    @Override
    public String toString() {
        return "~~~~ Administrador ~~~~" +
                "\nEmail = " + email + '\'' +
                "\nPass = " + pass + '\'' +
                "\n Hombre = " + nombre + '\'' +
                "\n|~~~~~~~~~~~~~~~~~~~~~~~~|";
    }
}
