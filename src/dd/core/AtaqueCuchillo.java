package dd.core;

import java.util.Random;

public class AtaqueCuchillo implements EstrategiaAtaque {
    private static final int ATAQUE = 25;
    private Random random = new Random();

    @Override
    public int lanzaAtaque(Personaje enemigo) {
        //acierta * factor * ATAQUE
        int acierta = random.nextInt(2);
        double factor = random.nextDouble();
        int ataque = (int) (acierta * factor * ATAQUE);

        //Mirar si acierta o no
        if (acierta == 0) {
            return 0;
        } else {
            return ataque;
        }
    }

    @Override
    public String getNombreAtaque() {
        return "cuchillo";
    }
}