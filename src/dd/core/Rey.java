package dd.core;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rey extends Personaje {
    private EstrategiaAtaque estrategiaAtaque;
    private boolean modoAleatorio;
    private Scanner scanner = new Scanner(System.in);

    public Rey(EstrategiaAtaque estrategiaAtaque, String nombre, boolean modoAleatorio) {
        super(nombre, 0, 2000, estrategiaAtaque);
        this.estrategiaAtaque = estrategiaAtaque;
        this.modoAleatorio = modoAleatorio;
    }

    @Override
    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        if (!modoAleatorio && enemigo.getSalud() > 0) {
            System.out.println("Selecciona tu estrategia de ataque:");
            System.out.println("1. Ataque con espada");
            System.out.println("2. Ataque con magia");
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, introduce un número válido.");
                scanner.next(); // descarta la entrada incorrecta
            }
            int opcion = scanner.nextInt();
            scanner.nextLine();
            if (opcion == 1) {
                estrategiaAtaque = new AtaqueEspada();
            } else if (opcion == 2) {
                estrategiaAtaque = new AtaqueMagia();
            }
        }
        if (estrategiaAtaque != null) {
            int numeroAtaques = modoAleatorio ? 3 : 1; // En modo aleatorio ataca 3 veces, en modo normal 1 vez
            for (int i = 0; i < numeroAtaques; i++) {
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