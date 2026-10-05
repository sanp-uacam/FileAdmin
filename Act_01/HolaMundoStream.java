import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class HolaMundoStream {
    public static void main(String[] args) {
        String rutaArchivo = "holamundo.txt";
        String mensaje = "¡Hola, Mundo!";

        // 1. Escribir en el archivo usando FileOutputStream
        try (FileOutputStream fos = new FileOutputStream(rutaArchivo)) {
            // Convertimos la cadena a bytes en UTF-8
            byte[] bytesEscritura = mensaje.getBytes(StandardCharsets.UTF_8);
            fos.write(bytesEscritura);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 2. Leer del archivo usando FileInputStream
        try (FileInputStream fis = new FileInputStream(rutaArchivo)) {
            // Leemos todos los bytes del flujo (Java 9+)
            byte[] bytesLectura = fis.readAllBytes();

            // Convertimos los bytes de vuelta a String
            String contenido = new String(bytesLectura, StandardCharsets.UTF_8);
            System.out.println(contenido);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}