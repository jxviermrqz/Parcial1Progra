
package Aereolinea;


public class Vuelos {
    private String nombreAerolinea;
    private String numVuelo; 
    private String destino;
    
    //constructor 

    public Vuelos(String nombreAerolinea, String numVuelo, String destino) {
        this.nombreAerolinea = nombreAerolinea;
        this.numVuelo = numVuelo;
        this.destino = destino;
    }
    
    //getters setters

    public String getNombreAerolinea() {
        return nombreAerolinea;
    }

    public void setNombreAerolinea(String nombreAerolinea) {
        this.nombreAerolinea = nombreAerolinea;
    }

    public String getNumVuelo() {
        return numVuelo;
    }

    public void setNumVuelo(String numVuelo) {
        this.numVuelo = numVuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
    
}
