package prepareexam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			DatagramSocket clientsoc = new DatagramSocket();
			InetAddress ipAddress = InetAddress.getByName("localhost");

			byte[] sendData = new byte[1024];
			byte[] receiveData = new byte[1024];

			String sentence = sc.nextLine();
			sendData = sentence.getBytes();

			DatagramPacket sendpacket = new DatagramPacket(sendData, sendData.length, ipAddress, 8876);
			DatagramPacket receivepacket = new DatagramPacket(receiveData, receiveData.length);
			clientsoc.receive(receivepacket);
			String mess = new String(receivepacket.getData());
			System.out.println("From server: " + mess);
			clientsoc.close();
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
}
