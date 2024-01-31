package ej1.servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

                StringBuilder pregunta = new StringBuilder();
                int byteLeido;
                // Leer byte a byte hasta encontrar el carácter "?"
                try (InputStreamReader reader = new InputStreamReader(entrada, "UTF-8")) {
                    // Leer byte a byte hasta encontrar el carácter "?"
                    while ((byteLeido = reader.read()) != -1) {
                        char caracter = (char) byteLeido;
                        pregunta.append(caracter);

                        if (caracter == '?') {
                            System.out.println("Pregunta recibida: " + pregunta.toString());

                            // Obtener respuesta y enviarla al cliente
                            String respuesta = obtenerRespuesta(pregunta.toString());
                            salida.write(respuesta.getBytes("UTF-8"));

                            // No olvidar cerrar la conexión del cliente
                            System.out.println("Cerramos conexión SUBSOCKET del cliente");
                            socketCliente.close();
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Esto se ejecutará cuando se cierre el servidor
            System.out.println("Cerramos Socket servidor");
        }
    }

    private static String obtenerRespuesta(String pregunta) {
        switch (pregunta.trim()) {
            case "¿Cómo te llamas?":
                return "Soy ejemplo de Socket";
            case "¿Qué día es hoy?":
                // Lógica para obtener la fecha actual
                return "Hoy es..." + obtenerFechaActual();
            default:
                return "No he entendido la pregunta, hazlo en java";
        }
    }

    private static String obtenerFechaActual() {
        // Lógica para obtener la fecha actual
        return "Fecha Actual";
    }
}

