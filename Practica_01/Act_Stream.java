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

    // Guardar una nota agregando salto de línea
    public static void guardarNota(String nota) throws IOException {
        OutputStream fos = new FileOutputStream("notas.txt", true);
        byte[] datos = (nota + "\n").getBytes();
        fos.write(datos);
        fos.flush();
        fos.close();
    }

    // Leer todas las notas del archivo y devolverlas como texto
    public static String leerNotas() throws IOException {
        File archivo = new File("notas.txt");
        if (!archivo.exists()) {
            return "";
        }
        InputStream fis = new FileInputStream(archivo);
        StringBuilder sb = new StringBuilder();
        int byteFile;
        while ((byteFile = fis.read()) != -1) {
            sb.append((char) byteFile);
        }
        fis.close();
        return sb.toString();
    }
}
