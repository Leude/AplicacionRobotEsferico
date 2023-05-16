package sincronizacion;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import ventanas.VentanaPrincipal;

import javax.swing.*;
import java.util.List;

public class Conexion extends Thread {
    private VentanaPrincipal ventanaPrincipal;
    private PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    private List<String> serialPorts;
    private boolean conectado = false;

    private int contador = 0;
    private SerialPortEventListener arduino_evento = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent serialPortEvent) {
            try {
                if (arduino.isMessageAvailable()) {
                    String s = arduino.printMessage();
                    if (s.equals("Desactivando Control Manual") && contador == 0) {
                        contador = 4;
                        System.out.println(s);
                    } else if (contador > 0) {
                        switch (contador) {
                            case 4 -> {
                                int i = Integer.valueOf(s);
                                System.out.println(i);
                                ventanaPrincipal.getSliderBase().setValue(i);
                            }
                            case 3 -> {
                                int i = Integer.valueOf(s);
                                System.out.println(i);
                                ventanaPrincipal.getSliderElevacionBrazo().setValue(Integer.valueOf(s));
                            }
                            case 2 -> {
                                int i = Integer.valueOf(s);
                                System.out.println(i);
                                ventanaPrincipal.getSliderExtensionBrazo().setValue(Integer.valueOf(s));
                            }
                            case 1 -> {
                                int i = Integer.valueOf(s);
                                System.out.println(i);
                                ventanaPrincipal.getSliderGarra().setValue(Integer.valueOf(s));
                            }
                        }
                        contador--;
                    } else {
                        System.out.println(s);
                    }
                }
            } catch (SerialPortException e) {
                throw new RuntimeException(e);
            } catch (ArduinoException e) {
                throw new RuntimeException(e);
            }
        }
    };

    public Conexion(VentanaPrincipal ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;
        actualizarCombobox();
    }

    @Override
    public void run() {
        while (true) {
            if (conectado) {
                if (arduino.getPortsAvailable() == 0) {
                    desconectar();
                }
            } else {
                if (serialPorts.isEmpty()) {
                    actualizarCombobox();
                } else if (!serialPorts.equals(arduino.getSerialPorts())) {
                    actualizarCombobox();
                }
            }

            try {
                this.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void actualizarCombobox() {
        this.serialPorts = this.arduino.getSerialPorts();
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (String serialPort : serialPorts) {
            model.addElement(serialPort);
        }
        ventanaPrincipal.getComboBox1().setModel(model);
    }

    public void conectar(String port_name) {
        try {
            arduino = new PanamaHitek_Arduino();
            arduino.arduinoRXTX(port_name, 9600, arduino_evento);
            conectado = true;
            ventanaPrincipal.getBotonConectar().setEnabled(false);
            ventanaPrincipal.getBotonDesconectar().setEnabled(true);
        } catch (ArduinoException e) {

        }
    }

    public void desconectar() {
        conectado = false;
        ventanaPrincipal.getBotonDesconectar().setEnabled(false);
        ventanaPrincipal.getBotonConectar().setEnabled(true);
        try {
            arduino.killArduinoConnection();
        } catch (ArduinoException e) {

        }
    }

    public PanamaHitek_Arduino getArduino() {
        return arduino;
    }
}
