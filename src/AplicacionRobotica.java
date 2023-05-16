import sincronizacion.Conexion;
import ventanas.VentanaPrincipal;

import javax.swing.*;

public class AplicacionRobotica {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        VentanaPrincipal panel = new VentanaPrincipal();
        frame.setContentPane(panel.getPanel());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        Conexion conexion = new Conexion(panel);
        panel.setConexion(conexion);
        conexion.start();
    }
}
