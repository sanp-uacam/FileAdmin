package parcial02.practica02;

import java.io.*;
import java.util.Scanner;

public class Main {

    // Metodo auxiliar para encontrar la ruta del archivo ya sea en la raiz o en la carpeta
    private static File obtenerArchivo(String nombre) {
        File f1 = new File(nombre);
        if (f1.exists()) {
            return f1;
        }
        File f2 = new File("parcial02/practica02/" + nombre);
        if (f2.exists()) {
            return f2;
        }
        File dir = new File("parcial02/practica02");
        if (dir.isDirectory()) {
            return new File("parcial02/practica02/" + nombre);
        }
        return f1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("===== PRACTICA 02: STREAMS DE TEXTO Y BINARIOS CON BUFFER =====");
        System.out.println("Alumno: Arturo Perez Kantun | Matricula: 56967");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Escribir texto (comparar BufferedWriter vs FileWriter)");
            System.out.println("2. Leer todo el archivo de texto (BufferedReader)");
            System.out.println("3. Escribir numeros (comparar BufferedOutputStream vs FileOutputStream)");
            System.out.println("4. Leer numeros (comparar BufferedInputStream vs FileInputStream)");
            System.out.println("5. Salir");
            System.out.print("Elige una opcion: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        escribirTexto();
                        break;
                    case 2:
                        leerTexto();
                        break;
                    case 3:
                        escribirNumeros();
                        break;
                    case 4:
                        leerNumeros();
                        break;
                    case 5:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Opcion no valida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un numero valido.");
            }
        } while (opcion != 5);

        scanner.close();
    }

    // 1. Leer todo el archivo de texto linea por linea
    static void leerTexto() {
        File archivo = obtenerArchivo("data.txt");
        System.out.println("\n--- Leyendo archivo: " + archivo.getName() + " ---");

        try (Reader r = new FileReader(archivo);
             BufferedReader br = new BufferedReader(r)) {

            String linea;
            int contador = 0;

            // Bucle para leer todo el archivo completo hasta que llegue a null
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
                contador++;
            }

            System.out.println("\n>> Se leyeron con exito " + contador + " lineas.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontro el archivo data.txt");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // 2. Escribir texto midiendo tiempos con buffer y sin buffer
    static void escribirTexto() {
        File archivo = obtenerArchivo("data.txt");
        System.out.println("\n--- Escribiendo 1000 lineas de texto ---");

        // Con BufferedWriter
        long inicioCon = System.nanoTime();
        try (Writer w = new FileWriter(archivo);
             BufferedWriter bw = new BufferedWriter(w)) {

            for (int i = 0; i <= 1000; i++) {
                bw.write("File Admin" + i + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir con buffer: " + e.getMessage());
        }
        long finCon = System.nanoTime();
        double msCon = (finCon - inicioCon) / 1_000_000.0;
        System.out.printf("Tiempo con BufferedWriter: %.3f ms\n", msCon);

        // Sin buffer (FileWriter directo)
        File archivoSin = obtenerArchivo("data_temp.txt");
        long inicioSin = System.nanoTime();
        try (Writer wDirecto = new FileWriter(archivoSin)) {
            for (int i = 0; i <= 1000; i++) {
                wDirecto.write("File Admin" + i + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error al escribir sin buffer: " + e.getMessage());
        }
        long finSin = System.nanoTime();
        double msSin = (finSin - inicioSin) / 1_000_000.0;
        System.out.printf("Tiempo con FileWriter directo: %.3f ms\n", msSin);

        archivoSin.delete(); // Borramos el temporal
    }

    // Alias por si el profesor lo llama por el nombre original con typo
    static void ecribirTexto() {
        escribirTexto();
    }

    // 3. Escribir numeros midiendo tiempos con y sin buffer
    static void escribirNumeros() {
        File archivo = obtenerArchivo("numeros.txt");
        System.out.println("\n--- Escribiendo numeros (0 a 1000) ---");

        // Con BufferedOutputStream
        long inicio = System.nanoTime();
        try (FileOutputStream fos = new FileOutputStream(archivo);
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            for (int i = 0; i <= 1000; i++) {
                bos.write(i);
            }
        } catch (IOException ex) {
            System.out.println("Error con buffer: " + ex.getMessage());
        }
        long fin = System.nanoTime();
        double duracionMs = (fin - inicio) / 1_000_000.0;
        System.out.printf("Tiempo con BufferedOutputStream: %.3f ms\n", duracionMs);

        // Sin buffer (FileOutputStream directo)
        File archivoSin = obtenerArchivo("numeros_temp.txt");
        long inicioSin = System.nanoTime();
        try (FileOutputStream fosDirecto = new FileOutputStream(archivoSin)) {
            for (int i = 0; i <= 1000; i++) {
                fosDirecto.write(i);
            }
        } catch (IOException ex) {
            System.out.println("Error sin buffer: " + ex.getMessage());
        }
        long finSin = System.nanoTime();
        double msSin = (finSin - inicioSin) / 1_000_000.0;
        System.out.printf("Tiempo con FileOutputStream directo: %.3f ms\n", msSin);

        archivoSin.delete(); // Borramos el temporal
    }

    // 4. Leer numeros midiendo tiempos con y sin buffer
    static void leerNumeros() {
        File archivo = obtenerArchivo("numeros.txt");
        System.out.println("\n--- Leyendo numeros de: " + archivo.getName() + " ---");

        // Con BufferedInputStream
        long inicioCon = System.nanoTime();
        int bytesLeidosCon = 0;
        try (FileInputStream fis = new FileInputStream(archivo);
             BufferedInputStream bis = new BufferedInputStream(fis)) {

            int octeto;
            while ((octeto = bis.read()) != -1) {
                bytesLeidosCon++;
            }
        } catch (IOException e) {
            System.out.println("Error con buffer: " + e.getMessage());
        }
        long finCon = System.nanoTime();
        double msCon = (finCon - inicioCon) / 1_000_000.0;
        System.out.printf("Tiempo con BufferedInputStream (%d bytes): %.3f ms\n", bytesLeidosCon, msCon);

        // Sin buffer (FileInputStream directo)
        long inicioSin = System.nanoTime();
        int bytesLeidosSin = 0;
        try (FileInputStream fisDirecto = new FileInputStream(archivo)) {
            int octeto;
            while ((octeto = fisDirecto.read()) != -1) {
                bytesLeidosSin++;
            }
        } catch (IOException e) {
            System.out.println("Error sin buffer: " + e.getMessage());
        }
        long finSin = System.nanoTime();
        double msSin = (finSin - inicioSin) / 1_000_000.0;
        System.out.printf("Tiempo con FileInputStream directo (%d bytes): %.3f ms\n", bytesLeidosSin, msSin);
    }
}
