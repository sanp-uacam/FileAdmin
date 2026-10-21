package Parcial_02.Practica_03;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        String logFileName = "terminal.log";

        try (Reader r = new InputStreamReader(System.in);
             BufferedReader br = new BufferedReader(r);
             FileWriter fw = new FileWriter(logFileName, true); // modo 'append' activado
             PrintWriter logWriter = new PrintWriter(fw)) {

            String ch;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter logDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            System.out.println("Bienvenido a la Mejor terminal de comandos:");
            System.out.println("Escribe 'help' para ver los comandos disponibles.");
            System.out.print(">>> ");

            do {
                ch = br.readLine();
                if (ch != null) {
                    // Guarda en el archivo de registro la fecha, hora y lo que escribió el usuario
                    String timestamp = LocalDateTime.now().format(logDateFormatter);
                    logWriter.println("[" + timestamp + "] " + ch);
                    logWriter.flush(); // Guarda los datos inmediatamente en el disco

                    if (!ch.trim().equalsIgnoreCase("exit()")) {
                        String input = ch.trim();

                        if (!input.isEmpty()) {
                            String[] partes = input.split("\\s+");
                            String comando = partes[0].toLowerCase();

                            switch (comando) {
                                case "sumar":
                                    if (partes.length == 3) {
                                        try {
                                            double num1 = Double.parseDouble(partes[1]);
                                            double num2 = Double.parseDouble(partes[2]);
                                            System.out.println("Resultado: " + (num1 + num2));
                                        } catch (NumberFormatException e) {
                                            System.out.println("Error: Los parámetros deben ser números válidos.");
                                        }
                                    } else {
                                        System.out.println("Uso incorrecto: sumar <num1> <num2>");
                                    }
                                    break;

                                case "hora":
                                case "mostrar_hora":
                                    System.out.println("Hora actual: " + LocalTime.now().format(formatter));
                                    break;

                                case "help":
                                case "ayuda":
                                    System.out.println("\n--- Comandos disponibles ---");
                                    System.out.println("  sumar <num1> <num2>  : Suma dos números (ej: sumar 5 10)");
                                    System.out.println("  hora / mostrar_hora  : Muestra la hora actual del sistema");
                                    System.out.println("  help / ayuda         : Muestra este menú de ayuda");
                                    System.out.println("  exit()               : Sale de la terminal\n");
                                    break;

                                default:
                                    System.out.println("Comando no reconocido: " + input + ". Escribe 'help' para ayuda.");
                                    break;
                            }
                        }

                        System.out.print(">>> ");
                    }
                }
            } while (ch != null && !ch.trim().equalsIgnoreCase("exit()"));

            System.out.println("Programa finalizado.");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}