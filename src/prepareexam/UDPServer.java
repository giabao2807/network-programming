package prepareexam;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPServer {
	public static void main(String[] args) {
		try {
			DatagramSocket serverSocket = new DatagramSocket(8876);
			byte[] receiveData= new byte[1024];
			byte[] sendData = new byte[1024];
			while(true) {
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);
				String sentence = new String(receivePacket.getData());
				InetAddress ipaddress = receivePacket.getAddress();
				int port= receivePacket.getPort();
				System.out.println(""+ipaddress+""+": " + sentence);
				String capsent= sentence.toUpperCase();
				sendData=capsent.getBytes();
				DatagramPacket sendPacket= new DatagramPacket(sendData, sendData.length,ipaddress,port);
				serverSocket.send(sendPacket);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
}
