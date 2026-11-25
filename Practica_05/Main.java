package Parcial_02.Practica05;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String FILE_PATH = "alumnosBD.csv";
    private static final ArrayList<Alumno> alumnos = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        cargarDesdeCSV();

        int option;
        do {
            System.out.println("\n--- SISTEMA DE GESTIÓN DE ALUMNOS ---");
            System.out.println("1. Agregar Alumno");
            System.out.println("2. Mostrar Alumnos");
            System.out.println("3. Editar Alumno");
            System.out.println("4. Eliminar Alumno");
            System.out.println("5. Guardar en CSV");
            System.out.println("6. Salir");
            option = readOption("Seleccione una opción: ");

            switch (option) {
                case 1 -> agregarAlumno();
                case 2 -> mostrarAlumnos();
                case 3 -> editarAlumno();
                case 4 -> eliminarAlumno();
                case 5 -> guardarEnCSV();
                case 6 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (option != 6);
    }

    public static int readOption(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número entero válido.");
            }
        }
    }

    private static void agregarAlumno() {
        System.out.println("\n--- INGRESAR DATOS DEL ALUMNO ---");
        int id = readOption("ID: ");

        System.out.print("Nombre: ");
        String name = sc.nextLine().trim();

        int age = readOption("Edad: ");

        System.out.print("Correo: ");
        String email = sc.nextLine().trim();

        alumnos.add(new Alumno(id, name, age, email));
        System.out.println("✔ Alumno registrado en memoria.");
    }

    private static void mostrarAlumnos() {
        System.out.println("\n--- LISTA DE ALUMNOS ---");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }
        for (Alumno a : alumnos) {
            System.out.println(a);
        }
    }

    private static void editarAlumno() {
        int id = readOption("\nIngrese ID del alumno a editar: ");
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getId() == id) {
                System.out.print("Nuevo Nombre: ");
                String name = sc.nextLine().trim();
                int age = readOption("Nueva Edad: ");
                System.out.print("Nuevo Correo: ");
                String email = sc.nextLine().trim();

                alumnos.set(i, new Alumno(id, name, age, email));
                System.out.println("✔ Alumno actualizado.");
                return;
            }
        }
        System.out.println("❌ Alumno no encontrado.");
    }

    private static void eliminarAlumno() {
        int id = readOption("\nIngrese ID del alumno a eliminar: ");
        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getId() == id) {
                alumnos.remove(i);
                System.out.println("✔ Alumno eliminado.");
                return;
            }
        }
        System.out.println("❌ Alumno no encontrado.");
    }

    private static void guardarEnCSV() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Alumno a : alumnos) {
                writer.println(a.toCSV());
            }
            System.out.println("✔ Datos guardados exitosamente en " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo: " + e.getMessage());
        }
    }

    private static void cargarDesdeCSV() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 4) {
                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    int age = Integer.parseInt(data[2].trim());
                    String email = data[3].trim();
                    alumnos.add(new Alumno(id, name, age, email));
                }
            }
            System.out.println("✔ Datos cargados automáticamente desde " + FILE_PATH);
        } catch (IOException | NumberFormatException e) {
            System.out.println("⚠️ No se pudieron cargar los datos previos: " + e.getMessage());
        }
    }
}