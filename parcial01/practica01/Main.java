package parcial01.practica01;

import java.io.*;


public class Main {
    public static void main(String[] args) {
        try {
            Act_Stream.leer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}