package parcial02.ProyectoFinal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String FILE_PATH = "alumnosBD.csv";
    private static final ArrayList<Alumno> alumnos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    // Metodo auxiliar para guardar y leer alumnosBD.csv dentro de la carpeta del proyecto si corremos desde la raiz
    private static File obtenerArchivoCSV() {
        File dir = new File("parcial02/ProyectoFinal");
        if (dir.isDirectory()) {
            return new File("parcial02/ProyectoFinal/" + FILE_PATH);
        }
        return new File(FILE_PATH);
    }

    public static void main(String[] args) {
        int option;
        System.out.println("Bienvenidos a la BD Alumnos-UACAM");

        // Cargamos la base de datos si el archivo ya existe
        File archivo = obtenerArchivoCSV();
        if (archivo.exists()) {
            cargarBD();
        }

        mostrarMenu();

        do {
            option = readOption("admin> ");
            switch (option){
                case 1 -> agregarAlumnos();
                case 2 -> listarAlumnos();
                case 3 -> cargarBD();
                case 4 -> modificarAlumno();
                case 5 -> eliminarAlumno();
                case 6 -> mostrarMenu();
                case 7 -> guardarCSV();
                case 0 -> System.out.println("Saliendo de la Base de Datos...");
                default -> System.out.println("Opcion no valida. Usa 6 para ver el menu.");
            }
        } while (option != 0);
    }

    public static int readOption(String msg){
        while (true){
            try {
                System.out.print(msg);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e){
                System.out.println("Debe ingresar un numero valido");
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- MENU PRINCIPAL ---");
        System.out.println("1. Agregar alumno");
        System.out.println("2. Listar alumnos");
        System.out.println("3. Cargar BD desde CSV");
        System.out.println("4. Modificar alumno");
        System.out.println("5. Eliminar alumno");
        System.out.println("6. Mostrar menu");
        System.out.println("7. Guardar CSV");
        System.out.println("0. Salir");
        System.out.println("----------------------");
    }

    public static void guardarCSV(){
        File archivo = obtenerArchivoCSV();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))){
            writer.write("id,nombre,edad,correo");
            writer.newLine();

            for (Alumno alumno : alumnos) {
                writer.write(alumno.getId() + "," + alumno.getName() + "," +
                        alumno.getAge() + "," + alumno.getMail());
                writer.newLine();
            }
            System.out.println("Archivo guardado en " + archivo.getPath());
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void cargarBD() {
        File archivo = obtenerArchivoCSV();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;
            int cargados = 0;

            alumnos.clear();

            while ((linea = reader.readLine()) != null) {
                // Saltamos el encabezado
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                if (linea.trim().isEmpty()) {
                    continue;
                }

                String[] partes = linea.split(",");
                if (partes.length == 4) {
                    int id = Integer.parseInt(partes[0].trim());
                    String name = partes[1].trim();
                    int age = Integer.parseInt(partes[2].trim());
                    String mail = partes[3].trim();

                    alumnos.add(new Alumno(id, name, age, mail));
                    cargados++;
                }
            }
            System.out.println("Base de datos cargada. Alumnos registrados: " + cargados);
        } catch (FileNotFoundException e) {
            System.out.println("Todavia no existe el archivo " + FILE_PATH + ". Guarda primero con la opcion 7.");
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: hay datos numericos mal escritos en el CSV.");
        }
    }

    private static void agregarAlumnos(){
        System.out.println("\nIntroduzca los datos del Alumno");
        try {
            System.out.print("Matricula: ");
            int id = Integer.parseInt(scanner.nextLine().trim());

            // Revisamos si ya existe esa matricula
            for (int i = 0; i < alumnos.size(); i++) {
                if (alumnos.get(i).getId() == id) {
                    System.out.println("Ya existe un alumno con la matricula " + id);
                    return;
                }
            }

            System.out.print("Nombre: ");
            String name = scanner.nextLine().trim();

            System.out.print("Edad: ");
            int age = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Correo: ");
            String mail = scanner.nextLine().trim();

            alumnos.add(new Alumno(id, name, age, mail));
            System.out.println("Alumno agregado correctamente.");
        } catch (NumberFormatException e) {
            System.out.println("Error: la matricula y la edad deben ser numeros.");
        }
    }

    private static void listarAlumnos() {
        System.out.println("\n--- LISTA DE ALUMNOS ---");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
            return;
        }

        for (int i = 0; i < alumnos.size(); i++) {
            Alumno a = alumnos.get(i);
            System.out.println((i + 1) + ". Matricula: " + a.getId()
                    + " | Nombre: " + a.getName()
                    + " | Edad: " + a.getAge()
                    + " | Correo: " + a.getMail());
        }
    }

    private static void modificarAlumno() {
        System.out.println("\n--- MODIFICAR ALUMNO ---");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos para modificar.");
            return;
        }

        int id = readOption("Ingrese la matricula del alumno a modificar: ");
        Alumno encontrado = null;

        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getId() == id) {
                encontrado = alumnos.get(i);
                break;
            }
        }

        if (encontrado == null) {
            System.out.println("No se encontro ningun alumno con la matricula " + id);
            return;
        }

        System.out.println("Alumno actual: " + encontrado.getName() + " (" + encontrado.getAge() + " anos, " + encontrado.getMail() + ")");
        System.out.println("Deja en blanco y presiona Enter si no quieres cambiar un dato.");

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = scanner.nextLine().trim();
        if (!nuevoNombre.isEmpty()) {
            encontrado.setName(nuevoNombre);
        }

        System.out.print("Nueva edad: ");
        String nuevaEdadTexto = scanner.nextLine().trim();
        if (!nuevaEdadTexto.isEmpty()) {
            try {
                int nuevaEdad = Integer.parseInt(nuevaEdadTexto);
                encontrado.setAge(nuevaEdad);
            } catch (NumberFormatException e) {
                System.out.println("Edad no valida, se conserva la anterior.");
            }
        }

        System.out.print("Nuevo correo: ");
        String nuevoCorreo = scanner.nextLine().trim();
        if (!nuevoCorreo.isEmpty()) {
            encontrado.setMail(nuevoCorreo);
        }

        System.out.println("Datos del alumno actualizados.");
    }

    private static void eliminarAlumno() {
        System.out.println("\n--- ELIMINAR ALUMNO ---");
        if (alumnos.isEmpty()) {
            System.out.println("No hay alumnos para eliminar.");
            return;
        }

        int id = readOption("Ingrese la matricula del alumno a eliminar: ");
        boolean eliminado = false;

        for (int i = 0; i < alumnos.size(); i++) {
            if (alumnos.get(i).getId() == id) {
                System.out.println("Eliminando a: " + alumnos.get(i).getName());
                alumnos.remove(i);
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            System.out.println("Alumno eliminado correctamente.");
        } else {
            System.out.println("No se encontro ningun alumno con la matricula " + id);
        }
    }
}
