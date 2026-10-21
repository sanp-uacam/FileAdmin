import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ComparacionStreams {
    public static void main(String[] args) {
        String texto = "Esta es una línea de prueba para medir el rendimiento de escritura.\n";
        int iteraciones = 100_000;
        
        File archivoFileOut = new File("salida_file.txt");
        File archivoBufferedOut = new File("salida_buffered.txt");

        // 1. Prueba con FileOutputStream (sin buffer)
        long inicioFile = System.nanoTime();
        try (FileOutputStream fos = new FileOutputStream(archivoFileOut)) {
            for (int i = 0; i < iteraciones; i++) {
                fos.write(texto.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long finFile = System.nanoTime();
        long tiempoFile = (finFile - inicioFile) / 1_000_000; // Convertir a milisegundos

        // 2. Prueba con BufferedOutputStream (con buffer)
        long inicioBuffered = System.nanoTime();
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(archivoBufferedOut))) {
            for (int i = 0; i < iteraciones; i++) {
                bos.write(texto.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long finBuffered = System.nanoTime();
        long tiempoBuffered = (finBuffered - inicioBuffered) / 1_000_000; // Convertir a milisegundos

        // Resultados
        System.out.println("--- RESULTADOS DE RENDIMIENTO ---");
        System.out.println("Tiempo con FileOutputStream: " + tiempoFile + " ms");
        System.out.println("Tiempo con BufferedOutputStream: " + tiempoBuffered + " ms");
        
        if (tiempoBuffered < tiempoFile) {
            long diferencia = tiempoFile - tiempoBuffered;
            System.out.println("BufferedOutputStream fue más rápido por aproximadamente " + diferencia + " ms.");
        }
        
        // Limpieza de archivos temporales
        archivoFileOut.delete();
        archivoBufferedOut.delete();
    }
}