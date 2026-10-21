package Practica_02;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("===== LISTA DE ASISTENCIA =====");

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar alumno a asistencia");
            System.out.println("2. Ver lista de alumnos");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        agregarAlumnoAsitencia(scanner);
                        break;
                    case 2:
                        verVerAlumno();
                        break;
                    case 3:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número.");
                opcion = 0; // Reiniciar para continuar
            }
        } while (opcion != 3);

        scanner.close();
    }

    private static void agregarAlumnoAsitencia(Scanner scanner) {
        System.out.print("Escribe el nombre del alumno: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        // Validamos que el nombre no contenga números
        boolean tieneNumeros = false;
        for (int i = 0; i < nombre.length(); i++) {
            if (Character.isDigit(nombre.charAt(i))) {
                tieneNumeros = true;
                break;
            }
        }

        if (tieneNumeros) {
            System.out.println("Error: El nombre no puede contener números.");
            return;
        }

        Act_Object_Stream.agregarAlumnoAsitencia(nombre);
    }

    private static void verVerAlumno() {
        Act_Object_Stream.verVerAlumno();
    }
}