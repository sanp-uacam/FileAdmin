/*Facultad de Ingeniería
 ISC
 Administracion de Archivos
Diego Manuel tello Ac
Sergio A Noh Puch
11/09/2026
*/ 

import java.io.*;

public class Act_Stream {
    public static void escribir( ) throws IOException {
        OutputStream fos = new FileOutputStream("ListaAsistencia.txt", true);
        fos.write(0x31);
        fos.write(50);
        fos.write(0x0A);

        fos.flush();
        fos.close();
    }

    public static void leer() throws IOException {
        InputStream fis = new FileInputStream("ListaAsistencia.txt");
        int byteFile;
        byteFile = fis.read();
        System.out.println(byteFile);
        fis.close();
    }

    public static void guardarNota (String nota) {
       System.out.println("guardar Nota");
       try (OutputStream fos = new FileOutputStream("calificaciones.txt", true)) {
        byte[]byteNotas = nota.getBytes();
        fos.write(byteNota);
        fos.write(0x0A);
        fos.flush();
   }catch (IOException e) {
        System.out.println("Ocurrio un error al guardar la nota: " + e.getMessage());
    }
 }
   public static String leerNotas() {
    File archivo = new File ("calificaciones.txt");
    if (!archivo.exists()) {
        return "No hay notas guardadas.";
    }
    StringBuilder contenido = new StringBuilder();
    try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
    } catch (IOException e) {
        return "Ocurrió un error al leer las notas: " + e.getMessage();
    }
    return contenido.toString().trim();
   }

}