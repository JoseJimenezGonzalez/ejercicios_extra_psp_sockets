package ej5.receptor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ReceptorDatagram {

    public static void main(String[] args) throws IOException {

        System.out.println("Creamos el DatagramSocket 2");

        InetSocketAddress dirApi2 = new InetSocketAddress("localhost", 55556);
        DatagramSocket socketApi2 = new DatagramSocket(dirApi2);

        try {
            while (true) {
                System.out.println("Esperando respuesta DatagramSocket 1");

                byte[] respuesta = new byte[100];
                DatagramPacket datagrama = new DatagramPacket(respuesta, respuesta.length);
                socketApi2.receive(datagrama);

                String strRespuesta = new String(datagrama.getData(), 0, datagrama.getLength()).trim();

                // Condicionales según contenga una palabra u otra
                String mensajeSegunPalabra = "";
                if (strRespuesta.contains("Hola")) {
                    mensajeSegunPalabra = "Olas que vienen, olas que van, hola que tal como estas?";
                } else if (strRespuesta.contains("Adiós")) {
                    mensajeSegunPalabra = "Hasta luego";
                } else {
                    mensajeSegunPalabra = "He recibido tu mensaje";
                }

                System.out.println("Enviamos respuesta a DatagramSocket 1");
                DatagramPacket mensajeResp = new DatagramPacket(mensajeSegunPalabra.getBytes(),
                        mensajeSegunPalabra.getBytes().length, datagrama.getAddress(), datagrama.getPort());
                socketApi2.send(mensajeResp);
                System.out.println("Mensaje de respuesta enviado");
            }
        }finally {
            System.out.println("Cerramos el socket");
            socketApi2.close();
            System.out.println("Terminamos en Api2");
        }
    }
}

