import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.*;
import java.util.Calendar;
/*Hola amigos de youtube como estan espero que muy bien */

// Sebastian Carlos - Alonso Cabello
public class Main implements Serializable {

    public static void main(String[] args) {
        Persistencia serial = new Persistencia();
        Boolean iniciar = true;
        int u = 0;
        ArrayList<Usuario> listaU = new ArrayList<>();
        serial.Deserializar(listaU);
        // Menu de Inicio
        System.out.println("Bienvenido!!");
        while (iniciar == true) {
            Scanner lectura = new Scanner(System.in);
            System.out.println("[1] Iniciar Sesion. \n[2] Registrarse. \n[3] Salir.");
            System.out.print("Opcion: ");
            int opcion = Integer.parseInt(lectura.nextLine());
            if (opcion == 1) {
                int indice = 0;
                Boolean cont = true;
                System.out.print("Ingrese su nombre de usuario: ");
                String name = lectura.nextLine();
                System.out.print("Clave: ");
                String clave = lectura.nextLine();
                while (cont == true) {
                    int condicionU = 0;
                    int condicionC = 0;
                    for (int i = 0; i < listaU.size(); i++) {
                        if (name.equals(listaU.get(i).correo)) {
                            condicionU += 1;
                            indice = i;
                        }
                        if (clave.equals(listaU.get(i).clave)) {
                            condicionC += 1;
                        }
                    }
                    if (condicionU == 1 && condicionC == 1) {
                        System.out.println("Bienvenido " + listaU.get(indice).nombre);
                        cont = false;
                    } else if (condicionU == 0 || condicionC == 0) {
                        System.out.println("Usuario o Clave son incorrectos");
                        System.out.print("Ingrese su nombre de usuario: ");
                        name = lectura.nextLine();
                        System.out.print("Clave: ");
                        clave = lectura.nextLine();
                    }
                }
                // Menu Principal
                Boolean continuar = true;
                while (continuar == true) {
                    System.out.println("Menu Principal");
                    System.out.println("[1] Ver notas");
                    System.out.println("[2] Crear notas");
                    System.out.println("[3] Editar notas");
                    System.out.println("[4] Eliminar notas");
                    System.out.println("[5] Ordenar por");
                    System.out.println("[6] Ver papelera");
                    System.out.println("[7] Cerrar sesion");
                    int elegido = Integer.parseInt(lectura.nextLine());

                    if (elegido == 1) { // Ver Notas
                        if (listaU.get(indice).listaNotas.size() == 0) {
                            System.out.println("No tiene notas registradas");
                        } else {
                            for (int i = 0; i < listaU.get(indice).listaNotas.size(); i++) {
                                System.out.println("[" + (i + 1) + "] '" + listaU.get(indice).listaNotas.get(i).titulo + "'");
                            }
                            System.out.print("Que nota desea ver?: ");
                            int verNota = Integer.parseInt(lectura.nextLine());
                            int temp = 0;
                            while (temp == 0) {
                                if (verNota < 1 || verNota > listaU.get(indice).listaNotas.size()) {
                                    System.out.println("Ingrese nuevamente");
                                    verNota = Integer.parseInt(lectura.nextLine());
                                } else {
                                    temp = 1;
                                }
                            }
                            System.out.println("Titulo: " + listaU.get(indice).listaNotas.get(verNota-1).titulo);
                            System.out.println("Fecha de creación: " + listaU.get(indice).listaNotas.get(verNota-1).fechaCreacion.dia
                                    + "/" + listaU.get(indice).listaNotas.get(verNota-1).fechaCreacion.mes + "/"
                                    + listaU.get(indice).listaNotas.get(verNota-1).fechaCreacion.anio);
                            System.out.println("Color: "+listaU.get(indice).listaNotas.get(verNota-1).color);
                            if (listaU.get(indice).listaNotas.get(verNota-1).prioridad == false){
                                System.out.println("Favorito: ★");
                            }else{
                                System.out.println("Favorito: ☆");
                            }
                            System.out.println("'"+listaU.get(indice).listaNotas.get(verNota-1).contenido+"'");
                            System.out.println("Tema: "+listaU.get(indice).listaNotas.get(verNota-1).temas);
                        }
                    }
                    if (elegido == 2) { // Crear Nota
                        System.out.print("Ingrese titulo: ");
                        String titulo = lectura.nextLine();
                        System.out.print("Escriba su nota: ");
                        String contenido = lectura.nextLine();
                        System.out.print("De que color desea su nota: ");
                        String color = lectura.nextLine();
                        System.out.println("Que tema desea agregarle?: ");
                        String temas = lectura.nextLine();
                        System.out.println("Desea marcar esta nota como favorita? [1] Si [0] No: ");
                        //verificar verificacion.
                        Boolean prioridad = false;
                        int fav = Integer.parseInt(lectura.nextLine());
                        int temp = 0;
                        while (temp == 0) {
                            if (fav < 0 || fav > 1) {
                                System.out.println("Intente nuevamente");
                                fav = Integer.parseInt(lectura.nextLine());
                            } else if (fav == 1) {
                                prioridad = false;
                                temp = 1;
                            }else if (fav == 0){
                                prioridad = true;
                                temp = 1;
                            }
                        }
                        Nota nota = new Nota(titulo, contenido, color, prioridad);
                        Fecha fCreacion = new Fecha(0,0,0);
                        fCreacion.fechaActual();
                        nota.temas = temas;
                        nota.fechaCreacion = fCreacion;
                        listaU.get(indice).listaNotas.add(nota);
                        System.out.println("Nota creada con exito");
                    }
                    if (elegido == 3) { // Editar Nota
                        Boolean bandera = true;
                        if (listaU.get(indice).listaNotas.size() == 0) {
                            System.out.println("No tiene notas registradas");
                        }else{
                            while (bandera == true) {
                                for (int i = 0; i < listaU.get(indice).listaNotas.size(); i++) {
                                    System.out.println("[" + (i + 1) + "] '" + listaU.get(indice).listaNotas.get(i).titulo + "'");
                                }
                                System.out.print("Que nota desea editar?: ");
                                int editNota = Integer.parseInt(lectura.nextLine());
                                int temp = 0;
                                while (temp == 0) {
                                    if (editNota < 1 || editNota > listaU.get(indice).listaNotas.size()) {
                                        System.out.println("Ingrese nuevamente: ");
                                        editNota = Integer.parseInt(lectura.nextLine());
                                    } else {
                                        temp = 1;
                                    }
                                }
                                System.out.println("Que desea editar: \n[1] Titulo\n[2] Contenido\n[3] Color\n[4] Temas\n[5] Favorito");
                                int edit = Integer.parseInt(lectura.nextLine());
                                temp = 0;
                                while (temp == 0) {
                                    if (edit < 0 || edit > 5) {
                                        System.out.println("Ingrese nuevamente: ");
                                        edit = Integer.parseInt(lectura.nextLine());
                                    } else {
                                        temp = 1;
                                    }
                                }

                                if (edit == 1) {
                                    System.out.print("Titulo nuevo: ");
                                    String tituloEdit = lectura.nextLine();
                                    listaU.get(indice).listaNotas.get(editNota - 1).editarTitulo(tituloEdit);
                                }
                                if (edit == 2) {
                                    System.out.print("Contenido nuevo:");
                                    String contenidoEdit = lectura.nextLine();
                                    listaU.get(indice).listaNotas.get(editNota - 1).editarContenido(contenidoEdit);
                                }
                                if (edit == 3) {
                                    System.out.print("Color nuevo: ");
                                    String colorEdit = lectura.nextLine();
                                    listaU.get(indice).listaNotas.get(editNota - 1).editarColor(colorEdit);
                                }
                                if (edit == 4) {
                                    System.out.println("Temas nuevos: ");
                                    String temasEdit = lectura.nextLine();
                                    listaU.get(indice).listaNotas.get(editNota - 1).editarTemas(temasEdit);
                                }
                                if (edit == 5) {
                                    System.out.println("Favorito? [1] Si [0] No");
                                    int fav = Integer.parseInt(lectura.nextLine());
                                    Boolean hecho = false;
                                    while (hecho == false) {
                                        if (fav == 1) {
                                            listaU.get(indice).listaNotas.get(editNota - 1).editarPrioridad(false);
                                            hecho = true;
                                        }
                                        if (fav == 0) {
                                            listaU.get(indice).listaNotas.get(editNota - 1).editarPrioridad(true);
                                            hecho = true;
                                        }
                                        if (fav > 1 || fav < 0) {
                                            System.out.println("Intente nuevamente");
                                            fav = Integer.parseInt(lectura.nextLine());
                                        }
                                    }
                                }
                                System.out.print("Desea seguir editando? [1] Si [0] No: ");
                                int Eseguir = Integer.parseInt(lectura.nextLine());
                                Boolean Econt = true;
                                while (Econt == true) {
                                    if (Eseguir == 0) {
                                        Econt = false;
                                        bandera = false;
                                    }
                                    if (Eseguir == 1) {
                                        Econt = false;
                                    } else if (Eseguir < 0 || Eseguir > 1) {
                                        System.out.println("Intente nuevamente");
                                        Eseguir = Integer.parseInt(lectura.nextLine());
                                    }
                                }
                            }
                        }
                    }
                    if (elegido == 4) { // Eliminar Nota
                        if (listaU.get(indice).listaNotas.size() == 0) {
                            System.out.println("No tiene notas registradas");
                        }else{
                            for (int i = 0; i < listaU.get(indice).listaNotas.size(); i++) {
                                System.out.println("[" + (i + 1) + "] '" + listaU.get(indice).listaNotas.get(i).titulo + "'");
                            }
                            System.out.print("Que nota desea eliminar?: ");
                            int elimNota = Integer.parseInt(lectura.nextLine());
                            int temp = 0;
                            while (temp == 0) {
                                if (elimNota < 1 || elimNota > listaU.get(indice).listaNotas.size()) {
                                    System.out.println("Ingrese nuevamente: ");
                                    elimNota = Integer.parseInt(lectura.nextLine());
                                } else {
                                    temp = 1;
                                }
                            }
                            listaU.get(indice).papelera.add(listaU.get(indice).listaNotas.get(elimNota - 1));
                            listaU.get(indice).listaNotas.remove(elimNota - 1);
                            System.out.println("La nota ha sido enviada a la papelera con exito");
                        }
                    }
                    if (elegido == 5) { // Ordenado por
                        if (listaU.get(indice).listaNotas.size()==0){
                            System.out.println("No tiene notas registradas");
                        }else {
                            Boolean bandera = true;
                            while (bandera == true) {
                                System.out.println("Ver ordenado por \n[1] Titulo.\n[2] Fecha de Creacion.\n[3] Favoritos.\n[4] Color.\n[5] Temas.\n[6] Volver al menu");
                                int opcOrdena = Integer.parseInt(lectura.nextLine());
                                int temp = 0;
                                while (temp == 0) {
                                    if (opcOrdena < 0 || opcOrdena > 6) {
                                        System.out.println("Ingrese nuevamente: ");
                                        opcOrdena = Integer.parseInt(lectura.nextLine());
                                    } else {
                                        temp = 1;
                                    }
                                }
                                if (opcOrdena == 1) { // Ver por Nombres
                                    ArrayList<Nota> clone = new ArrayList<>();
                                    for (int i=0;i<listaU.get(indice).listaNotas.size();i++){
                                        clone.add(listaU.get(indice).listaNotas.get(i));
                                    }
                                    Collections.sort(clone,
                                            (Nota p1, Nota p2) -> p1.titulo.compareTo(p2.titulo)
                                    );
                                    for (int i = 0; i < clone.size(); i++) {
                                        System.out.println("[" + (i + 1) + "] '" + clone.get(i).titulo + "'");
                                    }
                                    System.out.println("Que nota desea ver?: ");
                                    int verNotaO = Integer.parseInt(lectura.nextLine());
                                    int vernota = 0;
                                    while (temp == 0) {
                                        if (verNotaO < 1 || verNotaO > clone.size()) {
                                            System.out.println("Ingrese nuevamente: ");
                                            verNotaO = Integer.parseInt(lectura.nextLine());
                                        } else {
                                            vernota = 1;
                                        }
                                    }
                                    System.out.println("Titulo: " + clone.get(verNotaO-1).titulo);
                                    System.out.println("Fecha de creación: " + clone.get(verNotaO-1).fechaCreacion.dia
                                            + "/" + clone.get(verNotaO-1).fechaCreacion.mes + "/"
                                            + clone.get(verNotaO-1).fechaCreacion.anio);
                                    System.out.println("Color: "+clone.get(verNotaO-1).color);
                                    if (clone.get(verNotaO-1).prioridad == false){
                                        System.out.println("Favorito: ★");
                                    }else{
                                        System.out.println("Favorito: ☆");
                                    }
                                    System.out.println("'"+clone.get(verNotaO-1).contenido+"'");
                                    System.out.println(clone.get(verNotaO-1).temas);
                                    bandera = false;
                                }
                                if (opcOrdena == 2) { // Ver por Fecha de creacion
                                    ArrayList<Nota> clone = new ArrayList<>();
                                    for (int i=0;i<listaU.get(indice).listaNotas.size();i++){
                                        clone.add(listaU.get(indice).listaNotas.get(i));
                                    }

                                }
                                if (opcOrdena == 3) { // Ver por Prioridad
                                    ArrayList<Nota> clone = new ArrayList<>();
                                    ArrayList<Nota> temp2 = new ArrayList<>();
                                    int condicion = 0;
                                    for (int i=0;i<listaU.get(indice).listaNotas.size();i++){
                                        if (listaU.get(indice).listaNotas.get(i).prioridad==false){
                                            clone.add(listaU.get(indice).listaNotas.get(i));
                                        }else{
                                            temp2.add(listaU.get(indice).listaNotas.get(i));
                                            condicion++;
                                        }
                                    }
                                    if (condicion!=0){
                                        for (int i =0; i<condicion;i++){
                                            clone.add(temp2.get(i));
                                        }
                                    }
                                    for (int i = 0; i < listaU.get(indice).listaNotas.size(); i++) {
                                        System.out.println("[" + (i + 1) + "] '" + clone.get(i).titulo + "'");
                                    }
                                    bandera = false;
                                }
                                if (opcOrdena == 4) { // Ver por Color
                                    ArrayList<Nota> temp1 = new ArrayList<>();
                                    ArrayList<Nota> temp2 = new ArrayList<>();
                                    System.out.println("Escriba el color que desea ordenar: ");
                                    String Ccolor = lectura.nextLine();
                                    int condicion = 0;
                                    int condicion2 = 0;
                                    for (int i=0;i<listaU.get(indice).listaNotas.size();i++){
                                        if (Ccolor.toLowerCase().compareTo(listaU.get(indice).listaNotas.get(i).color.toLowerCase())==0){
                                            temp1.add(listaU.get(indice).listaNotas.get(i));
                                            condicion2++;
                                        }else{
                                            temp2.add(listaU.get(indice).listaNotas.get(i));
                                            condicion++;
                                        }
                                    }
                                    if (condicion !=0 && condicion2!=0){
                                        for (int i=0; i<condicion;i++){
                                            temp1.add(temp2.get(i));
                                        }
                                        for (int i = 0; i < listaU.get(indice).listaNotas.size(); i++) {
                                            System.out.println("[" + (i + 1) + "] '" + temp1.get(i).titulo + "'");
                                        }
                                        bandera=false;
                                    }else{
                                        System.out.println("No se ha encontrado el color");
                                        bandera=false;
                                    }
                                }
                                if (opcOrdena == 5) { // Ver por Temas
                                    ArrayList<Nota> temp1 = new ArrayList<>();
                                    ArrayList<Nota> temp2 = new ArrayList<>();
                                    System.out.println("Escriba el tema que desea ordenar: ");
                                    String Ccolor = lectura.nextLine();
                                    int condicion = 0;
                                    int condicion2 = 0;
                                    for (int i=0;i<listaU.get(indice).listaNotas.size();i++){
                                        if (Ccolor.toLowerCase().compareTo(listaU.get(indice).listaNotas.get(i).color.toLowerCase())==0){
                                            temp1.add(listaU.get(indice).listaNotas.get(i));
                                            condicion2++;
                                        }else{
                                            temp2.add(listaU.get(indice).listaNotas.get(i));
                                            condicion++;
                                        }
                                    }
                                    if (condicion !=0 && condicion2!=0){
                                        for (int i=0; i<condicion;i++){
                                            temp1.add(temp2.get(i));
                                        }
                                        for (int i = 0; i < listaU.get(indice).listaNotas.size(); i++) {
                                            System.out.println("[" + (i + 1) + "] '" + temp1.get(i).titulo + "'");
                                        }
                                        bandera=false;
                                    }else{
                                        System.out.println("No se ha encontrado el tema");
                                        bandera=false;
                                    }
                                }
                                if (opcOrdena == 6) { // Volver
                                    bandera=false;
                                }
                            }
                        }
                    }
                    if (elegido == 6) { // Papelera
                        Boolean Pbandera = true;
                        while (Pbandera == true) {
                            if (listaU.get(indice).papelera.size() == 0) {
                                System.out.println("No tiene notas en la papelera, volviendo al menu...");
                                Pbandera = false;
                            }else{
                                System.out.println("Que desea hacer?\n[0] Volver al Menu\n[1] Eliminar Nota\n[2] Eliminar Todo\n[3] Restaurar una Nota\n[4] Restaurar Todo");
                                int flag = Integer.parseInt(lectura.nextLine());
                                int temp = 0;
                                while (temp == 0) {
                                    if (flag < 0 || flag > 4) {
                                        System.out.println("Ingrese nuevamente: ");
                                        flag = Integer.parseInt(lectura.nextLine());
                                    } else {
                                        temp = 1;
                                    }
                                }
                                if (flag == 1) {
                                    for (int i = 0; i < listaU.get(indice).papelera.size(); i++) {
                                        System.out.println("[" + (i + 1) + "] '" + listaU.get(indice).papelera.get(i).titulo + "'");
                                    }
                                    System.out.print("Que nota desea eliminar: ");
                                    int Poption = Integer.parseInt(lectura.nextLine());
                                    temp = 0;
                                    while (temp == 0) {
                                        if (Poption < 0 || Poption > 4) {
                                            System.out.println("Ingrese nuevamente: ");
                                            Poption = Integer.parseInt(lectura.nextLine());
                                        } else {
                                            temp = 1;
                                        }
                                    }
                                    listaU.get(indice).papelera.remove(Poption - 1);
                                    System.out.println("La nota ha sido removida con exito");
                                }
                                if (flag == 2) {
                                    System.out.println("Esta seguro de eliminar todo? [1] Si [2] No");
                                    int Poption = Integer.parseInt(lectura.nextLine());
                                    temp = 0;
                                    while (temp == 0) {
                                        if (Poption < 1 || Poption > 2) {
                                            System.out.println("Ingrese nuevamente: ");
                                            Poption = Integer.parseInt(lectura.nextLine());
                                        } else {
                                            temp = 1;
                                        }
                                    }
                                    if (Poption == 1){
                                        int Etamaño = listaU.get(indice).papelera.size();
                                        for (int i = 0; i < Etamaño; i++) {
                                            listaU.get(indice).papelera.remove(0);
                                        }
                                        System.out.println("Las notas han sido eliminadas correctamente");
                                    }
                                }
                                if (flag == 3) {
                                    for (int i = 0; i < listaU.get(indice).papelera.size(); i++) {
                                        System.out.println("[" + (i + 1) + "] '" + listaU.get(indice).papelera.get(i).titulo + "'");
                                    }
                                    System.out.print("Que nota desea restaurar: ");
                                    int Poption = Integer.parseInt(lectura.nextLine());
                                    temp = 0;
                                    while (temp == 0) {
                                        if (Poption < 0 || Poption > 4) {
                                            System.out.println("Ingrese nuevamente: ");
                                            Poption = Integer.parseInt(lectura.nextLine());
                                        } else {
                                            temp = 1;
                                        }
                                    }
                                    listaU.get(indice).listaNotas.add(listaU.get(indice).papelera.get(Poption - 1));
                                    listaU.get(indice).papelera.remove(Poption - 1);
                                    System.out.println("La nota ha sido restaurada con exito");
                                }
                                if (flag == 4) {
                                    System.out.println("Esta seguro de restaurar todo? [1] Si [2] No");
                                    int Poption = Integer.parseInt(lectura.nextLine());
                                    temp = 0;
                                    while (temp == 0) {
                                        if (Poption < 1 || Poption > 2) {
                                            System.out.println("Ingrese nuevamente: ");
                                            Poption = Integer.parseInt(lectura.nextLine());
                                        } else {
                                            temp = 1;
                                        }
                                    }
                                    if (Poption == 1) {
                                        int tamaño = listaU.get(indice).papelera.size();
                                        for (int i = 0; i < listaU.get(indice).papelera.size(); i++) {
                                            listaU.get(indice).listaNotas.add(listaU.get(indice).papelera.get(i));
                                        }
                                        for (int i = 0; i < tamaño; i++) {
                                            listaU.get(indice).papelera.remove(0);
                                        }
                                        System.out.println("Las notas han sido restauradas con exito");
                                    }
                                }
                                System.out.println("Desea seguir revisando la papelera? [1] Si [0] No");
                                int Eseguir = Integer.parseInt(lectura.nextLine());
                                Boolean Econt = true;
                                while (Econt == true) {
                                    if (Eseguir == 0) {
                                        Econt = false;
                                        Pbandera = false;
                                    }
                                    if (Eseguir == 1) {
                                        Econt = false;
                                    } else if (Eseguir < 0 || Eseguir > 1) {
                                        System.out.println("Intente nuevamente");
                                        Eseguir = Integer.parseInt(lectura.nextLine());
                                    }
                                }
                            }
                        }
                    }
                    if (elegido == 7) { // Cerrar Sesion
                            continuar = false;
                    }
                    if (elegido < 1 || elegido > 7) {
                        System.out.println("Opcion Incorrecta");
                        opcion = Integer.parseInt(lectura.nextLine());
                        continuar = false;
                    }
                }
            }
            if (opcion == 2) {
                System.out.print("Ingrese su nombre: ");
                String nombre = lectura.nextLine();
                System.out.print("Ingrese su apellido: ");
                String apellido = lectura.nextLine();
                System.out.print("Ingrese su numero de matricula: ");
                int numeroMatricula = Integer.parseInt(lectura.nextLine());
                System.out.print("Ingrese su numero de telefono (Respete usando + y codigo del pais): ");
                String numeroTelefono = lectura.nextLine();
                System.out.println("Ingrese su fecha de nacimiento: ");
                System.out.print("Dia: ");
                int dia = Integer.parseInt(lectura.nextLine());
                System.out.print("Mes: ");
                int mes = Integer.parseInt(lectura.nextLine());
                System.out.print("Anio: ");
                int anio = Integer.parseInt(lectura.nextLine());
                Fecha fechaNacimiento = new Fecha(dia, mes, anio);
                Usuario usuario = new Usuario(numeroMatricula, nombre, apellido, numeroTelefono, fechaNacimiento);
                listaU.add(usuario);
                System.out.println("Cuenta Registrada con exito!!");
                listaU.get(listaU.size() - 1).generarCorreo(nombre, apellido);
                listaU.get(listaU.size() - 1).generarClave(nombre, apellido, numeroMatricula);
                ArrayList<Nota> listaN = new ArrayList<>();
                listaU.get(listaU.size() - 1).listaNotas = listaN;
                ArrayList<Nota> listaP = new ArrayList<>();
                listaU.get(listaU.size() - 1).papelera = listaP;

            }
            if (opcion == 3) {
                iniciar = false;
            }
            if (opcion < 1 || opcion > 3) {
                System.out.println("Opcion Incorrecta");
                opcion = Integer.parseInt(lectura.nextLine());

            }
        }
        serial.Serializacion(listaU);
    }
}
