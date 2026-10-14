import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class EscrituraBuffered {

    // Método para medir el tiempo de escritura usando BufferedOutputStream
    public static double medirConBuffer(int n, String ruta) {
        byte dato = 'A';
        long duracionNs = 0;

        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(ruta))) {
            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                bos.write(dato);
            }

            long fin = System.nanoTime();
            duracionNs = fin - inicio;

        } catch (IOException e) {
            System.err.println("Error al escribir con buffer: " + e.getMessage());
        }

        // Convertimos de nanosegundos a milisegundos
        return duracionNs / 1_000_000.0;
    }

    // Método para medir el tiempo de escritura usando FileOutputStream directo
    public static double medirSinBuffer(int n, String ruta) {
        byte dato = 'A';
        long duracionNs = 0;

        try (FileOutputStream fos = new FileOutputStream(ruta)) {
            long inicio = System.nanoTime();

            for (int i = 0; i < n; i++) {
                fos.write(dato);
            }

            long fin = System.nanoTime();
            duracionNs = fin - inicio;

        } catch (IOException e) {
            System.err.println("Error al escribir sin buffer: " + e.getMessage());
        }

        // Convertimos de nanosegundos a milisegundos
        return duracionNs / 1_000_000.0;
    }

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println(" COMPARACION: BufferedOutputStream vs FileOutputStream");
        System.out.println(" Alumno: Arturo Perez Kantun | Matricula: 56967");
        System.out.println("=============================================================\n");

        int[] tamanos = {100_000, 1_000_000, 10_000_000};
        int repeticiones = 3;

        for (int n : tamanos) {
            System.out.println("-------------------------------------------------------------");
            System.out.println(" Probando con n = " + n + " bytes");
            System.out.println("-------------------------------------------------------------");

            // Medición CON buffer
            double sumaBuffer = 0;
            for (int i = 1; i <= repeticiones; i++) {
                String ruta = "temp_buffer_" + n + ".dat";
                double tiempoMs = medirConBuffer(n, ruta);
                sumaBuffer += tiempoMs;
                System.out.printf("  [Con Buffer] Ejecucion %d: %.3f ms\n", i, tiempoMs);
                new File(ruta).delete(); // Limpiamos el archivo temporal
            }
            double promBuffer = sumaBuffer / repeticiones;
            System.out.printf("  >> Promedio CON buffer: %.3f ms\n\n", promBuffer);

            // Medición SIN buffer
            double sumaSinBuffer = 0;
            for (int i = 1; i <= repeticiones; i++) {
                String ruta = "temp_sin_buffer_" + n + ".dat";
                double tiempoMs = medirSinBuffer(n, ruta);
                sumaSinBuffer += tiempoMs;
                System.out.printf("  [Sin Buffer] Ejecucion %d: %.3f ms\n", i, tiempoMs);
                new File(ruta).delete(); // Limpiamos el archivo temporal
            }
            double promSinBuffer = sumaSinBuffer / repeticiones;
            System.out.printf("  >> Promedio SIN buffer: %.3f ms\n", promSinBuffer);

            double vecesMasRapido = promSinBuffer / promBuffer;
            System.out.printf("  ==> Con buffer fue %.1f veces mas rapido!\n\n", vecesMasRapido);
        }

        System.out.println("=============================================================");
        System.out.println(" Mediciones completadas exitosamente.");
        System.out.println("=============================================================");
    }
}
