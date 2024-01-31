package ej2.cliente;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClienteSocketStream {
    public static void main(String[] args) throws IOException {
        System.out.println("Creando el socket cliente");
        Socket cliente = new Socket();

        System.out.println("Establecemos conexion");
        InetSocketAddress dircServidor = new InetSocketAddress ("localhost", 55555);
        cliente.connect(dircServidor);

        //Creamos el flujo de salida
        OutputStream salida = cliente.getOutputStream();

        //Creamos el mensaje
        String mensaje = "ESTE ES EL MENSAJE";
        salida.write(mensaje.getBytes());

        System.out.println("Mensaje enviado");
        System.out.println("Cerrando el socket Cliente");
        cliente.close();
        System.out.println("Conexion socket Cliente cerrada");
    }
}
