package ventanas;

import sincronizacion.Conexion;
import util.Posicion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import static java.lang.Thread.sleep;

public class VentanaPrincipal {
    DefaultListModel model;
    private JPanel panel;
    private JComboBox comboBox1;
    private JButton botonConectar;
    private JTextField textField1;
    private JButton botonDesconectar;
    private JSlider sliderGarra;
    private JSlider sliderExtensionBrazo;
    private JSlider sliderElevacionBrazo;
    private JSlider sliderBase;
    private JList listaPosiciones;
    private JButton botonActivarPosicion;
    private JButton botonDesactivarPosicion;
    private JButton botonActivarManual;
    private JButton botonDesactivarManual;
    private JButton guardarPosicionButton;
    private JButton iniciarButton;
    private JButton calibrarButton;
    private JButton selecionarPosicionButton;
    private JPanel panelPosicion;
    private JPanel panelManual;
    private JButton cambiarPosicionButton;
    private JButton bajarBrazoButton;
    private JButton ocultarBrazoButton;
    private JButton girarBaseButton1;
    private JButton girarBaseButton;
    private JButton subirBrazoButton;
    private JButton extenderBrazoButton;
    private JButton abrirPinzaButton1;
    private JButton cerrarPinzaButton;

    private Vector<Posicion> posicions = new Vector<>();

    private Conexion conexion;


    public VentanaPrincipal() {
        panel.setFocusable(true);

        botonConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conexion.conectar((String) getComboBox1().getItemAt(getComboBox1().getSelectedIndex()));
            }
        });

        botonDesconectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conexion.desconectar();
            }
        });

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getExtendedKeyCode() == KeyEvent.VK_UP) {
                    try {
                        conexion.getArduino().sendData("i");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_DOWN) {
                    try {
                        conexion.getArduino().sendData("k");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_LEFT) {
                    try {
                        conexion.getArduino().sendData("j");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_RIGHT) {
                    try {
                        conexion.getArduino().sendData("l");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_O) {
                    try {
                        conexion.getArduino().sendData("o");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_P) {
                    try {
                        conexion.getArduino().sendData("P");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_N) {
                    try {
                        conexion.getArduino().sendData("M");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
                if (e.getExtendedKeyCode() == KeyEvent.VK_M) {
                    try {
                        conexion.getArduino().sendData("N");
                    } catch (Exception ex) {
                        System.err.println("Se produjo un error en la comunicacion");
                    }
                }
            }
        });


        botonActivarManual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexion.getArduino().sendData("z");
                    for (Component component : panelPosicion.getComponents()) {
                        component.setEnabled(false);
                    }
                    calibrarButton.setEnabled(false);
                } catch (Exception ex) {
                    System.err.println("Se produjo un error en la comunicacion");
                }
            }
        });
        botonDesactivarManual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexion.getArduino().sendData("e");
                    for (Component component : panelPosicion.getComponents()) {
                        component.setEnabled(true);
                    }
                    calibrarButton.setEnabled(true);
                } catch (Exception ex) {
                    System.err.println("Se produjo un error en la comunicacion");
                }
            }
        });
        calibrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexion.getArduino().sendData("c");
                } catch (Exception ex) {
                    System.err.println("Se produjo un error en la comunicacion");
                }
            }
        });
        botonActivarPosicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexion.getArduino().sendData("x");
                    for (Component component : panelManual.getComponents()) {
                        component.setEnabled(false);
                    }
                    calibrarButton.setEnabled(false);
                } catch (Exception ex) {
                    System.err.println("Se produjo un error en la comunicacion");
                }
            }
        });
        botonDesactivarPosicion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    conexion.getArduino().sendData("-1");
                    for (Component component : panelManual.getComponents()) {
                        component.setEnabled(true);
                    }
                    calibrarButton.setEnabled(true);
                } catch (Exception ex) {
                    System.err.println("Se produjo un error en la comunicacion");
                }
            }
        });

        guardarPosicionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                posicions.add(new Posicion(sliderBase.getValue(),sliderElevacionBrazo.getValue(),sliderExtensionBrazo.getValue(),sliderGarra.getValue()));
                model = new DefaultListModel();
                model.addAll(posicions);
                listaPosiciones.setModel(model);
            }
        });
        listaPosiciones.addComponentListener(new ComponentAdapter() {
        });
        selecionarPosicionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Posicion posicion = posicions.get(listaPosiciones.getSelectedIndex());
                sliderBase.setValue(posicion.getBase());
                sliderElevacionBrazo.setValue(posicion.getElevacionBrazo());
                sliderExtensionBrazo.setValue(posicion.getExtensionBrazo());
                sliderGarra.setValue(posicion.getGarra());
            }
        });
        cambiarPosicionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Runnable thread = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            conexion.getArduino().sendData(String.valueOf(sliderBase.getValue()));
                            sleep(2000);
                            conexion.getArduino().sendData(String.valueOf(sliderElevacionBrazo.getValue()));
                            sleep(2000);
                            conexion.getArduino().sendData(String.valueOf(sliderExtensionBrazo.getValue()));
                            sleep(2000);
                            conexion.getArduino().sendData(String.valueOf(sliderGarra.getValue()));
                        } catch (Exception ex) {
                            System.err.println("Se produjo un error en la comunicacion");
                        }
                    }
                };
                thread.run();
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    public JComboBox getComboBox1() {
        return comboBox1;
    }

    public void setConexion(Conexion conexion) {
        this.conexion = conexion;
    }

    public JButton getConexionButton() {
        return botonConectar;
    }

    public JButton getBotonDesconectar() {
        return botonDesconectar;
    }

    public JButton getBotonConectar() {
        return botonConectar;
    }

    public JSlider getSliderGarra() {
        return sliderGarra;
    }

    public JSlider getSliderExtensionBrazo() {
        return sliderExtensionBrazo;
    }

    public JSlider getSliderElevacionBrazo() {
        return sliderElevacionBrazo;
    }

    public JSlider getSliderBase() {
        return sliderBase;
    }
}
