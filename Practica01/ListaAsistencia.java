import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ListaAsistencia {

    public static void main(String[] args) {
        String nombre = "Juan Pérez - Presente\n";
        byte[] datos = nombre.getBytes();
        int repeticiones = 100_000;

        // ---------------------------------------------------------
        // 1. Escritura directa con FileOutputStream (Sin buffer)
        // ---------------------------------------------------------
        System.out.println("Iniciando escritura con FileOutputStream...");
        try (FileOutputStream fos = new FileOutputStream("asistencia_fos.txt")) {

            long inicio = System.nanoTime();

            for (int i = 0; i < repeticiones; i++) {
                fos.write(datos);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;
            System.out.println("Duración (FileOutputStream): " + duracionNs + " ns (" + duracionMs + " ms)");

        } catch (IOException e) {
            System.err.println("Error al escribir con FileOutputStream: " + e.getMessage());
        }

        // ---------------------------------------------------------
        // 2. Escritura optimizada con BufferedOutputStream (Con buffer)
        // ---------------------------------------------------------
        System.out.println("\nIniciando escritura con BufferedOutputStream...");
        try (FileOutputStream fos = new FileOutputStream("asistencia_bos.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            long inicio = System.nanoTime();

            for (int i = 0; i < repeticiones; i++) {
                bos.write(datos);
            }
            bos.flush(); // Vaca el contenido restante del buffer al archivo

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;
            System.out.println("Duración (BufferedOutputStream): " + duracionNs + " ns (" + duracionMs + " ms)");

        } catch (IOException e) {
            System.err.println("Error al escribir con BufferedOutputStream: " + e.getMessage());
        }
    }
}