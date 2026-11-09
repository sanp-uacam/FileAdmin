package parcial02.practica03;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class terminal {

    // Ruta del archivo de log
    private static final String LOG_FILE = "historial_terminal.log";

    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL MINI-TERMINAL ===");
        System.out.println("Comandos disponibles:");
        System.out.println(" - sumar [num1] [num2]");
        System.out.println(" - invertir [texto]");
        System.out.println(" - mayusculas [texto]");
        System.out.println(" - salir");
        System.out.println("===================================");

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String input;
            boolean continuar = true;

            while (continuar) {
                System.out.print("> ");
                input = br.readLine();

                // Si es null (EOF), salimos del bucle
                if (input == null) {
                    break;
                }

                // Guardamos el input en el log
                guardarEnLog("INPUT", input);

                // Procesamos el comando
                String respuesta = procesarComando(input);

                // Mostramos la respuesta en consola
                if (respuesta != null) {
                    System.out.println(respuesta);
                    // Guardamos la respuesta en el log
                    guardarEnLog("OUTPUT", respuesta);
                }

                // Condición de salida
                if (input.trim().equalsIgnoreCase("salir")) {
                    continuar = false;
                }
            }
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }

    /**
     * Procesa el comando ingresado y devuelve la respuesta.
     */
    private static String procesarComando(String input) {
        String[] partes = input.trim().split("\\s+"); // Separa por espacios
        String comando = partes[0].toLowerCase();

        try {
            switch (comando) {
                case "sumar":
                    if (partes.length == 3) {
                        double num1 = Double.parseDouble(partes[1]);
                        double num2 = Double.parseDouble(partes[2]);
                        return "Resultado de la suma: " + (num1 + num2);
                    } else {
                        return "Error: Uso correcto -> sumar [num1] [num2]";
                    }

                case "invertir":
                    if (partes.length >= 2) {
                        // Reconstruimos el texto por si tiene espacios
                        String textoAInvertir = input.substring(input.indexOf(" ") + 1);
                        return "Texto invertido: " + new StringBuilder(textoAInvertir).reverse().toString();
                    } else {
                        return "Error: Uso correcto -> invertir [texto]";
                    }

                case "mayusculas":
                    if (partes.length >= 2) {
                        String texto = input.substring(input.indexOf(" ") + 1);
                        return "Texto en mayúsculas: " + texto.toUpperCase();
                    } else {
                        return "Error: Uso correcto -> mayusculas [texto]";
                    }

                case "salir":
                    return "Cerrando terminal... ¡Hasta luego!";

                default:
                    return "Error: Comando desconocido. Intenta con 'sumar', 'invertir' o 'mayusculas'.";
            }
        } catch (NumberFormatException e) {
            return "Error: Los valores para sumar deben ser números válidos.";
        } catch (Exception e) {
            return "Error inesperado al procesar el comando.";
        }
    }

    /**
     * Escribe una línea en el archivo de log con la fecha y hora actual.
     */
    private static void guardarEnLog(String tipo, String mensaje) {
        // Formato de fecha: AAAA-MM-DD HH:MM:SS
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String lineaLog = String.format("[%s] %s: %s", timestamp, tipo, mensaje);

        // Usamos FileWriter con 'true' para modo append (no sobrescribir)
        try (FileWriter fw = new FileWriter(LOG_FILE, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(lineaLog);

        } catch (IOException e) {
            System.err.println("No se pudo escribir en el archivo de log: " + e.getMessage());
        }
    }
}