package Implementacion;

import Aereolinea.*;
import Data.Datos;
import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Principal implements Datos, ActionListener {

    private ArrayList<Vuelos> viajes = new ArrayList<>();
    private ArrayList<Personas> pasajeros = new ArrayList<>();
    private final PantallaPrincipal vistaPrincipal;

    public Principal(PantallaPrincipal p2) { 
        this.vistaPrincipal = p2;
        
        this.vistaPrincipal.getBtnCargarDatos().addActionListener(this);
        this.vistaPrincipal.getBtnMostrarCategoria().addActionListener(this);
        this.vistaPrincipal.getBtnReporte().addActionListener(this);
        this.vistaPrincipal.getBtnSalir().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getBtnCargarDatos()) {
            cargarDatos();
            JOptionPane.showMessageDialog(vistaPrincipal, "¡Datos cargados con éxito!");
        } else if (e.getSource() == vistaPrincipal.getBtnMostrarCategoria()) {
            //mostrarCategoria();
        } else if (e.getSource() == vistaPrincipal.getBtnReporte()) {
            //reporte();
        } else if (e.getSource() == vistaPrincipal.getBtnSalir()) {
            salir();
       }
    }
    
    public static void main(String[] args) {
        Login p1 = new Login();
        Implementacion.ControladorLogin controlador = new Implementacion.ControladorLogin(p1);
        p1.setLocationRelativeTo(null);
        p1.setVisible(true);
        p1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void cargarDatos() {
        // Limpiamos las listas por si el usuario le da clic al botón más de una vez
        viajes.clear();
        pasajeros.clear();

        // 1. CARGAR VUELOS
        for (int i = 0; i < datos_vuelos.length; i++) { 
            String lineaVuelo = datos_vuelos[i]; 
            String[] partesLinea = lineaVuelo.split(";");

            String aerolinea = partesLinea[0];
            String numVuelo   = partesLinea[1];
            String destino    = partesLinea[2];

            Vuelos v = new Vuelos(aerolinea, numVuelo, destino);
            viajes.add(v);
        }  

        // 2. CARGAR PERSONAS
        for (int i = 0; i < datos_persona.length; i++) {
            String lineaPersona = datos_persona[i];
            String[] datosPersona = lineaPersona.split(";");

            String tipo = datosPersona[0]; 
            int id = Integer.parseInt(datosPersona[1].replaceAll("[^0-9]", ""));
            String nombre = datosPersona[2];
            int edad = Integer.parseInt(datosPersona[3]);
            String codigoVuelo = datosPersona[4];

           
            Vuelos vueloAsignado = buscarVuelo(codigoVuelo);
            String categoriaInicial = "Sin Asignar";  
            
            
            switch (tipo) {
                case "PASAJERO":
                    String asiento = datosPersona[5];                    
                    int precioVuelo = Integer.parseInt(datosPersona[6]);  
                
                    Pasajero nuevoPasajero = new Pasajero(asiento, precioVuelo, id, nombre, edad, vueloAsignado, categoriaInicial);
                    pasajeros.add(nuevoPasajero);
                    break; 

                case "AZAFATA":
                    double estatura = Double.parseDouble(datosPersona[5]); 
                    int cantidadIdiomas = Integer.parseInt(datosPersona[6]);
                
                    Azafata nuevaAzafata = new Azafata(estatura, cantidadIdiomas, id, nombre, edad, vueloAsignado, categoriaInicial);
                    pasajeros.add(nuevaAzafata);
                    break; 

                case "PILOTO":
                    int horasVuelo = Integer.parseInt(datosPersona[5]); 
                
                    Piloto nuevoPiloto = new Piloto(horasVuelo, id, nombre, edad, vueloAsignado, categoriaInicial);
                    pasajeros.add(nuevoPiloto); 
                    break; 
            }
        }
    }   

    
    private Vuelos buscarVuelo(String numVuelo) {
        for (int i = 0; i < viajes.size(); i++) {
            Vuelos v = viajes.get(i);
            if (v.getNumVuelo().equalsIgnoreCase(numVuelo)) {
                return v;
            }
        }
        return null;
    }

    void salir() {
        int opcion = JOptionPane.showConfirmDialog(vistaPrincipal, 
                "¿Seguro que desea salir del sistema?", 
                "Salir", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}