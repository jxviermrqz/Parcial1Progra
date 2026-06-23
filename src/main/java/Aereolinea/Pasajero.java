
package Aereolinea;


public class Pasajero extends Personas{
    private String numAsiento;
    private int valorPasaje;
    
    //constructor 

    public Pasajero(String numAsiento, int valorPasaje, int id, String nombres, int edad, Vuelos vuelo, String categoria) {
        super(id, nombres, edad, vuelo, categoria);
        this.numAsiento = numAsiento;
        this.valorPasaje = valorPasaje;
    }
    
    
    @Override
    public void calcularCategoria() {
        // Si pagó un pasaje mayor a 300 es VIP, de lo contrario es Regular
        if (this.valorPasaje > 300) {
            this.setCategoria("Clase VIP");
        } else {
            this.setCategoria("Clase Regular");
        }
    }
    
    //getters setters 

    public String getNumAsiento() {
        return numAsiento;
    }

    public void setNumAsiento(String numAsiento) {
        this.numAsiento = numAsiento;
    }

    public int getValorPasaje() {
        return valorPasaje;
    }

    public void setValorPasaje(int valorPasaje) {
        this.valorPasaje = valorPasaje;
    }
    
    
}
