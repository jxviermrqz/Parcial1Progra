package Implementacion;

import Vista.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ControladorLogin implements ActionListener {
    private final Login vistaLogin;

    ControladorLogin(Login p1) {
        this.vistaLogin = p1;
        
        this.vistaLogin.getBtnIngresar().addActionListener(this);
        this.vistaLogin.getBtnSalir().addActionListener(this);    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaLogin.getBtnIngresar()) {
            comprobarUsuario();
        } else if (e.getSource() == vistaLogin.getBtnSalir()) {
            salir();
        } 
    }

    private void comprobarUsuario() {
        String usuario = vistaLogin.getTxtUsuario().getText().trim();
        String contraseña = vistaLogin.getTxtContraseña().getText();

        if (usuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(vistaLogin, 
                    "complete todos los campos.", 
                    "Campos Vacíos", 
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (usuario.equals("admin") && contraseña.equals("123")) {
            JOptionPane.showMessageDialog(vistaLogin, 
                    "Hola", 
                    "Ingreso", 
                    JOptionPane.INFORMATION_MESSAGE);
            PantallaPrincipal p2 = new PantallaPrincipal();
            p2.setLocationRelativeTo(null);
            p2.setVisible(true);
            p2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            vistaLogin.dispose(); 
        } else {
            JOptionPane.showMessageDialog(vistaLogin, 
                    "Usuario o contraseña incorrectos.", 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            
            vistaLogin.getTxtUsuario().setText("");
            vistaLogin.getTxtContraseña().setText("");
            vistaLogin.getTxtUsuario().requestFocusInWindow();
        }
    }

    private void salir() {
        int opcion = JOptionPane.showConfirmDialog(vistaLogin, 
                "seguro que desea salir del sistema?", 
                "Salir", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.QUESTION_MESSAGE);
        
        if (opcion == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    
}
