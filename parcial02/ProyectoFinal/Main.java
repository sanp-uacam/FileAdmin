package Parcial_02.Practica05;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String FILE_PATH = "alumnosBD.csv";
    private static final ArrayList<Alumno> alumnos = new ArrayList<>();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenidos a la BD Alumnos");

        // Cargamos la base de datos automáticamente al iniciar
        cargarBD();

        int option;
        do {
            mostrarMenu();
            option = readOption("Seleccione una opción: ");

            switch (option){
                case 0 -> {
                    guardarCSV(); // Guarda automáticamente antes de salir
                    System.out.println("Saliendo de la BD Alumnos...");
                }
                case 1 -> agregarAlumnos();
                case 2 -> listarAlumnos();
                case 3 -> modificarAlumno();
                case 4 -> eliminarAlumno();
                case 5 -> guardarCSV();
                case 6 -> cargarBD(); // TAREA: Opción para cargar la BD
                default -> System.out.println("Opción no válida. Por favor, intente de nuevo.\n");
            }
        } while (option != 0);
    }

    public static int readOption(String msg){
        while (true){
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un numero valido");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n=======================");
        System.out.println("       MENÚ CRUD       ");
        System.out.println("=======================");
        System.out.println("1. Agregar Alumno");
        System.out.println("2. Listar Alumnos");
        System.out.println("3. Modificar Alumno");
        System.out.println("4. Eliminar Alumno");
        System.out.println("5. Guardar en CSV");
        System.out.println("6. Cargar BD desde CSV");
        System.out.println("0. Salir");
        System.out.println("=======================");
    }

    private static void agregarAlumnos(){
        System.out.println("\n--- Introduzca los datos del Alumno ---");
        try {
            System.out.print("Matricula: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Nombre: ");
            String name = sc.nextLine().trim();

            System.out.print("Edad: ");
            int age = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Correo: ");
            String mail = sc.nextLine().trim();

            alumnos.add(new Alumno(id, name, age, mail));
            System.out.println("✅ Alumno agregado con éxito.");
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Debe ingresar un número válido para la matrícula o edad.");
        }
    }

    private static void listarAlumnos() {
        System.out.println("\n--- Lista de Alumnos ---");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados en la BD.");
        } else {
            for (Alumno alumno : alumnos) {
                System.out.println(alumno.toString());
            }
        }
    }

    private static void modificarAlumno() {
        System.out.println("\n--- Modificar Alumno ---");
        System.out.print("Ingrese la matricula del alumno a modificar: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean encontrado = false;

            for (Alumno alumno : alumnos) {
                if (alumno.getId() == id) {
                    encontrado = true;
                    System.out.println("Alumno encontrado: " + alumno.getName());

                    System.out.print("Nuevo Nombre (deje en blanco para no cambiar): ");
                    String name = sc.nextLine().trim();
                    if (!name.isEmpty()) {
                        alumno.setName(name);
                    }

                    System.out.print("Nueva Edad (ingrese 0 para no cambiar): ");
                    String ageInput = sc.nextLine().trim();
                    if (!ageInput.isEmpty() && !ageInput.equals("0")) {
                        alumno.setAge(Integer.parseInt(ageInput));
                    }

                    System.out.print("Nuevo Correo (deje en blanco para no cambiar): ");
                    String mail = sc.nextLine().trim();
                    if (!mail.isEmpty()) {
                        alumno.setMail(mail);
                    }

                    System.out.println("✅ Alumno modificado correctamente.");
                    break;
                }
            }

            if (!encontrado) {
                System.out.println("❌ No se encontró ningún alumno con esa matrícula.");
            }

        } catch (NumberFormatException e) {
            System.out.println("❌ Matrícula o edad inválida.");
        }
    }

    private static void eliminarAlumno() {
        System.out.println("\n--- Eliminar Alumno ---");
        System.out.print("Ingrese la matricula del alumno a eliminar: ");

        try {
            int id = Integer.parseInt(sc.nextLine().trim());
            boolean eliminado = alumnos.removeIf(alumno -> alumno.getId() == id);

            if (eliminado) {
                System.out.println("✅ Alumno eliminado correctamente.");
            } else {
                System.out.println("❌ No se encontró ningún alumno con esa matrícula.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Matrícula inválida.");
        }
    }

    private static void guardarCSV() {
        System.out.println("\n--- Guardando datos en CSV ---");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            bw.write("Matricula,Nombre,Edad,Correo");
            bw.newLine();

            for (Alumno alumno : alumnos) {
                bw.write(alumno.getId() + "," + alumno.getName() + "," +
                        alumno.getAge() + "," + alumno.getMail());
                bw.newLine();
            }
            System.out.println("✅ Base de datos guardada exitosamente en: " + FILE_PATH);
        } catch (IOException e) {
            System.out.println("❌ Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    // TAREA: Nuevo método para LEER la base de datos CSV
    private static void cargarBD() {
        System.out.println("\n--- Cargando datos desde CSV ---");
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String linea;
            boolean primeraLinea = true;
            int count = 0;

            alumnos.clear(); // Limpiamos la lista para no duplicar datos si se carga manualmente varias veces

            while ((linea = br.readLine()) != null) {
                // Saltamos la primera línea porque son los encabezados
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }

                // Dividimos la línea por las comas
                String[] datos = linea.split(",");
                if (datos.length == 4) {
                    int id = Integer.parseInt(datos[0].trim());
                    String name = datos[1].trim();
                    int age = Integer.parseInt(datos[2].trim());
                    String mail = datos[3].trim();

                    // Añadimos el alumno a la lista
                    alumnos.add(new Alumno(id, name, age, mail));
                    count++;
                }
            }
            System.out.println("✅ Base de datos cargada. " + count + " alumnos recuperados.");

        } catch (FileNotFoundException e) {
            System.out.println("⚠️ El archivo " + FILE_PATH + " aún no existe. Se creará al guardar.");
        } catch (IOException e) {
            System.out.println("❌ Error al leer el archivo CSV: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("❌ Error: Formato de número inválido en el archivo CSV.");
        }
    }
}