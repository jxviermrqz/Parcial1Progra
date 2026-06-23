package Implementacion;

import Aereolinea.*;
import Data.Datos;
import java.util.ArrayList;

public class Principal implements Datos {

    private ArrayList<Vuelos> viajes = new ArrayList<>();
    private ArrayList<Personas> pasajeros = new ArrayList<>(); 
    
    public void cargarDatos(){
    for (int i = 0; i < datos_vuelos.length; i++) { 
    
    String lineaVuelo = datos_vuelos[i]; 
    
    
    String[] partesLinea = lineaVuelo.split(";");
    
    String aerolinea = partesLinea[0];
    String numVuelo   = partesLinea[1];
    String destino    = partesLinea[2];
    
    Vuelos v = new Vuelos(aerolinea, numVuelo, destino);
    viajes.add(v);
    
}
    for (int i = 0; i < datos_persona.length; i++) {
        
        String lineaPersona = datos_persona[i];
        
        String[] datosPersona = lineaPersona.split(";");
        
        String tipo = datosPersona[0]; 
        int id = Integer.parseInt(datosPersona[1].replaceAll("[^0-9]", ""));
        String nombre = datosPersona[2];
        int edad = Integer.parseInt(datosPersona[3]);
        String codigoVuelo = datosPersona[4];
        
        Vuelos vueloAsignado = buscarVuelo(codigoVuelo);
        String categoriaInicial = "no se";
        
        
        
    }
    
}
    
    public static void main(String[] args) {
        Principal programa = new Principal();
        
    }
}
