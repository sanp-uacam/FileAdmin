//Jaime Michel Garcia Sostenes
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Act_Stream {

    // Nombre del archivo unificado para toda la práctica
    private static final String ARCHIVO = "ListaAsitencia.txt";

    // --------------------------------------------------------
    // MÉTODOS PARA EL MENÚ (Practica_01)
    // --------------------------------------------------------

    public static void escribirNota(String nota) throws IOException {
        // 'true' para agregar al final sin borrar lo anterior
        OutputStream fos = new FileOutputStream(ARCHIVO, true);

        String linea = nota + "\n";
        fos.write(linea.getBytes());

        fos.flush();
        fos.close();
    }

    public static String leerNotas() throws IOException {
        InputStream fis = new FileInputStream(ARCHIVO);
        StringBuilder contenido = new StringBuilder();
        int byteLeido;

        // Leemos byte por byte hasta el final (-1)
        while ((byteLeido = fis.read()) != -1) {
            contenido.append((char) byteLeido);
        }

        fis.close();
        return contenido.toString();
    }

    // --------------------------------------------------------
    // MÉTODO ORIGINAL (Del 1 al 10 y nombre en ASCII)
    // --------------------------------------------------------

    public static void escribir() throws IOException {
        OutputStream fos = new FileOutputStream(ARCHIVO, true);

        // Escribe del 1 al 10
        for (int i = 1; i <= 10; i++) {
            String linea = i + "\n";
            fos.write(linea.getBytes());
        }

        // Escribe "Jaime" usando códigos ASCII
        fos.write(74);  // J
        fos.write(97);  // a
        fos.write(105); // i
        fos.write(109); // m
        fos.write(101); // e
        fos.write(10);  // Salto de línea (\n)

        fos.flush();
        fos.close();
    }
}