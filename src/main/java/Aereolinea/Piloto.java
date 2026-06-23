
package Aereolinea;


public class Piloto extends Personas{
    private int horas;
    
    //constructor 

    public Piloto(int horas, int id, String nombres, int edad, Vuelos vuelo, String categoria) {
        super(id, nombres, edad, vuelo, categoria);
        this.horas = horas;
    }
    
    @Override
    public void calcularCategoria() {
        if (this.horas >= 250) {
            this.setCategoria("Piloto Privado");
        } else {
            this.setCategoria("Piloto Comercial");
        }
    }

    
    //getters setters

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }
    
}
