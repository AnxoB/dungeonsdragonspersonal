package dd.core;

import java.util.ArrayList;
import java.util.List;

public class Mago extends Personaje {
    private EstrategiaAtaque estrategiaAtaque;

    public Mago(EstrategiaAtaque estrategiaAtaque, String nombre) {
        super(nombre, 0, 1000, estrategiaAtaque);
        this.estrategiaAtaque = estrategiaAtaque;
    }

    @Override
    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        if (estrategiaAtaque != null) {
            // El mago ataca 2 veces
            for (int i = 0; i < 2; i++) {
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
