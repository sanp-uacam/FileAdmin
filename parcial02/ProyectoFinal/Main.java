package parcial02.ProyectoFinal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static final String FILE_PATH = "alumnosBD.csv";
    private static final ArrayList<Alumno> alumnos = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int option;
        System.out.println("Bienvenidos a la BD Alumnos-UACAM");
        do {
            option = readOption("admin> ");
            switch (option){
                case 1 -> agregarAlumnos();
                //====================================
                //Implementar las siguientes funciones
                //====================================

                //case 2 -> listarAlumnos();
                //case 4 -> modificarAlumno();
                //case 5 -> eliminarAlumno();
                //case 6 -> mostrarMenu();
                case 0 -> System.out.println("Saliendo de la Base de Datos...");
                //CRUD -> Create Read Update Delete
            }
        }while (option != 0);

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

    private static void agregarAlumnos(){
        System.out.println("Introduzca los datos del Alumnos");
        System.out.print("Matricula: ");
        int id = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nombre: ");
        String name = scanner.nextLine().trim();
        int age = Integer.parseInt(scanner.nextLine().trim());
        String mail = scanner.nextLine().trim();

        alumnos.add(new Alumno(id, name, age, mail));
    }
}



























