package udpCalculator;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class CalculatorClientConso {
	public static void main(String[] args) {
		new CalculatorClientConso();
	}

	public CalculatorClientConso() {
		Scanner sc = new Scanner(System.in);
		try {
			byte buf[] = new byte[1000];
			while (true) {
				DatagramSocket soc = new DatagramSocket();
				String str = sc.nextLine();
				DatagramPacket sedata = new DatagramPacket(str.getBytes(), str.length(),
						InetAddress.getByName("localhost"), 5000);
				soc.send(sedata);
				DatagramPacket redata = new DatagramPacket(buf, 1000);
				soc.receive(redata);
				String check = new String(new String(redata.getData()));

				System.out.println(check);

			}
		} catch (Exception e) {
		}
	}
}