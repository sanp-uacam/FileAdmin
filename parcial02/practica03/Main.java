package parcial02.practica03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Main {
    public static void main(String[] args) {
        try (Reader r = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(r);
        ){
            String ch;
            System.out.println("Bienvenido a la Mejor terminal de comandos:");
            System.out.print(">>>");
            do {
                ch = br.readLine();
                if (ch != null && !ch.equalsIgnoreCase("exit()")) {
                    System.out.println("comando recibido");
                    System.out.print(">>>");
                }
            } while (ch != null && !ch.equalsIgnoreCase("exit()"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}