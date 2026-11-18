import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscrituraBuffered {

    public static void main(String[] args) {
        int[] tamanos = {100_000, 1_000_000, 10_000_000};
        byte dato = 'A';

        System.out.println("=== PRÁCTICA 1: MEDICIÓN DE TIEMPO DE EJECUCIÓN ===");

        for (int n : tamanos) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("Pruebas para n = " + n + " bytes");
            System.out.println("--------------------------------------------------");

            // Medición con BufferedOutputStream
            probarConBuffer(n, dato);

            // Medición con FileOutputStream directo
            probarSinBuffer(n, dato);
        }
    }

    public static void probarConBuffer(int n, byte dato) {
        String ruta = "archivo_salida_buffered.dat";
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ruta))) {
            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                bos.write(dato);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Con Buffer -> Duración: " + duracionNs + " ns (" + duracionMs + " ms)");
        } catch (IOException e) {
            System.err.println("Error al escribir con buffer: " + e.getMessage());
        }
    }

    public static void probarSinBuffer(int n, byte dato) {
        String ruta = "archivo_salida_directo.dat";
        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                fos.write(dato);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Sin Buffer -> Duración: " + duracionNs + " ns (" + duracionMs + " ms)");
        } catch (IOException e) {
            System.err.println("Error al escribir sin buffer: " + e.getMessage());
        }
    }
}
