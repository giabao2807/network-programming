package lesson04;

import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ConnectWeb {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("Name: ");
			String name = sc.next();
			int port = 80;
			Socket soc = new Socket(InetAddress.getByName(name),port);
			System.out.println(soc.getLocalAddress());
			System.out.println(soc.getInetAddress());
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
