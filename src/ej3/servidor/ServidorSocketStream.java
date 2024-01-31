package ej3.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServidorSocketStream {
    public static void main(String[] args) {
        Map<String, String> mapaPalabras = new HashMap<>();

        // Agregar datos al HashMap
        mapaPalabras.put("a ", "Amigo");
        mapaPalabras.put("b", "Bicicleta");
        mapaPalabras.put("c", "Casa");
        mapaPalabras.put("d", "Dedo");
        mapaPalabras.put("e", "Elefante");
        mapaPalabras.put("f", "Familia");
        mapaPalabras.put("g", "Gato");
        mapaPalabras.put("h", "Helado");
        mapaPalabras.put("i", "Isla");
        mapaPalabras.put("j", "Juego");
        mapaPalabras.put("k", "Koala");
        mapaPalabras.put("l", "Lapicero");
        mapaPalabras.put("m", "Mesa");
        mapaPalabras.put("n", "Nube");
        mapaPalabras.put("ñ", "Ñoqui");
        mapaPalabras.put("o", "Ola");
        mapaPalabras.put("p", "Perro");
        mapaPalabras.put("q", "Queso");
        mapaPalabras.put("r", "React");
        mapaPalabras.put("s", "Sol");
        mapaPalabras.put("t", "Tomate");
        mapaPalabras.put("u", "Uva");
        mapaPalabras.put("v", "Ventana");
        mapaPalabras.put("w", "Waffles");
        mapaPalabras.put("x", "Xenomorfo");
        mapaPalabras.put("y", "Yogur");
        mapaPalabras.put("z", "Zorro");


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

                //Leemos la letra que nos manda el cliente
                byte[] mensaje = new byte[10];
                entrada.read(mensaje);
                String letraCliente = new String(mensaje).trim();
                String palabraAsociadaLetra = mapaPalabras.get(letraCliente);
                //Mandamos la palabra que nos ha devulto el hash map
                salida.write(palabraAsociadaLetra.getBytes());

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
