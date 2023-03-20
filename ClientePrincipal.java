package chatjava;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientePrincipal extends JFrame {
    private JTextField entradaMensaje;
    private JTextArea areaChat;
    private Cliente cliente;
    
    public ClientePrincipal(String nombre, String servidor, int puerto) {
        cliente = new Cliente(nombre, servidor, puerto);
        
        areaChat = new JTextArea();
        areaChat.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(areaChat);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        add(scrollPane, BorderLayout.CENTER);
        
        JPanel panel = new JPanel(new FlowLayout());
        entradaMensaje = new JTextField(30);
        panel.add(entradaMensaje);
        JButton enviarButton = new JButton("Enviar");
        enviarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mensaje = entradaMensaje.getText();
                cliente.enviarMensaje(mensaje);
                entradaMensaje.setText("");
            }
        });
        panel.add(enviarButton);
        add(panel, BorderLayout.SOUTH);
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void iniciar() {
        cliente.recibirMensajes();
    }
    
    public static void main(String[] args) {
        String nombre = JOptionPane.showInputDialog("Ingresa tu nombre:");
        String servidor = JOptionPane.showInputDialog("Ingresa el servidor:");
        int puerto = Integer.parseInt(JOptionPane.showInputDialog("Ingresa el puerto:"));
        
        ClientePrincipal cliente = new ClientePrincipal(nombre, servidor, puerto);
        cliente.iniciar();
    }
}
