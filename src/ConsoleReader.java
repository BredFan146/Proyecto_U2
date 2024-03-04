import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleReader {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    // Método para leer un entero desde la consola
    public static int readInt(String prompt) {
        int input = 0;
        boolean validInput = false;
        do {
            try {
                System.out.print(prompt);
                input = Integer.parseInt(reader.readLine());
                validInput = true;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
            }
        } while (!validInput);
        return input;
    }

    // Método para leer un número decimal desde la consola
    public static double readDouble(String prompt) {
        double input = 0.0;
        boolean validInput = false;
        do {
            try {
                System.out.print(prompt);
                input = Double.parseDouble(reader.readLine());
                validInput = true;
            } catch (IOException | NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese un número decimal válido.");
            }
        } while (!validInput);
        return input;
    }

    // Método para leer una cadena de texto desde la consola
    public static String readString(String prompt) {
        String input = "";
        try {
            System.out.print(prompt);
            input = reader.readLine();
        } catch (IOException e) {
            System.out.println("Error: No se pudo leer la entrada del usuario.");
        }
        return input;
    }
}
