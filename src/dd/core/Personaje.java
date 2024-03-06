package dd.core;

import java.util.ArrayList;
import java.util.List;

public abstract class Personaje {
    private String nombre;
    private int ataque;
    private int salud;
    private EstrategiaAtaque estrategiaAtaque;

    // Constructor de la clase Personaje
    public Personaje(String nombre, int ataque, int salud, EstrategiaAtaque estrategiaAtaque) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.salud = salud;
        this.estrategiaAtaque = estrategiaAtaque;
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getAtaque() {
        return ataque;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public int getSalud() {
        return salud;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }


    public List<String> ataca(Personaje enemigo) {
        List<String> registros = new ArrayList<>();
        this.estrategiaAtaque.lanzaAtaque(enemigo);
        return registros;
    }

    public void curar(int cantidad) {
        this.salud += cantidad;
    }
}
