package dd.core;

import java.util.ArrayList;
import java.util.List;

// La clase Rey extiende de la clase Personaje, por lo que es un tipo de Personaje
public class Rey extends Personaje {
    private EstrategiaAtaque estrategiaAtaque;

    // Constructor de la clase Rey
    public Rey(EstrategiaAtaque estrategiaAtaque, String nombre) {
        super(nombre, 0, 2000, estrategiaAtaque);
        this.estrategiaAtaque = estrategiaAtaque;
    }

    // Sobrescribe el método ataca de la clase Personaje
    @Override
    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        if (estrategiaAtaque != null) {
            // El rey ataca 3 veces
            for (int i = 0; i < 3; i++) {
                if (enemigo.getSalud() <= 0) {
                    break; // Detiene los ataques adicionales si el enemigo ya está muerto
                }
                int valorAtaque = estrategiaAtaque.lanzaAtaque(enemigo);
                if (valorAtaque > 0) {
                    enemigo.setSalud(enemigo.getSalud() - valorAtaque);
                }
                String registro = this.getNombre() + " [" + this.getSalud() + "] ataca con "+ estrategiaAtaque.getNombreAtaque()+" contra " + enemigo.getNombre() + " [" + enemigo.getSalud() + "]";
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