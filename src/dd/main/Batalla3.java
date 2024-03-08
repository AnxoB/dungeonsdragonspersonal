package dd.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import dd.core.*;

public class Batalla3 {
    private List<Personaje> ejercitoAliados;
    private List<Personaje> ejercitoTrolls;
    private Random random = new Random();
    private Scanner scanner;

    public Batalla3(Scanner scanner) {
        this.scanner = scanner;
        ejercitoAliados = new ArrayList<>();
        ejercitoTrolls = new ArrayList<>();

        // Reclutar ejército de aliados
        System.out.println("¿Cuántos miembros quieres en tu ejército? (máximo 5)");
        int numMiembros = scanner.nextInt();
        numMiembros = Math.min(numMiembros, 5); // Limita el número de miembros a 5
        int tipoPersonaje;

        for (int i = 0; i < numMiembros; i++) {
            System.out.println("¿Qué tipo de personaje quieres añadir? [ Rey(1), Caballero(2), Mago(3) ]");
            tipoPersonaje = scanner.nextInt();

            System.out.println("¿Cómo quieres llamar a este personaje?");
            String nombrePersonaje = scanner.next();

            if (nombrePersonaje.trim().isEmpty()) {
                switch (tipoPersonaje) {
                    case 1:
                        nombrePersonaje = "Rey " + (i + 1);
                        break;
                    case 2:
                        nombrePersonaje = "Caballero " + (i + 1);
                        break;
                    case 3:
                        nombrePersonaje = "Mago " + (i + 1);
                        break;
                }
            }
        
            switch (tipoPersonaje) {
                case 1:
                    ejercitoAliados.add(new Rey(new AtaqueEspada(), new AtaqueMagia(), nombrePersonaje, false, false, true)); // Asume que la clase Rey y AtaqueEspada existen
                    break;
                case 2:
                    ejercitoAliados.add(new Caballero(new AtaqueEspada(), new AtaqueArco(), nombrePersonaje, false, false, true)); // Asume que la clase Caballero y AtaqueEspada existen
                    break;
                case 3:
                    ejercitoAliados.add(new Mago(new AtaqueMagia(), new Curar(), nombrePersonaje, false, false, true)); // Asume que la clase Mago y AtaqueMagia existen
                    break;
                default:
                    System.out.println("Tipo de personaje no reconocido. Por favor, introduce 1, 2 o 3.");
                    i--; // Disminuye el contador para volver a preguntar
                    break;
            }
        }


        // Reclutar ejército de trolls
        int numTrolls = random.nextInt(11) + 5; // Número aleatorio de trolls entre 5 y 15
        EstrategiaAtaque[] ataques = {new AtaqueEspada(), new AtaqueArco(), new AtaqueCuchillo()};
        for (int i = 0; i < numTrolls; i++) {
            EstrategiaAtaque ataqueAleatorio = ataques[random.nextInt(ataques.length)];
            Personaje troll = new Troll(ataqueAleatorio, "Troll " + (i + 1));
            ejercitoTrolls.add(troll);
        }
    }

    public List<String> luchar() {
        List<String> registroDeAtaques = new ArrayList<>();
        int turno = 1;
    
        // Mientras haya aliados y trolls, la batalla continúa
        while (!ejercitoAliados.isEmpty() && !ejercitoTrolls.isEmpty()) {
            registroDeAtaques.add("Turno " + turno + ":");
    
            // Turno de los aliados
            for (Personaje aliado : new ArrayList<>(ejercitoAliados)) {
                System.out.println("Turno de " + aliado.getNombre() + ". Elige una acción: 1. Atacar, 2. Defender, 3. Pasar turno");
                int accion = scanner.nextInt();
    
                switch (accion) {
                    case 1: // Atacar
                        if (!ejercitoTrolls.isEmpty()) {
                            // Ataca a un troll aleatorio
                            Personaje trollAleatorio = ejercitoTrolls.get(random.nextInt(ejercitoTrolls.size()));
                            List<String> ataques = aliado.ataca(trollAleatorio);
                            registroDeAtaques.addAll(ataques);
    
                            // Si el troll muere, se elimina del ejército
                            if (trollAleatorio.getSalud() <= 0) {
                                ejercitoTrolls.remove(trollAleatorio);
                                registroDeAtaques.add(trollAleatorio.getNombre() + " ha muerto!");
                            }
                        }
                        break;
                    case 2: // Defender
                        // Implementa la lógica de defensa aquí
                        break;
                    case 3: // Pasar turno
                        // No hace nada
                        break;
                    default:
                        System.out.println("Acción no reconocida. Por favor, introduce 1, 2 o 3.");
                        break;
                }
            }
    
            // Turno de los trolls
            for (Personaje troll : new ArrayList<>(ejercitoTrolls)) {
                if (!ejercitoAliados.isEmpty()) {
                    // Ataca a un aliado aleatorio
                    Personaje aliadoAleatorio = ejercitoAliados.get(random.nextInt(ejercitoAliados.size()));
                    List<String> ataques = troll.ataca(aliadoAleatorio);
                    registroDeAtaques.addAll(ataques);
            
                    // Si el aliado muere, se elimina del ejército
                    if (aliadoAleatorio.getSalud() <= 0) {
                        ejercitoAliados.remove(aliadoAleatorio);
                        registroDeAtaques.add(aliadoAleatorio.getNombre() + " ha muerto!");
                    }
                }
            }
            turno++;
        }
        return registroDeAtaques;    
    }

    public List<Personaje> getEjercitoAliados() {
        return this.ejercitoAliados;
    }

    public List<Personaje> getEjercitoTrolls() {
        return this.ejercitoTrolls;
    }
}

