package dd.core;

import java.util.Random;

public class Curar implements EstrategiaAtaque {
    private static final int MIN_CURACION = 25;
    private static final int MAX_CURACION = 200;
    private Random random = new Random();

    @Override
    public int lanzaAtaque(Personaje personaje) {
        // Genera un valor de curación aleatorio entre MIN_CURACION y MAX_CURACION
        int curacion = random.nextInt(MAX_CURACION - MIN_CURACION + 1) + MIN_CURACION;
        personaje.curar(curacion); // Asume que la clase Personaje tiene un método curar
        return curacion;
    }

    @Override
    public String getNombreAtaque() {
        return "cura";
    }
}
