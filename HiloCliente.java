package chatjava;

import chatjava.ServidorPrincipal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HiloCliente extends Thread {
    private Socket cliente;
    private BufferedReader entrada;
    private PrintWriter salida;
    
    public HiloCliente(Socket cliente) {
        this.cliente = cliente;
        try {
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            salida = new PrintWriter(cliente.getOutputStream(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        try {
            String nombre = entrada.readLine();
            System.out.println("El cliente " + nombre + " se ha conectado");
            String mensaje;
            
            do {
                mensaje = entrada.readLine();
                System.out.println(nombre + ": " + mensaje);
                enviarMensaje(nombre, mensaje);
            } while (!mensaje.equals("adios"));
            
            cliente.close();
            System.out.println("El cliente " + nombre + " se ha desconectado");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviarMensaje(String nombre, String mensaje) {
        for (HiloCliente hilo : ServidorPrincipal.getHilos()) {
            if (hilo != this) {
                hilo.salida.println(nombre + ": " + mensaje);
            }
        }
    }
    
    public PrintWriter getSalida() {
        return salida;
    }
}