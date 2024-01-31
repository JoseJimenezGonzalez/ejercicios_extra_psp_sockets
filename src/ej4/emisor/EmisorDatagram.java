package ej4.emisor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class EmisorDatagram {

    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);

        System.out.println("Creamos el DatagramSocket 1");

        InetSocketAddress dirApi1 = new InetSocketAddress("localhost", 55555);
        DatagramSocket socketApi1 = new DatagramSocket(dirApi1);


        System.out.println("Mensaje para el receptor");

        String mensaje = teclado.nextLine();

        InetAddress dirRecep = InetAddress.getByName("localhost");
        DatagramPacket datagrama = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, dirRecep, 55556);

        socketApi1.send(datagrama);

        System.out.println("Mensaje enviado");

        System.out.println("Esperamos respuesta del DatagramSocket 2");

        byte [] respuesta = new byte[50];
        DatagramPacket datagrama_resp = new DatagramPacket(respuesta, respuesta.length);

        socketApi1.receive(datagrama_resp);

        System.out.println("Respuesta recibida: " + new String(respuesta));
        System.out.println("Cerrado el DatagramSocket 1");

        socketApi1.close();

    }
}
