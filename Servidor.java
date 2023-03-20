package chatjava;

import chatjava.HiloCliente;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private int puerto;
    
    public Servidor(int puerto) {
        this.puerto = puerto;
    }
    
    public void iniciar() {
        try {
            ServerSocket servidor = new ServerSocket(puerto);
            System.out.println("Servidor iniciado en el puerto " + puerto);
            
            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress().getHostName());
                
                // Creamos un hilo para manejar la comunicación con el cliente
                HiloCliente hilo = new HiloCliente(cliente);
                hilo.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}