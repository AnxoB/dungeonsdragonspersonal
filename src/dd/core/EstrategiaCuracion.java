package dd.core;

// Interfaz EstrategiaAtaque
public interface EstrategiaCuracion {
    int lanzaAtaque(Personaje curador, Personaje receptor);
    String getNombreAtaque(); 
}
