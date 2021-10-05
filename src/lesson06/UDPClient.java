package lesson06;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public static void main(String args[]) throws Exception {
		Scanner input = new Scanner(System.in);
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		String sentence = input.nextLine();
		sendData = sentence.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 8876);
		;
		DatagramPacket receivePacket = new DatagramPacket(receiveData, sentence.length());
		clientSocket.receive(receivePacket);
		String modifiedsentence = new String(receivePacket.getData());
		System.out.println("From Server:" + modifiedsentence);
		clientSocket.close();
	}
}
