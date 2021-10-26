package lesson06;

import java.net.*;

public class UDPServer {
	public static void main(String args[]) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(8876);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while (true) {
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			String sentence = new String(receivePacket.getData());
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			System.out.println("" + IPAddress + "," + port + ":" + sentence);
			String capsent = sentence.toUpperCase();
			sendData = capsent.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, capsent.length(), IPAddress, port);
			serverSocket.send(sendPacket);
		}
	}
}
