
package Aereolinea;


public class Piloto extends Personas{
    private int horas;
    
    //constructor 

    public Piloto(int id, String nombres, int edad, Vuelos vuelo, String categoria) {
        super(id, nombres, edad, vuelo, categoria);
    }
    
    //getters setters

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
}
