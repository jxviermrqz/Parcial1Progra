package Implementacion;

import Aereolinea.*;
import Data.Datos;
import Vista.*;
import java.util.ArrayList;
import javax.swing.JFrame;

public class Principal implements Datos {

    private ArrayList<Vuelos> viajes = new ArrayList<>();
    private ArrayList<Personas> pasajeros = new ArrayList<>(); 
    
    public static void main(String[] args) {
        Login p1 = new Login();
        Implementacion.ControladorLogin controlador = new Implementacion.ControladorLogin(p1);
        p1.setLocationRelativeTo(null);
        p1.setVisible(true);
        p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
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

            //Vuelos vueloAsignado = buscarVuelo(codigoVuelo);
            String categoriaInicial = "no se";   
        }
    }   
}
