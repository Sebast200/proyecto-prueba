import java.util.ArrayList;
import java.io.*;
import java.io.FileOutputStream;
public class Persistencia implements Serializable {
    public void Serializacion(ArrayList<Usuario> listado){
        try {
            FileOutputStream archivo = new FileOutputStream("usuarios.txt");
            ObjectOutputStream objSalida = new ObjectOutputStream(archivo);
            objSalida.writeObject(listado);
            objSalida.close();
            archivo.close();
            System.out.println("Archivo Serializado... ");
        } catch (IOException ex) {
            System.out.println("salida... ");
            ex.printStackTrace();
        }
    }
    public void Deserializar(ArrayList<Usuario> lista){
        ArrayList<Usuario> listado;
        try {
            FileInputStream archivo = new FileInputStream("usuarios.txt");
            ObjectInputStream objEntrada = new ObjectInputStream(archivo);
            listado = (ArrayList<Usuario>) objEntrada.readObject();
            objEntrada.close();
            archivo.close();
            System.out.println("Items: " + listado.size());
            for (int i = 0; listado.size() > i; i++) {
                lista.add(listado.get(i));
            }
        } catch (IOException ex) {
            System.out.println("Archivo no leido... ");
        } catch (ClassNotFoundException cnf) {
            System.out.println("clase no existe... ");
        }
    }
}

