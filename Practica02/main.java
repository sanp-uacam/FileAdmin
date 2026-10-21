
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class main {
    public static void main(String[] args) {
        try (Writer w = new FileWriter("dsts.txt");) {

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    static void escribirNumeros() {
        try (FileOutputStream fos = new FileOutputStream("numeros.bin");
             BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            for (var i = 0; i <= 255; i++) {
                bos.write(i);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void leerNumeros()  {
        try (FileInputStream fis = new FileInputStream("numeros.bin");
             BufferedInputStream bis = new BufferedInputStream(fis)){
            int octeto;
            int retornos = 0;
            bis.mark(100);
            do {
                octeto = bis.read();
                if (octeto == 150 && ++retornos<3){
                    bis.reset();
                }
                if (octeto != -1){
                    System.out.print(octeto);
                    System.out.print(" ");
                    if (octeto % 20==0){
                        System.out.println();
                    }
                }
            } while (octeto!= -1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}