package dd.core;

import java.util.Random;

public class Curar implements EstrategiaCuracion {
    private static final int MIN_CURACION = 25;
    private static final int MAX_CURACION = 200;
    private Random random = new Random();

    public int lanzaAtaque(Personaje curador, Personaje receptor) {
        // Genera un valor de curación aleatorio entre MIN_CURACION y MAX_CURACION
        int curacion = random.nextInt(MAX_CURACION - MIN_CURACION + 1) + MIN_CURACION;
        receptor.curar(curacion); // Cura al personaje receptor
        return curacion;
    }

    @Override
    public String getNombreAtaque() {
        return "cura";
    }
}
