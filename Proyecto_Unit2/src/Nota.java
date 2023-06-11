import java.io.Serializable;

public class Nota implements Serializable {
    // Atributos
    String titulo, contenido, color, temas;
    Boolean prioridad;
    Fecha fechaCreacion;
    // Metodos
    public Nota(String titulo, String contenido, String color, Boolean prioridad){
        this.titulo = titulo;
        this.contenido = contenido;
        this.color = color;
        this.prioridad = prioridad;
    }
    public void editarTitulo(String titulo){
        this.titulo = titulo;
    }
    public void editarContenido(String contenido){
        this.contenido = contenido;
    }
    public void editarColor(String Color){
        this.color = Color;
    }
    public void editarTemas(String temas){
        this.temas = temas;
    }
    public void editarPrioridad(Boolean prioridad){
        this.prioridad = prioridad;
    }
}
