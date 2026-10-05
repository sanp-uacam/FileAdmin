import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Practica_01 {

    // Método principal del menú, llamado desde tu clase Main
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Ver todas las notas");
            System.out.println("3. Calcular promedio");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer de entrada

                switch (opcion) {
                    case 1:
                        agregarNota(scanner);
                        break;
                    case 2:
                        verNotas();
                        break;
                    case 3:
                        double promedio = calcularPromedio();
                        if (promedio > 0) {
                            System.out.println("Promedio: " + String.format("%.2f", promedio));
                        }
                        break;
                    case 4:
                        salir = true;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número.");
                scanner.nextLine(); // Limpiar el buffer para evitar bucle infinito
            }
        }
        scanner.close();
    }

    // Tarea 1: Guardar la nota manejando IOException y usando tu método escribirNota
    public static void agregarNota(Scanner scanner) {
        System.out.print("Ingresa la nota: ");
        String nota = scanner.nextLine();

        try {
            Act_Stream.escribirNota(nota);
            System.out.println("Nota guardada.");
        } catch (IOException e) {
            System.out.println("Error al guardar la nota: " + e.getMessage());
        }
    }

    // Tarea 2: Corregir el scope de la variable y agregar el mensaje de error
    public static void verNotas() {
        String notas = ""; // Se declara fuera del try

        try {
            notas = Act_Stream.leerNotas();
            System.out.println("\n--- Notas Registradas ---");
            System.out.println(notas);
        } catch (IOException e) {
            System.out.println("Error al leer las notas: " + e.getMessage());
        }
    }

    // Tarea 3: Calcular promedio manejando NumberFormatException y IOException
    public static double calcularPromedio() {
        try {
            String contenido = Act_Stream.leerNotas();

            if (contenido == null || contenido.trim().isEmpty()) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            // Separar el string devuelto por leerNotas() usando los saltos de línea
            String[] lineas = contenido.split("\\r?\\n");
            double suma = 0;
            int contador = 0;

            for (String linea : lineas) {
                if (!linea.trim().isEmpty()) {
                    suma += Double.parseDouble(linea.trim());
                    contador++;
                }
            }

            if (contador == 0) {
                System.out.println("No hay notas numéricas registradas.");
                return 0;
            }

            return suma / contador;

        } catch (IOException e) {
            System.out.println("Error al acceder al archivo: " + e.getMessage());
            return 0;
        } catch (NumberFormatException e) {
            // Se captura si el archivo original ("ListaAsitencia.txt") tenía texto (por ejemplo, el nombre ASCII)
            System.out.println("Error: El archivo contiene datos no numéricos que no se pueden promediar.");
            return 0;
        }
    }
}