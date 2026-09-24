import java.io.IOException;
import java.util.Scanner;

public class Practica_01 {
    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("===== SISTEMA DE NOTAS =====");

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Ver todas las notas");
            System.out.println("3. Calcular promedio");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarNota(scanner);
                        break;
                    case 2:
                        verNotas();
                        break;
                    case 3:
                        double promedio = calcularPromedio();
                        System.out.println("Promedio: " + promedio);
                        break;
                    case 4:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
                opcion = 0; // Reiniciar para continuar
            }
        } while (opcion != 4);

        scanner.close();
    }

    private static void agregarNota(Scanner scanner) {
        System.out.print("Escribe tu nota: ");
        String nota = scanner.nextLine();
        try {
            Act_Stream.guardarNota(nota);
            System.out.println("Nota guardada.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }


    private static void verNotas() {
        String notas = "";
        try {
            notas = Act_Stream.leerNotas();
        } catch (IOException e) {
            System.out.println("Error al leer: " + e.getMessage());
        }

        System.out.println(notas);
    }

    private static double calcularPromedio() {
        try {
            String notas = Act_Stream.leerNotas();

            // Si el archivo está vacío o no hay notas
            if (notas == null || notas.trim().isEmpty()) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            // Separamos por líneas para obtener cada nota
            String[] lineas = notas.split("\n");
            double suma = 0;
            int totalNotas = 0;

            for (int i = 0; i < lineas.length; i++) {
                String linea = lineas[i].trim();
                // Verificamos que no sea una línea vacía
                if (!linea.isEmpty()) {
                    double nota = Double.parseDouble(linea);
                    suma = suma + nota;
                    totalNotas++;
                }
            }

            if (totalNotas == 0) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            return suma / totalNotas;

        } catch (IOException e) {
            System.out.println("Error al leer las notas: " + e.getMessage());
            return 0;
        } catch (NumberFormatException e) {
            System.out.println("Error en el formato de una nota: " + e.getMessage());
            return 0;
        }
    }

    public static void main(String[] args) {
        ejecutar();
    }
}