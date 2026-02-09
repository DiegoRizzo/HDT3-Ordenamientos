import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    
    public static void main(String[] args) {

        File file = new File("input.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {

            System.out.println("Escribe la cantidad de números a generar: ");
            int length = Integer.parseInt(System.console().readLine());

            for (int i = 0; i < length; i++) {
                writer.write((int) (Math.random() * 9999) + "\n");
            }

        }

        catch (IOException e) {
            System.out.println("Ha ocurrido un error.");
            e.printStackTrace();
        }
    }
}
