package lesson04;

import java.net.InetAddress;

public class Mylocalhost {
	public static void main(String[] args) {
		try {
		InetAddress myHost = InetAddress.getLocalHost();
		System.out.println(myHost.getHostAddress());
		System.out.println(myHost.getHostName());
		} catch(Exception e) {
			
		}
	}
}
