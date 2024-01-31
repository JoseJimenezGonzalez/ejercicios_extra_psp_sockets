package ej1.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStream {
    public static void main(String[] args) throws IOException {
        System.out.println("Creando el socket cliente");
        Socket cliente = new Socket();

        System.out.println("Establecemos conexión");
        InetSocketAddress dirServidor = new InetSocketAddress("localhost", 55555);
        cliente.connect(dirServidor);

        // Creamos el flujo de salida
        OutputStream salida = cliente.getOutputStream();
        InputStream entrada = cliente.getInputStream();

        // Creamos el mensaje
        String mensaje = "¿Cómo te llamas?";

        // Enviamos el mensaje al servidor
        salida.write(mensaje.getBytes());

        // Leemos la respuesta del servidor
        byte[] respuestaBytes = new byte[1000];
        int bytesRecibidos = entrada.read(respuestaBytes);
        String respuesta = new String(respuestaBytes, 0, bytesRecibidos);

        System.out.println("Respuesta del servidor: " + respuesta);

        // Cerramos la conexión
        System.out.println("Cerrando el socket cliente");
        cliente.close();
        System.out.println("Conexión socket cliente cerrada");
    }
}

