package Practica_03;

public class Main {
    public static void main(String[] args) {
        // 1. Crear la base de datos inicial (Sobrescribe el archivo con los 4 alumnos base)
        GestionAlumnos.escribirAlumnos();

        // 2. Leer y mostrar todos los alumnos
        GestionAlumnos.mostrarAlumnos();

        // 3. Buscar por matrícula (Probamos con "3466" que sí existe)
        System.out.println("\nBuscando matrícula 3466...");
        GestionAlumnos.mostrarBusqueda("3466");
        
        // Probamos con una matrícula que no existe
        System.out.println("Buscando matrícula 9999...");
        GestionAlumnos.mostrarBusqueda("9999");

        // 4. Agregar un alumno nuevo
        GestionAlumnos.agregarAlumno(new Alumno("1122", "Ana", 9.3));

        // 5. Verificar que se guardó (Volvemos a mostrar la lista)
        GestionAlumnos.mostrarAlumnos();
    }
}