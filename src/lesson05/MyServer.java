package lesson05;

import java.net.ServerSocket;
import java.net.Socket;


public class MyServer {
	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(80);
			while(true) {
				Socket s = server.accept();
				System.out.println(s.getInetAddress());
				s.close();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
}
