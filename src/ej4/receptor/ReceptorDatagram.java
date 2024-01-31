package ej4.receptor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class ReceptorDatagram {

    public static void main(String[] args) throws IOException {

        System.out.println("Creamos el DatagramSocket 2");

        InetSocketAddress dirApi2 = new InetSocketAddress("localhost", 55556);
        DatagramSocket socketApi2 = new DatagramSocket(dirApi2);


        System.out.println("Esperando respuesta DatagramSocket 1");

        byte[] respuesta = new byte[50];
        DatagramPacket datagrama = new DatagramPacket(respuesta, respuesta.length);
        socketApi2.receive(datagrama);
        String strRespuesta = new String(respuesta).trim();
        //condicional segun contenga una palabra u otra
        String mensajeSegunPalabra = "";
        if (strRespuesta.contains("Hola")) {
            mensajeSegunPalabra = "Hola que tal?";
        } else if (strRespuesta.contains("Adiós")) {
            mensajeSegunPalabra = "Hasta luego";
        }else {
            mensajeSegunPalabra = "He recibido tu mensaje";
        }
        System.out.println("Enviamos respuesta a DatagramSocket 1");
        DatagramPacket mensajeResp = new DatagramPacket(mensajeSegunPalabra.getBytes(),
                mensajeSegunPalabra.getBytes().length,datagrama.getAddress(),datagrama.getPort());
        socketApi2.send(mensajeResp);
        System.out.println("Mensaje de respuesta enviado");
        System.out.println("Cerramos el socket");
        socketApi2.close();
        System.out.println("Terminamos en Api2");


    }
}
