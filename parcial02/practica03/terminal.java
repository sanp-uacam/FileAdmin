package parcial02.practica03;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class terminal {

    // Nombres de archivos de log compatibles con lo indicado por el profesor
    private static final String LOG_FILE = "historial.log";
    private static final String LOG_FILE_ALT = "historial_terminal.log";

    public static void main(String[] args) {
        System.out.println("=== BIENVENIDO AL MINI-TERMINAL ===");
        System.out.println("Comandos disponibles:");
        System.out.println(" - sumar [num1] [num2]");
        System.out.println(" - invertir [texto]");
        System.out.println(" - mayusculas [texto]");
        System.out.println(" - help (ver comandos)");
        System.out.println(" - salir / exit()");
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

                // Si da enter vacio, no procesamos nada
                if (input.trim().isEmpty()) {
                    continue;
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

                // Condicion de salida
                String cmd = input.trim().toLowerCase();
                if (cmd.equals("salir") || cmd.equals("exit()")) {
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
                        double suma = num1 + num2;
                        // Si no tiene decimales lo muestra como entero limpio
                        if (suma == (long) suma) {
                            return "Resultado de la suma: " + (long) suma;
                        } else {
                            return "Resultado de la suma: " + suma;
                        }
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
                        return "Texto en mayusculas: " + texto.toUpperCase();
                    } else {
                        return "Error: Uso correcto -> mayusculas [texto]";
                    }

                case "help":
                case "ayuda":
                    return "Comandos disponibles:\n - sumar [num1] [num2]\n - invertir [texto]\n - mayusculas [texto]\n - salir / exit()";

                case "salir":
                case "exit()":
                    return "Cerrando terminal... Hasta luego!";

                default:
                    return "Error: Comando desconocido. Intenta con 'sumar', 'invertir', 'mayusculas' o 'help'.";
            }
        } catch (NumberFormatException e) {
            return "Error: Los valores para sumar deben ser numeros validos.";
        } catch (Exception e) {
            return "Error inesperado al procesar el comando: " + e.getMessage();
        }
    }

    /**
     * Escribe una linea en el archivo de log con la fecha y hora actual en modo append.
     */
    private static void guardarEnLog(String tipo, String mensaje) {
        // Formato exacto pedido por el profesor: [yyyy-MM-dd HH:mm:ss]
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = LocalDateTime.now().format(formatter);
        String lineaLog = String.format("[%s] %s: %s", timestamp, tipo, mensaje);

        // Escribimos en la raiz
        escribirLinea(LOG_FILE, lineaLog);
        escribirLinea(LOG_FILE_ALT, lineaLog);

        // Si la ejecucion se hace desde la raiz del proyecto, tambien guardamos copia dentro de parcial02/practica03/
        File carpeta = new File("parcial02/practica03");
        if (carpeta.isDirectory()) {
            escribirLinea("parcial02/practica03/" + LOG_FILE, lineaLog);
            escribirLinea("parcial02/practica03/" + LOG_FILE_ALT, lineaLog);
        }
    }

    private static void escribirLinea(String ruta, String linea) {
        // Usamos FileWriter con 'true' para modo append (no sobrescribir)
        try (FileWriter fw = new FileWriter(ruta, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            out.println(lineaLogFormat(linea));
            out.flush();

        } catch (IOException e) {
            System.err.println("No se pudo escribir en el archivo de log: " + e.getMessage());
        }
    }

    private static String lineaLogFormat(String linea) {
        return linea;
    }
}
