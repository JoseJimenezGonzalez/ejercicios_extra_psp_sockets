package ej3.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorSocketStream {
    public static void main(String[] args) {
        try {
            System.out.println("Creando socket Servidor");
            ServerSocket servidor = new ServerSocket();

            System.out.println("Asignar IP y puerto al servidor");
            InetSocketAddress dirServidor = new InetSocketAddress("localhost", 55555);
            servidor.bind(dirServidor);

            while (true) {
                System.out.println("Esperando conexiones");
                Socket socketCliente = servidor.accept();
                System.out.println("Conexion aceptada");

                // Procesar la conexión (entrada, salida, etc.)
                InputStream entrada = socketCliente.getInputStream();
                OutputStream salida = socketCliente.getOutputStream();

                byte[] mensaje = new byte[1000];
                entrada.read(mensaje);

                System.out.println("Mensaje recibido " + new String(mensaje).trim());

                System.out.println("Cerramos conexion SUBSOCKET del cliente");
                socketCliente.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Esto se ejecutará cuando se cierre el servidor
            System.out.println("Cerramos Socket servidor");
        }
    }
}
