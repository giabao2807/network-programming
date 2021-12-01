package udpFibonacii;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class FiboClient {
	public static void main(String[] args) {
		new FiboClient();
	}

	public FiboClient() {
		Scanner sc = new Scanner(System.in);
		try {
			byte receiceData[] = new byte[1000];
			while (true) {
				DatagramSocket soc = new DatagramSocket();
				String str = sc.nextLine();
				DatagramPacket sedata = new DatagramPacket(str.getBytes(), str.length(),
						InetAddress.getByName("localhost"), 5000);
				soc.send(sedata);
				
				DatagramPacket redata = new DatagramPacket(receiceData, 1000);
				soc.receive(redata);
				
				String check = new String(new String(redata.getData()));
				boolean isCheck = check.trim().equals("stop");
				System.out.println(isCheck);
				if (isCheck)
					break;
			}
		} catch (Exception e) {
		}
	}

}