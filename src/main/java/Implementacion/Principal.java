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
        this.vistaPrincipal.getBtnReporte().addActionListener(this);
        this.vistaPrincipal.getBtnSalir().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaPrincipal.getBtnCargarDatos()) {
            vistaPrincipal.getjTabbedPane1().setVisible(true);
            System.out.println("entro a cargardatos");
            cargarDatos(); 
            llenarTabla(); 
        
            vistaPrincipal.getjTabbedPane1().setVisible(true);
        
            System.out.println("entro a cargardatos y lleno la tabla");
            JOptionPane.showMessageDialog(vistaPrincipal, "¡Datos cargados con éxito!");
        } else if (e.getSource() == vistaPrincipal.getBtnReporte()) {
            reporte();
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

        //CARGAR VUELOS
        for (int i = 0; i < datos_vuelos.length; i++) { 
            String lineaVuelo = datos_vuelos[i]; 
            String[] partesLinea = lineaVuelo.split(";");

            String aerolinea = partesLinea[0];
            String numVuelo   = partesLinea[1];
            String destino    = partesLinea[2];

            Vuelos v = new Vuelos(aerolinea, numVuelo, destino);
            viajes.add(v);
        }  

        //CARGAR PERSONAS
        for (int i = 0; i < datos_persona.length; i++) {
            String lineaPersona = datos_persona[i];
            String[] datosPersona = lineaPersona.split(";");

            String tipo = datosPersona[0]; 
            int id = Integer.parseInt(datosPersona[1].replaceAll("[^0-9]", ""));
            String nombre = datosPersona[2];
            int edad = Integer.parseInt(datosPersona[3]);
            String codigoVuelo = datosPersona[4];

           
            Vuelos vueloAsignado = buscarVuelo(codigoVuelo);
            String categoriaInicial = ""; 
            
            
            switch (tipo) {
                case "PASAJERO":
                    String asiento = datosPersona[5];                    
                    int precioVuelo = Integer.parseInt(datosPersona[6]);  
                
                    Pasajero nuevoPasajero = new Pasajero(asiento, precioVuelo, id, nombre, edad, vueloAsignado, categoriaInicial);
                    nuevoPasajero.calcularCategoria();
                    pasajeros.add(nuevoPasajero);
                    break; 

                case "AZAFATA":
                    double estatura = Double.parseDouble(datosPersona[5]); 
                    int cantidadIdiomas = Integer.parseInt(datosPersona[6]);
                
                    Azafata nuevaAzafata = new Azafata(estatura, cantidadIdiomas, id, nombre, edad, vueloAsignado, categoriaInicial);
                    nuevaAzafata.calcularCategoria();
                    pasajeros.add(nuevaAzafata);
                    break; 

                case "PILOTO":
                    int horasVuelo = Integer.parseInt(datosPersona[5]); 
                
                    Piloto nuevoPiloto = new Piloto(horasVuelo, id, nombre, edad, vueloAsignado, categoriaInicial);
                    nuevoPiloto.calcularCategoria();
                    pasajeros.add(nuevoPiloto); 
                    break; 
            }
        }
    } 
    
    public void llenarTabla() {
    String[] columnas = {"Cédula/ID", "Nombre", "Edad", "Tipo", "Vuelo/Destino", "Dato Particular", "Categoría"};
    
    javax.swing.table.DefaultTableModel modelo = new javax.swing.table.DefaultTableModel(columnas, 0);
    
        for (int i = 0; i < pasajeros.size(); i++) {
            Personas p = pasajeros.get(i);
                    
            String tipoPersona = "";
            String datoParticular = "";
        
            if (p instanceof Pasajero pas) {
                tipoPersona = "PASAJERO";
                datoParticular = "Asiento: " + pas.getNumAsiento() + " ($" + pas.getValorPasaje() + ")";    
            } else if (p instanceof Azafata aza) {
                tipoPersona = "AZAFATA";
                datoParticular = "Idiomas: " + aza.getIdiomas() + " | Altura: " + aza.getAltura(); 
            } else if (p instanceof Piloto pil) {
                tipoPersona = "PILOTO";
                datoParticular = "Horas de Vuelo: " + pil.getHoras(); 
            }
            String infoVuelo = (p.getVuelo() != null) 
                ? p.getVuelo().getNumVuelo() + " a " + p.getVuelo().getDestino() 
                : "Sin Vuelo";
            Object[] fila = {
                p.getId(),               
                p.getNombres(),           
                p.getEdad(),           
                tipoPersona,
                infoVuelo,
                datoParticular, 
                p.getCategoria()
            };        
            modelo.addRow(fila);
        }
           vistaPrincipal.getjTable1().setModel(modelo);
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
    
    public void reporte(){
        ArrayList<String> listaDestinos = new ArrayList<>();
        ArrayList<Integer> cantP = new ArrayList<>();
        ArrayList<Integer> dinero = new ArrayList<>();
        
        int totalP = 0;
        int totalTripula = 0;
        
        for (int i = 0; i <pasajeros.size(); i++) {
            Personas p = pasajeros.get(i);
            if (p instanceof Pasajero pas) {
                totalP++;
                if (pas.getVuelo() != null) {
                    String d= pas.getVuelo().getDestino();
                    int posicion = listaDestinos.indexOf(d);
                    if (posicion ==-1) {
                        listaDestinos.add(d);
                        cantP.add(1);
                        dinero.add(pas.getValorPasaje());
                    }else{
                        cantP.set(posicion, cantP.get(posicion) + 1);
                        dinero.set(posicion, dinero.get(posicion) + pas.getValorPasaje());
                    }
                }
            }else{
                totalTripula++;
            }
        }
        String destinoMasVisitado = "";
        int maximoPasajeros = -1;
        int dineroRecaudadoDestino = 0;

        for (int i = 0; i < listaDestinos.size(); i++) {
            if (cantP.get(i) > maximoPasajeros) {
                maximoPasajeros = cantP.get(i);
                destinoMasVisitado = listaDestinos.get(i);
                dineroRecaudadoDestino = dinero.get(i);
            }
        }
        Piloto topPiloto = null;
        int mayorHoras = -1;

        for (int i = 0; i < pasajeros.size(); i++) {
            Personas p = pasajeros.get(i);
            if (p instanceof Piloto pil) {
                if (pil.getHoras() > mayorHoras) {
                    mayorHoras = pil.getHoras();
                    topPiloto = pil;
                }
            }
        }
        // Armamos el texto final para mostrarlo en el componente de texto plano
        String txt = "==================================================\n";
        txt += "            REPORTE ESTADÍSTICO DE OPERACIONES    \n";
        txt += "==================================================\n\n";
        
        txt += "1. INFORMACIÓN GENERAL DE TRIPULACIÓN Y PASAJEROS\n";
        txt += "--------------------------------------------------\n";
        txt += "• Total Pasajeros Procesados: " + totalP + "\n";
        txt += "• Total Personal de Tripulación: " + totalTripula + "\n\n";
        
        txt += "2. DESTINO CON MAYOR CANTIDAD DE PASAJEROS\n";
        txt += "--------------------------------------------------\n";
        txt += "• Destino con más demanda: " + destinoMasVisitado + "\n";
        txt += "• Total Pasajeros hacia allá: " + maximoPasajeros + "\n";
        txt += "• Monto recaudado en este destino: $" + dineroRecaudadoDestino + " USD\n\n";
        
        txt += "3. PILOTO CON MAYOR CANTIDAD DE HORAS DE VUELO\n";
        txt += "--------------------------------------------------\n";
        if (topPiloto != null) {
            txt += "• Piloto Destacado: " + topPiloto.getNombres() + "\n";
            txt += "• Cédula / ID: " + topPiloto.getId() + "\n";
            txt += "• Horas Registradas: " + mayorHoras + " horas\n";
        } else {
            txt += "• No se encontraron pilotos en el sistema.\n";
        }
        txt += "==================================================\n";

        // Pintamos el reporte en la interfaz y cambiamos el JTabbedPane a la pestaña 2 (índice 1)
        vistaPrincipal.getjTextPane1().setText(txt);
        vistaPrincipal.getjTabbedPane1().setSelectedIndex(1);
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