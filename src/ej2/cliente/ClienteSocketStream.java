package ej2.cliente;

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

        //Creamos los flujos
        OutputStream salida = cliente.getOutputStream();
        InputStream entrada = cliente.getInputStream();

        //Primero va el mensaje de cuantas palabras se van a concatenar
        int palabrasEnviar = 5;
        String mensajeNumeroPalabras = String.valueOf(palabrasEnviar);
        salida.write(mensajeNumeroPalabras.getBytes());
        //Se van a enviar las palabras una a una
        for (int i = 0; i < palabrasEnviar ; i++){
            System.out.println("Introduce palabra");
            String palabra = teclado.next();
            salida.write(palabra.getBytes());
        }

        //Recibimos las palabras concatenadas
        byte[] mensaje = new byte[500];
        entrada.read(mensaje);
        String resultado = new String(mensaje).trim();
        //Mostramos las palabras concatenadas
        System.out.println("El resultado de las palabras concatenadas.");
        System.out.println(resultado);
        System.out.println("Cerrando el socket Cliente");
        cliente.close();
        System.out.println("Conexion socket Cliente cerrada");
    }
}
