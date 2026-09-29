package Practica_03;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("===== GESTIÓN DE ALUMNOS (ARCHIVOS BINARIOS) =====");

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Ver todos los alumnos");
            System.out.println("2. Buscar alumno por matrícula");
            System.out.println("3. Agregar nuevo alumno");
            System.out.println("4. Eliminar alumno");
            System.out.println("5. Calcular promedio grupal");
            System.out.println("6. Alumno con mejor promedio");
            System.out.println("7. Salir");
            System.out.print("Elige una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine().trim());

                switch (opcion) {
                    case 1:
                        GestionAlumnos.mostrarAlumnos();
                        break;
                    case 2:
                        buscarAlumno(scanner);
                        break;
                    case 3:
                        agregarNuevoAlumno(scanner);
                        break;
                    case 4:
                        eliminarAlumno(scanner);
                        break;
                    case 5:
                        double promedio = GestionAlumnos.promedioGrupal();
                        System.out.printf("Promedio grupal: %.2f\n", promedio);
                        break;
                    case 6:
                        Alumno mejor = GestionAlumnos.mejorPromedio();
                        if (mejor != null) {
                            System.out.println("Alumno con mejor promedio: " + mejor);
                        } else {
                            System.out.println("No hay alumnos registrados.");
                        }
                        break;
                    case 7:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Debes ingresar un número válido.");
                opcion = 0;
            }
        } while (opcion != 7);

        scanner.close();
    }

    private static void buscarAlumno(Scanner scanner) {
        System.out.print("Ingresa la matrícula a buscar: ");
        String matricula = scanner.nextLine().trim();
        if (matricula.isEmpty()) {
            System.out.println("La matrícula no puede estar vacía.");
            return;
        }
        GestionAlumnos.mostrarBusqueda(matricula);
    }

    private static void agregarNuevoAlumno(Scanner scanner) {
        try {
            System.out.print("Ingresa la matrícula: ");
            String matricula = scanner.nextLine().trim();

            System.out.print("Ingresa el nombre: ");
            String nombre = scanner.nextLine().trim();

            System.out.print("Ingresa el promedio (0 - 10): ");
            double promedio = Double.parseDouble(scanner.nextLine().trim());

            if (matricula.isEmpty() || nombre.isEmpty()) {
                System.out.println("La matrícula y el nombre no pueden estar vacíos.");
                return;
            }

            Alumno nuevo = new Alumno(matricula, nombre, promedio);
            GestionAlumnos.agregarAlumno(nuevo);

        } catch (NumberFormatException e) {
            System.out.println("Error: El promedio debe ser un número válido.");
        }
    }

    private static void eliminarAlumno(Scanner scanner) {
        System.out.print("Ingresa la matrícula del alumno a eliminar: ");
        String matricula = scanner.nextLine().trim();
        if (matricula.isEmpty()) {
            System.out.println("La matrícula no puede estar vacía.");
            return;
        }
        GestionAlumnos.eliminarAlumno(matricula);
    }
}