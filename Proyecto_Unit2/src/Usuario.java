import java.io.Serializable;
import java.util.*;

public class Usuario implements Serializable {
    // Atributos
    String nombre, apellido, correo, clave, telefono;
    int numeroMatricula;
    Fecha fechaNacimiento;
    ArrayList<Nota> listaNotas, papelera;
    // Metodos
    public Usuario(int numeroMatricula, String nombre, String apellido, String telefono, Fecha fechaNacimiento){
        this.numeroMatricula = numeroMatricula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }
    public String generarCorreo(String nombre, String apellido){
        char minus = Character.toLowerCase(nombre.charAt(0));
        this.correo = Character.toString(minus) + "." + apellido.toLowerCase() + "@alumnos.basados.cl";
        System.out.println("Su correo es : " + this.correo);
        return correo;
    }
    public String generarClave(String nombre, String apellido, int numeroMatricula){
        char mayus = Character.toUpperCase(nombre.charAt(0));
        char minus = Character.toLowerCase(apellido.charAt(0));
        String mat = Integer.toString(this.numeroMatricula);
        String matricula = "";
        for (int i = mat.length() ; i>4; i--){
            matricula = matricula +  Character.toString(mat.charAt(i-1));
        }
        this.clave = Character.toString(mayus) + Character.toString(minus) + matricula;
        System.out.println("Su clave es: " + this.clave);
        return clave;
    }
}
