package dd.main;

import dd.core.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Batalla {
    private List<Personaje> ejercitoAliados;
    private List<Personaje> ejercitoTrolls;
    private Random rand;
    
    // Constructor de la clase Batalla
    public Batalla(List<Personaje> ejercitoAliados, List<Personaje> ejercitoTrolls) {
        this.ejercitoAliados = ejercitoAliados;
        this.ejercitoTrolls = ejercitoTrolls;
        this.rand = new Random();

        //Crear ejercito aliado
        Personaje p1 = new Rey(new AtaqueEspada(), "Arturo", true);
        Personaje p2 = new Caballero(new AtaqueEspada(), "Lancelot");
        Personaje p3 = new Caballero(new AtaqueArco(), "Percival");
        ejercitoAliados.add(p1);
        ejercitoAliados.add(p2);
        ejercitoAliados.add(p3);

        //Crear ejercito de trolls
        int numTrolls = rand.nextInt(9) + 2; // Genera un número aleatorio entre 2 y 10

        //Array de ataques
        EstrategiaAtaque[] ataques = {new AtaqueEspada(), new AtaqueArco(), new AtaqueCuchillo()};

        //Crear trolls con ataques aleatorios
        for (int i = 0; i < numTrolls; i++) {
            EstrategiaAtaque ataqueAleatorio = ataques[rand.nextInt(ataques.length)];
            Personaje troll = new Troll(ataqueAleatorio, "Troll " + (i + 1));
            ejercitoTrolls.add(troll);
        }
        
    }
    
    // Método para luchar
    public List<String> luchar() {
        
        List<String> registroDeAtaques = new ArrayList<>();
        int turno = 1;

        // Mientras haya aliados y trolls, la batalla continúa
        while (!ejercitoAliados.isEmpty() && !ejercitoTrolls.isEmpty()) {
            registroDeAtaques.add("Turno " + turno + ":");

            // Turno de los aliados
            for (Personaje aliado : new ArrayList<>(ejercitoAliados)) {
                if (!ejercitoTrolls.isEmpty()) {
                    // Ataca a un troll aleatorio
                    Personaje trollAleatorio = ejercitoTrolls.get(rand.nextInt(ejercitoTrolls.size()));
                    List<String> ataques = aliado.ataca(trollAleatorio);
                    registroDeAtaques.addAll(ataques);

                    // Si el troll muere, se elimina del ejército
                    if (trollAleatorio.getSalud() <= 0) {
                        ejercitoTrolls.remove(trollAleatorio);
                        registroDeAtaques.add(trollAleatorio.getNombre() + " ha muerto!");
                    }
                }
            }
    
            // Turno de los trolls
            for (Personaje troll : new ArrayList<>(ejercitoTrolls)) {
                if (!ejercitoAliados.isEmpty()) {
                    // Ataca a un aliado aleatorio
                    Personaje aliadoAleatorio = ejercitoAliados.get(rand.nextInt(ejercitoAliados.size()));
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
    
}
