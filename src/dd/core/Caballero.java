package dd.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Caballero extends Personaje {
    private EstrategiaAtaque estrategiaAtaque;
    private boolean modoAleatorio;
    private boolean modoNormal;
    private boolean modoLocura;
    private Scanner scanner = new Scanner(System.in);

    // Constructor de la clase Caballero
    public Caballero(EstrategiaAtaque estrategiaAtaque, String nombre, boolean modoAleatorio, boolean modoLocura, boolean modoNormal) {
        super(nombre, 0, 1500, estrategiaAtaque);
        this.estrategiaAtaque = estrategiaAtaque;
        this.modoAleatorio = modoAleatorio;
        this.modoNormal = modoNormal;
        this.modoLocura = modoLocura;
    }

    // Sobrescribe el método ataca de la clase Personaje
    @Override
    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        if (modoLocura && enemigo.getSalud() > 0) {
            System.out.println("Selecciona la estrategia de ataque de " + getNombre() + ":");
            System.out.println("1. Ataque con espada");
            System.out.println("2. Ataque con arco");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // descarta la entrada incorrecta                                                    
            }
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                estrategiaAtaque = new AtaqueEspada();
            } else if (opcion == 2) {
                estrategiaAtaque = new AtaqueArco();
            }
        }

        if (modoNormal && enemigo.getSalud() > 0) {
            System.out.println("Es tu turno, elige una acción:");
            System.out.println("1. Ataque espada");
            System.out.println("2. Ataque magia");
            // Añade más opciones si es necesario
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // descarta la entrada incorrecta
            }
            int accion = scanner.nextInt();
            scanner.nextLine();
            switch (accion) {
                case 1: // Atacar
                    estrategiaAtaque = new AtaqueEspada();
                    break;
                case 2: // Defender
                    estrategiaAtaque = new AtaqueMagia();
                    break;
                // Añade más casos si es necesario
            }
        }
        if (modoAleatorio && enemigo.getSalud() > 0) {
            // El rey ataca 3 veces
            for (int i = 0; i < 2; i++) {
                if (enemigo.getSalud() <= 0) {
                    break; // Detiene los ataques adicionales si el enemigo ya está muerto
                }
                int valorAtaque = estrategiaAtaque.lanzaAtaque(enemigo);
                if (valorAtaque > 0) {
                    enemigo.setSalud(enemigo.getSalud() - valorAtaque);
                }
                String registro = this.getNombre() + " [" + this.getSalud() + "] ataca con "+ estrategiaAtaque.getNombreAtaque()+ " contra " + enemigo.getNombre() + " [" + enemigo.getSalud() + "]";
                if (valorAtaque == 0) {
                    registro += " -> El ataque ha fallado";
                } else {
                    registro += " -> Daño: " + valorAtaque;
                }
                registros.add(registro);
            }
        }
        return registros;
    }
}
