package dd.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Mago extends Personaje {
    private EstrategiaCuracion estrategiaCura;
    private EstrategiaAtaque estrategiaAtaqueMagia;
    private boolean modoAleatorio;
    private boolean modoNormal;
    private boolean modoLocura;
    private Scanner scanner = new Scanner(System.in);

    public Mago(EstrategiaAtaque estrategiaAtaqueMagia, EstrategiaCuracion estrategiaCura, String nombre, boolean modoAleatorio, boolean modoLocura, boolean modoNormal) {
        super(nombre, 0, 1000, estrategiaAtaqueMagia);
        this.estrategiaCura = estrategiaCura;
        this.estrategiaAtaqueMagia = estrategiaAtaqueMagia;
        this.modoAleatorio = modoAleatorio;
        this.modoNormal = modoNormal;
        this.modoLocura = modoLocura;
    }

    @Override
    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        if (modoLocura && enemigo.getSalud() > 0) {
            System.out.println("Selecciona la estrategia de ataque de " + getNombre() + ":");
            System.out.println("1. Ataque con magia");
            System.out.println("2. Curar");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // descarta la entrada incorrecta                                                    
            }
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                for (int i = 0; i < 2; i++) {
                    if (enemigo.getSalud() <= 0) {
                        break; // Detiene los ataques adicionales si el enemigo ya está muerto
                    }
                    int valorAtaque = estrategiaAtaqueMagia.lanzaAtaque(enemigo);
                    if (valorAtaque > 0) {
                        enemigo.setSalud(enemigo.getSalud() - valorAtaque);
                    }
                    String registro = this.getNombre() + " [" + this.getSalud() + "] ataca con "+ estrategiaAtaqueMagia.getNombreAtaque()+ " contra " + enemigo.getNombre() + " [" + enemigo.getSalud() + "]";
                    if (valorAtaque == 0) {
                        registro += " -> El ataque ha fallado";
                    } else {
                        registro += " -> Daño: " + valorAtaque;
                    }
                    registros.add(registro);
                }
            } else if (opcion == 2) {
                
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
                    estrategiaCura = new Curar();
                    break;
                case 2: // Defender
                    estrategiaAtaqueMagia = new AtaqueMagia();
                    break;
                // Añade más casos si es necesario
            }
        }
        if (modoAleatorio && enemigo.getSalud() > 0) {
            // El mago ataca 2 veces
            for (int i = 0; i < 2; i++) {
                if (enemigo.getSalud() <= 0) {
                    break; // Detiene los ataques adicionales si el enemigo ya está muerto
                }
                int valorAtaque = estrategiaAtaqueMagia.lanzaAtaque(enemigo);
                if (valorAtaque > 0) {
                    enemigo.setSalud(enemigo.getSalud() - valorAtaque);
                }
                String registro = this.getNombre() + " [" + this.getSalud() + "] ataca con "+ estrategiaAtaqueMagia.getNombreAtaque()+ " contra " + enemigo.getNombre() + " [" + enemigo.getSalud() + "]";
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
