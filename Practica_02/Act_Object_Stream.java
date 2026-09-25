package Practica_02;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Act_Object_Stream {
  public static void escribirObjectoTryWith(){
        try (OutputStream fos = new FileOutputStream("object_data.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos);){
            ArrayList<String> listaAsistencia = new ArrayList<>();
            listaAsistencia.add("Arturo");
            listaAsistencia.add("Cecilia");
            listaAsistencia.add("Diego");
            listaAsistencia.add("Fernando");

            oos.writeBoolean(true);
            oos.writeUTF("Hola Mundo!");
            oos.writeObject(listaAsistencia);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void leerObjectoTryWith(){
        try (InputStream fis = new FileInputStream("object_data.txt");
             ObjectInputStream ois = new ObjectInputStream(fis);){

            System.out.println(ois.readBoolean());
            System.out.println(ois.readUTF());
            ArrayList<String> ListaAsistenciaInput = (ArrayList<String>) ois.readObject();
            System.out.println("Total de Estudiantes: "+ListaAsistenciaInput.size());
        } catch (IOException e){
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
