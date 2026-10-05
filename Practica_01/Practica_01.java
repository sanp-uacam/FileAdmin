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
            System.out.println("3. Salir");
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

    private static void agregarNota(Scanner scanner) {
        System.out.print("Escribe tu nota: ");
        String nota = scanner.nextLine();

    }


    private static void verNotas() {
        try {

        String notas = Act_Stream.leerNotas();
        } catch (IOException e){

        }

        System.out.println(notas);

    }
}