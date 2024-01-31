package ej3.cliente;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteSocketStream {
    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Creando el socket cliente");
        Socket cliente = new Socket();

        System.out.println("Establecemos conexion");
        InetSocketAddress dircServidor = new InetSocketAddress ("localhost", 55555);
        cliente.connect(dircServidor);

        //Creamos el flujo de salida
        OutputStream salida = cliente.getOutputStream();
        InputStream entrada = cliente.getInputStream();

        //Introducir la letra
        System.out.println("Introduce una letra");
        String letraUsuario = teclado.next();
        //Pasamos a mandar la letra al servidor
        salida.write(letraUsuario.getBytes());
        //Recibimos la palabra que empieza por esa letra
        System.out.println("La palabra que empiza por esa letra es: ");
        byte[] mensaje = new byte[50];
        entrada.read(mensaje);
        String palabraResultado = new String(mensaje).trim();
        System.out.println(palabraResultado);
        System.out.println("Cerrando el socket Cliente");
        cliente.close();
        System.out.println("Conexion socket Cliente cerrada");
    }
}
