import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Cabrera_72980_Practica_01 {
    public static void main(String[] args) {
        String nombreArchivo = "asistencia.txt";
        String registroBase = "72980 - Cabrera - Presente";
        int totalLineas = 100000;

        // Medición de tiempo de inicio
        long inicio = System.nanoTime();

        // Escritura eficiente con BufferedWriter y manejo de excepciones
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo))) {
            
            for (int i = 1; i <= totalLineas; i++) {
                bw.write(i + " - " + registroBase);
                bw.newLine();
            }

            System.out.println("Archivo '" + nombreArchivo + "' generado exitosamente.");

        } catch (IOException e) {
            System.err.println("Error de I/O al escribir el archivo: " + e.getMessage());
            e.printStackTrace();
        }

        // Medición de tiempo final
        long fin = System.nanoTime();
        long duracionNs = fin - inicio;
        double duracionMs = duracionNs / 1_000_000.0;

        System.out.println("Duración: " + duracionNs + " ns (" + duracionMs + " ms)");
    }
}