import java.io.Serializable;
import java.util.Calendar;

public class Fecha implements Serializable {
    //Atributos
    int dia, mes, anio;
    Calendar calendario = Calendar.getInstance();
    int year = calendario.get(Calendar.YEAR);
    int month = calendario.get(Calendar.MONTH)+1;
    int day = calendario.get(Calendar.DATE);
    //Metodos
    public Fecha(int dia, int mes, int anio){
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }
    public void fechaActual(){
        this.dia =  day;
        this.mes = month;
        this.anio = year;
        //System.out.println(this.dia+"/"+this.mes+"/"+this.anio);
    }
}