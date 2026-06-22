
package Aereolinea;

public class Azafata extends Personas {
    private double altura;
    private int idiomas;
    
    //constrctor

    public Azafata(double altura, int idiomas, int id, String nombres, int edad, Vuelos vuelo, String categoria) {
        super(id, nombres, edad, vuelo, categoria);
        this.altura = altura;
        this.idiomas = idiomas;
    }
    
    //getters setters 

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public int getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(int idiomas) {
        this.idiomas = idiomas;
    }
    
}
