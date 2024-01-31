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
        System.out.println("Mensaje recibido: "+new String(respuesta).trim());


        System.out.println("Enviamos respuesta a DatagramSocket 1");
        String mensaje = "Mensaje de respuesta desde DatagramSocket 2";
        //InetAddress dirRecep = InetAddress.getByName("localhost");
        DatagramPacket mensaje_resp = new DatagramPacket(mensaje.getBytes(),
                mensaje.getBytes().length,datagrama.getAddress(),datagrama.getPort());

        socketApi2.send(mensaje_resp);
        System.out.println("Mensje de respuesta enviado");
        System.out.println("Cerramos el socket");
        socketApi2.close();
        System.out.println("Terminamos en Api2");


    }
}
