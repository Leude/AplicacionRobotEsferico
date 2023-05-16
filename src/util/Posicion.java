package util;

public class Posicion {
    int base;
    int elevacionBrazo;
    int extensionBrazo;
    int garra;

    public Posicion(int base, int elevacionBrazo, int extensionBrazo, int garra) {
        this.base = base;
        this.elevacionBrazo = elevacionBrazo;
        this.extensionBrazo = extensionBrazo;
        this.garra = garra;
    }

    public int getBase() {
        return base;
    }

    public int getElevacionBrazo() {
        return elevacionBrazo;
    }

    public int getExtensionBrazo() {
        return extensionBrazo;
    }

    public int getGarra() {
        return garra;
    }

    @Override
    public String toString() {
        return "Posicion";
    }
}
