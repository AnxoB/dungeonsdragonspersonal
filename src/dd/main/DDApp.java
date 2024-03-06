package dd.main;

import dd.core.*;
import java.util.*;

public class DDApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Bienvenido al juego. Por favor, selecciona un modo:");
            System.out.println("1. Modo aleatorio");
            System.out.println("2. Modo locura");
            System.out.println("3. Salir");

            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    modoAleatorio();
                    break;
                case 2:
                    modoNormal(scanner);
                    break;
                case 3:
                    System.out.println("Gracias por jugar. ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 3.");
                    break;
            }
        } while (opcion != 3);
        
        scanner.close();
    }    
        public static void modoAleatorio() {
            System.out.println("Arturo, Lancelot y Percival se encuentran unos Trolls. ¡A luchar!");

            // Crear ejércitos
            List<Personaje> ejercitoAliados = new ArrayList<>();
            List<Personaje> ejercitoTrolls = new ArrayList<>();
            // Crear la batalla
            Batalla batalla = new Batalla(ejercitoAliados, ejercitoTrolls);

            // Iniciar la batalla y guardar el registro 
            List<String> registroDeAtaques = batalla.luchar();

            for (String mensaje : registroDeAtaques) {
                System.out.println(mensaje);
                try {
                    Thread.sleep(500); // Espera 250 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            // Mostrar el resultado de la batalla
            if (ejercitoAliados.isEmpty()) {
                System.out.println("Los trolls han ganado la batalla.");
            } else if (ejercitoTrolls.isEmpty()) {
                System.out.println("Los aliados han ganado la batalla.");
            } else {
                System.out.println("La batalla terminó en empate.");
            }
        }
    
        public static void modoNormal(Scanner scanner) {
            System.out.println("Debes hacer frente a unos trolls. ¡Tu eliges como!");

            // Crear la batalla
            Batalla2 batalla = new Batalla2(scanner);

            // Iniciar la batalla y guardar el registro 
            List<String> registroDeAtaques = batalla.luchar();

            for (String mensaje : registroDeAtaques) {
                System.out.println(mensaje);
                try {
                    Thread.sleep(500); // Espera 250 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            
            // Mostrar el resultado de la batalla
            if (batalla.getEjercitoAliados().isEmpty()) {
                System.out.println("Los trolls han ganado la batalla.");
            } else if (batalla.getEjercitoTrolls().isEmpty()) {
                System.out.println("Los aliados han ganado la batalla.");
            } else {
                System.out.println("La batalla terminó en empate.");
            }
        } 

}