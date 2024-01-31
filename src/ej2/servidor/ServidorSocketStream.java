package ej2.servidor;

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

                //Recibimos el numero de palabras
                byte[] mensaje = new byte[50];
                entrada.read(mensaje);
                System.out.println("Se van a enviar " + new String(mensaje).trim() + " palabras.");
                int numeroPalabras = Integer.valueOf(new String(mensaje).trim());
                //Recibimos las palabras
                StringBuilder resultado = new StringBuilder();
                //Bucle que nos dice cuando parar segun el numero de palabras
                for (int i = 0; i < numeroPalabras; i++){
                    byte[] palabraBytes = new byte[50];
                    entrada.read(palabraBytes);
                    resultado.append(new String(palabraBytes).trim() + " ");
                }
                //Mostramos las palabras concatenadas
                System.out.println("El resultado de las palabras concatenadas.");
                System.out.println(resultado);
                //Mandamos el resultado al cliente
                salida.write(resultado.toString().getBytes());


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
