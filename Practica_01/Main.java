import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Main {
    public static void main(String[] args) {
        try {
            Act_Stream.leer();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}