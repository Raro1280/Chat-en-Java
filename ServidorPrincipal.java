package chatjava;

import java.util.ArrayList;
import java.util.List;

public class ServidorPrincipal {
    private static List<HiloCliente> hilos = new ArrayList<>();
    
    public static List<HiloCliente> getHilos() {
        return hilos;
    }
    
    public static void main(String[] args) {
        Servidor servidor = new Servidor(12345);
        servidor.iniciar();
    }
}
