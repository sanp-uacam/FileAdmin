import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Practica_02 {

    public static void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú de Asistencia (ObjectStream) ---");
            System.out.println("1. Agregar alumno (Asistencia)");
            System.out.println("2. Ver lista de alumnos y total");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (opcion) {
                    case 1:
                        agregarAlumnoAsitencia(scanner);
                        break;
                    case 2:
                        verVerAlumno();
                        break;
                    case 3:
                        salir = true;
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes ingresar un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
        }
        scanner.close();
    }

    // Funcionalidad 1: Guarda en archivo usando ObjectStream
    public static void agregarAlumnoAsitencia(Scanner scanner) {
        System.out.print("Ingresa el nombre del alumno: ");
        String nombre = scanner.nextLine();

        // Creamos el OBJETO alumno
        Alumno alumno = new Alumno(nombre);

        // Lo mandamos a guardar
        Act_ObjectStream.guardarAlumno(alumno);
        System.out.println("Asistencia de '" + nombre + "' guardada correctamente.");
    }

    // Funcionalidad 2: Imprimir lista de alumnos y total de asistencia
    public static void verVerAlumno() {
        // Recuperamos la lista de objetos
        List<Alumno> listaAsistencia = Act_ObjectStream.leerAlumnos();

        System.out.println("\n--- Lista de Asistencia ---");

        if (listaAsistencia.isEmpty()) {
            System.out.println("No hay asistencias registradas aún.");
        } else {
            // Imprimimos cada objeto Alumno
            for (Alumno alumno : listaAsistencia) {
                System.out.println("- " + alumno.getNombre());
            }

            // Imprimimos el total calculando el tamaño de la lista
            System.out.println("\nTotal de asistencia: " + listaAsistencia.size() + " alumno(s).");
        }
    }
}