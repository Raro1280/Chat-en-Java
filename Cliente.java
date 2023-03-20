package chatjava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
    private String nombre;
    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;
    
    public Cliente(String nombre, String servidor, int puerto) {
        this.nombre = nombre;
        try {
            socket = new Socket(servidor, puerto);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            salida.println(nombre);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void enviarMensaje(String mensaje) {
        salida.println(mensaje);
    }
    
    public void recibirMensajes() {
        try {
            String mensaje;
            
            do {
                mensaje = entrada.readLine();
                System.out.println(mensaje);
            } while (!mensaje.equals(nombre + ": adios"));
            
                socket.close();
                System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
