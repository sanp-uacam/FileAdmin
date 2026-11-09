package parcial02.practica02;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        leerTexto();

    }

    static void leerTexto(){
        try (Reader r = new FileReader("data.txt");
        BufferedReader br = new BufferedReader(r)
        ){
            //leer todo el archivo
            System.out.println(br.readLine());

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void ecribirTexto(){
        try (Writer w = new FileWriter("data.txt");
             BufferedWriter bw = new BufferedWriter(w)
        ){
            //medir tiempos con buffer y sin buffer
            for (var i = 0; i <= 1000; i++) {
                bw.write("File Admin" + i + "\n");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    static void escribirNumeros() {
        try (FileOutputStream fos = new FileOutputStream("numeros.txt");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {

            long inicio = System.nanoTime();

            for (var i = 0; i <= 1000; i++) {
                bos.write(i);
            }

            long fin = System.nanoTime();
            long duracionNs = fin - inicio;
            double duracionMs = duracionNs / 1_000_000.0;

            System.out.println("Duración: " + duracionNs + " ns (" + duracionMs + " ms)");


        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void leerNumeros()  {
       try (FileInputStream fis = new FileInputStream("numeros.txt");
       BufferedInputStream bis = new BufferedInputStream(fis)
       ){
           //medir tiempos con Buffer y sin Buffer
           int octeto = 0;
           while (octeto != -1){
               octeto = bis.read();
               System.out.println(octeto);
           }
       } catch (FileNotFoundException e) {
           throw new RuntimeException(e);
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
    }
}