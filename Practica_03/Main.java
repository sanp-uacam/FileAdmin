package Practica_3;

// Jaime Michel Garcia Sostenes
import java.io.File;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        // Genera el archivo inicial si no existe para tener datos de prueba
        File archivo = new File("AlumnosDB.txt");
        if (!archivo.exists()) {
            GestionAlumnos.escribirAlumnos();
        }

        while (!salir) {
            System.out.println("\n=== MENÚ GESTIÓN DE ALUMNOS ===");
            System.out.println("1. Ver todos los alumnos");
            System.out.println("2. Buscar alumno por matrícula");
            System.out.println("3. Agregar nuevo alumno");
            System.out.println("4. Eliminar alumno por matrícula");
            System.out.println("5. Ver promedio del grupo");
            System.out.println("6. Ver alumno con mejor promedio");
            System.out.println("7. Restaurar datos de prueba originales");
            System.out.println("8. Salir");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        GestionAlumnos.leerAlumnos();
                        break;
                    case 2:
                        System.out.print("Ingresa la matrícula a buscar: ");
                        String matBuscar = scanner.nextLine();
                        Alumno encontrado = GestionAlumnos.buscarAlumnoPorMatricula(matBuscar);
                        System.out.println(encontrado != null ? encontrado : "❌ Alumno no encontrado");
                        break;
                    case 3:
                        System.out.print("Ingresa la matrícula: ");
                        String nuevaMat = scanner.nextLine();
                        System.out.print("Ingresa el nombre: ");
                        String nuevoNom = scanner.nextLine();
                        System.out.print("Ingresa el promedio: ");
                        double nuevoProm = scanner.nextDouble();
                        scanner.nextLine(); // Limpiar buffer

                        GestionAlumnos.agregarAlumno(new Alumno(nuevaMat, nuevoNom, nuevoProm));
                        break;
                    case 4:
                        System.out.print("Ingresa la matrícula a eliminar: ");
                        String matEliminar = scanner.nextLine();
                        boolean borrado = GestionAlumnos.eliminarAlumno(matEliminar);
                        if (!borrado) {
                            System.out.println("❌ No se encontró un alumno con esa matrícula.");
                        }
                        break;
                    case 5:
                        double promedio = GestionAlumnos.promedioGrupal();
                        System.out.printf("El promedio general del grupo es: %.2f\n", promedio);
                        break;
                    case 6:
                        Alumno mejor = GestionAlumnos.mejorPromedio();
                        System.out.println(mejor != null ? "El alumno con mejor promedio es: " + mejor : "No hay datos para calcular.");
                        break;
                    case 7:
                        GestionAlumnos.escribirAlumnos();
                        System.out.println("✅ Base de datos restaurada con los 4 alumnos de prueba.");
                        break;
                    case 8:
                        salir = true;
                        System.out.println("Cerrando el sistema. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("❌ Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("❌ Error: Debes ingresar un número válido.");
                scanner.nextLine(); // Limpiar el buffer si hay error de tipo de dato
            }
        }
        scanner.close();
    }
}