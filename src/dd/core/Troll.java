package dd.core;

import java.util.ArrayList;
import java.util.List;

public class Troll extends Personaje {
    private EstrategiaAtaque estrategiaAtaque;

    public Troll(EstrategiaAtaque estrategiaAtaque, String nombre) {
        super(nombre, 0, 1000, estrategiaAtaque);
        this.estrategiaAtaque = estrategiaAtaque;
    }

    // Sobrescribe el método ataca de la clase Personaje
    @Override
    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        if (enemigo.getSalud() <= 0) {
            return registros; // Devuelve una lista vacía si el enemigo ya está muerto
        }
        // El troll ataca 1 vez
        if (estrategiaAtaque != null) {
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
        return registros;
    }
}
